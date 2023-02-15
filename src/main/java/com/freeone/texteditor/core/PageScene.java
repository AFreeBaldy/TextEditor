package com.freeone.texteditor.core;

import com.freeone.texteditor.core.interfaces.Observer;
import processing.core.PApplet;
import processing.event.KeyEvent;

import java.lang.annotation.Inherited;
import java.util.ArrayList;

public class PageScene extends Scene{
    private ArrayList<Page> pages;
    private Page currentPage;
    public PageScene(Window applet) {
        super(applet);
        pages = new ArrayList<Page>();
        Page page = new Page(this);
        pages.add(page);
        currentPage = page;
    }
}
