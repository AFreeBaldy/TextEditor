package com.freeone.texteditor.core;

import com.freeone.texteditor.core.interfaces.*;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class Page implements Observer, Subject, Renderable, KeyboardListener, MouseListener {
    private Scene scene;
    private LinkedList<Block> blocks;

    protected final HashMap<Events, LinkedHashSet<Observer>> eventsMap;
    public Page(Scene scene) {
        this.scene = scene;
        blocks = new LinkedList<Block>();
        scene.registerObserver(this, "KEY_EVENT");
        scene.registerObserver(this, "MOUSE_EVENT");
        scene.registerObserver(this, "ON_DRAW");
        eventsMap = new HashMap<>();
        for (Events event : Events.values()) {
            eventsMap.put(event, new LinkedHashSet<>());
        }
    }

    @Override
    public void update(Object eventName, Object... args) {
        switch ((Scene.SceneEvents) eventName) {
            case ON_DRAW -> draw();
            case KEY_EVENT -> keyEvent((KeyEvent) args[0]);
            case MOUSE_EVENT -> mouseEvent((MouseEvent) args[0]);
        }
    }

    public void draw() {
        notifyObservers(String.valueOf(Events.ON_DRAW));
    }

    public void keyEvent(KeyEvent event) {
        notifyObservers(String.valueOf(Events.KEY_EVENT), event);
    }

    public void mouseEvent(MouseEvent event) {
        // Check if a text block exist if it doesn't create a default textblock
        if (blocks.size() == 0) {
            TextBlock textBlock = new TextBlock(this);
            blocks.add(textBlock);
        }
        notifyObservers(String.valueOf(Events.MOUSE_EVENT), event);
    }

    @Override
    public void registerObserver(Observer observer, String eventName) {
        Events event = Events.valueOf(eventName.toUpperCase().strip());
        eventsMap.get(event).add(observer);
    }

    @Override
    public void removeObserver(Observer observer, String eventName) {
        Events event = Events.valueOf(eventName.toUpperCase().strip());
        eventsMap.get(event).remove(observer);
    }

    @Override
    public void notifyObservers(String eventName, Object... args) {
        Events event = Events.valueOf(eventName.toUpperCase().strip());
        var observers = eventsMap.get(event);
        if (observers.size() != 0)
            observers.forEach((Observer observer) -> observer.update(event, args));
    }

    public Scene getScene() {
        return scene;
    }

    public enum Events {
        ON_DRAW,
        KEY_EVENT,
        MOUSE_EVENT
    }
}
