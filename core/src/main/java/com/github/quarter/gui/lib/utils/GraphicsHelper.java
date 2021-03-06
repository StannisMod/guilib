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

package com.github.quarter.gui.lib.utils;

import com.github.quarter.gui.lib.GuiLib;
import com.github.quarter.gui.lib.api.adapter.IFontRenderer;

public class GraphicsHelper {

    public static void drawCenteredScaledString(String text, int x, int y, double scale, int color) {
        drawCenteredScaledString(GuiLib.standardRenderer(), text, x, y, scale, color);
    }

    public static void drawCenteredScaledString(IFontRenderer fontRenderer, String text, int x, int y, double scale, int color) {
        GuiLib.getResourceManager().helper().drawCenteredScaledString(fontRenderer, text, x, y, scale, color);
    }

    public static void drawScaledString(String text, int x, int y, float scale, int color) {
        drawScaledString(GuiLib.standardRenderer(), text, x, y, scale, color);
    }

    public static void drawScaledString(IFontRenderer fontRenderer, String text, int x, int y, float scale, int color) {
        GuiLib.getResourceManager().helper().drawScaledString(fontRenderer, text, x, y, scale, color);
    }

    public static void drawCenteredString(String text, int x, int y, int color) {
        drawCenteredString(GuiLib.standardRenderer(), text, x, y, color);
    }

    public static void drawCenteredString(IFontRenderer fontRenderer, String text, int x, int y, int color) {
        GuiLib.getResourceManager().helper().drawCenteredString(fontRenderer, text, x, y, color);
    }

    public static void drawString(String text, int x, int y, int color) {
        drawString(GuiLib.standardRenderer(), text, x, y, color);
    }

    public static void drawString(IFontRenderer fontRenderer, String text, int x, int y, int color) {
        GuiLib.getResourceManager().helper().drawString(fontRenderer, text, x, y, color);
    }

    public static void glScissor(int x, int y, int width, int height) {
        GuiLib.getResourceManager().helper().glScissor(x, y, width, height);
    }

    public static void drawTexturedModalRect(int x, int y, int width, int height, int u, int v, int textureWidth, int textureHeight, int textureSizeX, int textureSizeY, float zLevel) {
        GuiLib.getResourceManager().helper().drawTexturedModalRect(x, y, width, height, u, v, textureWidth, textureHeight, textureSizeX, textureSizeY, zLevel);
    }

    public static void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height, float zLevel) {
        GuiLib.getResourceManager().helper().drawTexturedModalRect(x, y, textureX, textureY, width, height, zLevel);
    }
}