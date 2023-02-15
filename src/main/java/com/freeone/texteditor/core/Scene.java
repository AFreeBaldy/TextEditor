package com.freeone.texteditor.core;

import com.freeone.texteditor.core.interfaces.*;
import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedHashSet;


public abstract class Scene implements Subject, Observer, KeyboardListener, MouseListener, Renderable {

    private final Window applet;
    protected final HashMap<SceneEvents, LinkedHashSet<Observer>> eventsMap;
    public Scene(Window applet) {
        this.applet = applet;
        eventsMap = new HashMap<>();
        for (SceneEvents event : SceneEvents.values()) {
            eventsMap.put(event, new LinkedHashSet<>());
        }
        applet.registerMethod("draw", this);
        applet.registerMethod("keyEvent", this);
        applet.registerMethod("mouseEvent", this);

    }

    public void keyEvent(KeyEvent event) {
        notifyObservers(String.valueOf(SceneEvents.KEY_EVENT), event);
    }


    public void draw() {
        applet.background(0);
        notifyObservers(String.valueOf(SceneEvents.ON_DRAW));
    }

    @Override
    public void update(Object eventName, Object... args) {

    }

    @Override
    public void registerObserver(Observer observer, String eventName) {
        SceneEvents event = SceneEvents.valueOf(eventName.toUpperCase().strip());
        eventsMap.get(event).add(observer);
    }

    @Override
    public void removeObserver(Observer observer, String eventName) {
        SceneEvents event = SceneEvents.valueOf(eventName.toUpperCase().strip());
        eventsMap.get(event).remove(observer);
    }

    @Override
    public void notifyObservers(String eventName, Object... args) {
        SceneEvents event = SceneEvents.valueOf(eventName.toUpperCase().strip());
        eventsMap.get(event).forEach((Observer observer) -> observer.update(event, args));
    }

    @Override
    public void mouseEvent(MouseEvent event) {
        notifyObservers(String.valueOf(SceneEvents.MOUSE_EVENT), event);
    }

    public Window getWindow() {
        return applet;
    }

    public enum SceneEvents {
        KEY_EVENT,
        MOUSE_EVENT,
        ON_DRAW
    }
}
