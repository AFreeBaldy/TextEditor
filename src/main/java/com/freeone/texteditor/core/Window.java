package com.freeone.texteditor.core;

import com.freeone.texteditor.core.interfaces.Observer;
import com.freeone.texteditor.core.interfaces.Subject;
import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import java.util.ArrayList;

public class Window extends PApplet {

    private ArrayList<Scene> scenes;
    private Scene currentScene;
    private long appStartTime;

    public Window() {
        scenes = new ArrayList<>();
    }
    @Override
    public void settings() {
        int SCREEN_WIDTH = 1080;
        size(SCREEN_WIDTH, (int) (SCREEN_WIDTH / 16.0 * 9.0), P2D);
    }

    @Override
    public void setup() {
        windowTitle("Text Editor");
        Scene scene = new PageScene(this);
        currentScene = scene;
        scenes.add(scene);
        appStartTime = System.nanoTime();
        frameRate(120);
    }

    @Override
    public void draw() {
    }
}
