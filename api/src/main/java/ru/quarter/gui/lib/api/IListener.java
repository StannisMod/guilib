package ru.quarter.gui.lib.api;

/**
 * Functional interface for all listeners in GuiLib
 * Use IListener#setTarget() for binding the listener and IListener#listen() to tick the listener
 */

public interface IListener<T extends IGraphicsComponent> {

    void setTarget(T component);

    T getTarget();

    default void listen() {
        listen(getTarget());
    }

    void listen(T component);
}