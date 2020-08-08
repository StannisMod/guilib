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

package ru.quarter.gui.lib.components;

import ru.quarter.gui.lib.api.IGraphicsComponent;
import ru.quarter.gui.lib.api.IListener;
import ru.quarter.gui.lib.components.container.BasicLayout;
import ru.quarter.gui.lib.utils.StyleMap;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class GTooltip extends BasicLayout implements IListener<IGraphicsComponent> {

    protected int xOffset;
    protected int yOffset;

    protected int mouseX;
    protected int mouseY;

    private IGraphicsComponent target;
    private boolean updated;

    private boolean visible;

    private final Map<Class<? extends IGraphicsComponent>, List<IGraphicsComponent>> content = new HashMap<>();

    protected int addComponent(int depth, IGraphicsComponent component, Class<? extends IGraphicsComponent> clazz) {
        content.compute(clazz, (aClass, components) -> {
            if (components == null) {
                components = new LinkedList<>();
            }
            component.setDepth(depth);
            components.add(component);
            return components;
        });
        return component.getID();
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public void setTarget(IGraphicsComponent target) {
        this.target = target;
        updated = true;
    }

    @Override
    public IGraphicsComponent getTarget() {
        return target;
    }

    @Override
    public boolean checkUpdates() {
        return true;
    }

    @Override
    public void update() {
        if (intersects(mouseX, mouseY)) {
            this.x = mouseX - getWidth() - xOffset;
            this.y = mouseY - getHeight() - yOffset;
            return;
        }

        if (getX() + getWidth() <= getParent().getWidth()) {
            this.x = mouseX + xOffset;
        } else {
            this.x = getParent().getWidth() - getWidth();
        }

        if (getY() + getHeight() <= getParent().getHeight()) {
            this.y = mouseY + yOffset;
        } else {
            this.y = getParent().getHeight() - getHeight();
        }

        if (updated) {
            this.clear();
            this.setContent(content.get(getTarget().getClass()));
            updated = false;
        }
    }

    public abstract void initTooltip();

    @Override
    public void init() {
        initTooltip();
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        if (visible) {
            StyleMap.current().drawTooltip(getX(), getY(), getWidth(), getHeight());
            super.draw(mouseX, mouseY);
        }
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    @Override
    public void onMousePressed(int mouseX, int mouseY, int mouseButton) {
        // we will never delegate this event to inner components
    }

    @Override
    public void onMouseReleased(int mouseX, int mouseY, int mouseButton) {
        // we will never delegate this event to inner components
    }

    @Override
    public void onKeyPressed(char typedChar, int keyCode) {
        // we will never delegate this event to inner components
    }

    @Override
    public void onResize(int w, int h) {}
}
