/*
 * Copyright 2020 Stanislav Batalenkov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.quarter.gui.lib.components.container;

import com.github.quarter.gui.lib.GuiLib;
import com.github.quarter.gui.lib.api.IGraphicsComponent;
import com.github.quarter.gui.lib.api.IGraphicsLayout;
import com.github.quarter.gui.lib.api.ISelectable;
import com.github.quarter.gui.lib.api.ISelector;
import com.github.quarter.gui.lib.utils.LayoutContent;

import java.util.HashMap;
import java.util.Map;

public class GTabPanel<K extends IGraphicsComponent, V extends IGraphicsComponent> extends GList<K> implements ISelector {

    private static final LayoutContent<? extends IGraphicsComponent> EMPTY_CONTENT = LayoutContent.create();

    private int selected;
    private IGraphicsLayout<V> target;
    private final Map<Integer, LayoutContent<V>> contentMap = new HashMap<>();

    private GTabPanel() {
        this.setSelector(this);
    }

    @Override
    public int getSelectedId() {
        return selected;
    }

    @Override
    public void select(int id) {
        this.selected = id;
    }

    @Override
    public void onSelect(IGraphicsComponent component) {
        if (getSelectedId() == component.getID()) {
            this.onDeselect(getSelectedComponent());
        } else if (component instanceof ISelectable) {
            ISelectable selectable = (ISelectable) component;
            selectable.onSelect();
            this.select(selectable);
        } else {
            this.unselect();
        }

        if (target == null) {
            return;
        }

        LayoutContent<? extends IGraphicsComponent> content = contentMap.get(component.getID());
        if (content == null) {
            GuiLib.warn(this, "Selected unmapped component, setting empty content");
        }
        target.setContent(content == null ? EMPTY_CONTENT : content);
    }

    @Override
    public void onDeselect(IGraphicsComponent component) {
        if (component instanceof ISelectable) {
            ((ISelectable) component).onDeselect();
        }
        target.setContent(EMPTY_CONTENT);
    }

    public void setTarget(IGraphicsLayout<V> target) {
        this.target = target;
        unselect();
    }

    public static class Builder<K extends IGraphicsComponent, V extends IGraphicsComponent> {

        private final GTabPanel<K, V> instance = new GTabPanel<>();

        public Builder<K, V> size(int width, int height) {
            instance.setWidth(width);
            instance.setHeight(height);
            return this;
        }

        public Builder<K, V> target(IGraphicsLayout<V> target) {
            instance.target = target;
            return this;
        }

        public Builder<K, V> setContentMap(Map<Integer, Map<Integer, V>> contentMap) {
            contentMap.forEach(this::putContent);
            return this;
        }

        public Builder<K, V> putContent(int selectedId, V component) {
            instance.contentMap.compute(selectedId, (key, value) -> {
                if (value == null) {
                    value = LayoutContent.create();
                }
                value.putComponent(component);
                return value;
            });
            return this;
        }

        public Builder<K, V> putContent(int selectedId, Map<Integer, V> content) {
            instance.contentMap.put(selectedId, LayoutContent.withContent(content));
            return this;
        }

        public Builder<K, V> placeAt(int x, int y) {
            instance.setX(x);
            instance.setY(y);
            return this;
        }

        public GTabPanel<K, V> build() {
            return instance;
        }
    }
}
