package com.freeone.texteditor.core.interfaces;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Objects;

public interface Subject {
    void registerObserver(Observer observer, String eventName);
    void removeObserver(Observer observer, String eventName);
    void notifyObservers(String eventName, Object... args);
}

