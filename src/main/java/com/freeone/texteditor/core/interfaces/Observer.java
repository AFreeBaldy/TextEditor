package com.freeone.texteditor.core.interfaces;

public interface Observer {

    void update(Object eventName, Object... args);
}
