package ru.quarter.gui.lib.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class TextureMapping {

    private ResourceLocation location;
    private int u;
    private int v;
    private int textureX;
    private int textureY;
    private int textureWidth;
    private int textureHeight;

    protected TextureMapping() {
        this(null);
    }

    public TextureMapping(ResourceLocation location) {
        this.location = location;
    }

    public TextureMapping(ResourceLocation location, int u, int v, int textureX, int textureY, int textureWidth, int textureHeight) {
        this(location);
        this.setU(u);
        this.setV(v);
        this.textureX = textureX;
        this.textureY = textureY;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
    }

    public TextureMapping up() {
        return new TextureMapping(location, u, v + textureY, textureX, textureY, textureWidth, textureHeight);
    }

    public TextureMapping down() {
        return new TextureMapping(location, u, v - textureY, textureX, textureY, textureWidth, textureHeight);
    }

    public TextureMapping left() {
        return new TextureMapping(location, u - textureX, v + textureY, textureX, textureY, textureWidth, textureHeight);
    }

    public TextureMapping right() {
        return new TextureMapping(location, u + textureX, v, textureX, textureY, textureWidth, textureHeight);
    }

    public TextureMapping(ResourceLocation location, int u, int v, int textureX, int textureY) {
        this(location, u, v, textureX, textureY, 256, 256);
    }

    public void draw(int x, int y, int width, int height, float zLevel) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(location);
        GraphicsHelper.drawTexturedModalRect(x, y, width, height, u, v, textureX, textureY, textureWidth, textureHeight, zLevel);
    }

    public void draw(int x, int y, int dx, int dy, int width, int height, float zLevel) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(location);
        GraphicsHelper.drawTexturedModalRect(x, y, width, height, u + dx, v + dy, textureX, textureY, textureWidth, textureHeight, zLevel);
    }

    public void draw(int x, int y, int dx, int dy, int texDX, int texDY, int width, int height, float zLevel) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(location);
        GraphicsHelper.drawTexturedModalRect(x, y, width, height, u + dx, v + dy, textureX + texDX, textureY + texDY, textureWidth, textureHeight, zLevel);
    }

    protected void setLocation(ResourceLocation location) {
        this.location = location;
    }

    public int getU() {
        return u;
    }

    public void setU(int u) {
        if (u < 0 || u > 1) {
            throw new IllegalArgumentException("U out of bounds: " + u);
        }
        this.u = u;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        if (v < 0 || v > 1) {
            throw new IllegalArgumentException("V out of bounds: " + v);
        }
        this.v = v;
    }

    public int getTextureX() {
        return textureX;
    }

    public void setTextureX(int textureX) {
        this.textureX = textureX;
    }

    public int getTextureY() {
        return textureY;
    }

    public void setTextureY(int textureY) {
        this.textureY = textureY;
    }

    public int getTextureWidth() {
        return textureWidth;
    }

    public void setTextureWidth(int textureWidth) {
        this.textureWidth = textureWidth;
    }

    public int getTextureHeight() {
        return textureHeight;
    }

    public void setTextureHeight(int textureHeight) {
        this.textureHeight = textureHeight;
    }
}
