package com.freeone.texteditor.core;

import com.freeone.texteditor.core.interfaces.Observer;
import processing.core.PApplet;
import processing.core.PFont;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class TextBlock extends Block implements Observer {
    private final StringBuilder content;
    private final StringBuilder defaultContent = new StringBuilder("Type here");
    private final Page page;
    public TextBlock(Page page) {
        this.page = page;
        content = new StringBuilder("");
        page.registerObserver(this, "KEY_EVENT");
        page.registerObserver(this, "MOUSE_EVENT");
        page.registerObserver(this, "ON_DRAW");
    }

    @Override
    public void update(Object eventName, Object... args) {
        switch ((Page.Events) eventName) {
            case ON_DRAW -> draw();
            case KEY_EVENT -> keyEvent((KeyEvent) args[0]);
            case MOUSE_EVENT -> mouseEvent((MouseEvent) args[0]);
        }
    }

    @Override
    public void draw() {
        Window window = page.getScene().getWindow();
        window.color(255);
        window.textSize(20);
        window.text(content.length() == 0 ? defaultContent.toString() : content.toString(), 100, 100);
    }

    @Override
    public void keyEvent(KeyEvent event) {
        Window window = page.getScene().getWindow();
        switch (event.getAction()) {
            case KeyEvent.PRESS -> onKeyPressed(event);
            case KeyEvent.RELEASE -> onKeyReleased(event);
        }
    }

    @Override
    public void mouseEvent(MouseEvent event) {

    }

    private void onKeyPressed(KeyEvent event) {

    }

    private void onKeyReleased(KeyEvent event) {
        if (event.getKeyCode() == PApplet.CODED)
            return;
        content.append(event.getKey());
    }
}
