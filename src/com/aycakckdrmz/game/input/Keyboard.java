package com.aycakckdrmz.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{

    public final static int MAX_KEY = 300;
    private boolean [] keys = new boolean[MAX_KEY];

    public boolean u_, d_, l_, r_;

    public void update(){
        u_ = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
        d_ = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_D];
        l_ = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
        r_ = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];

        System.out.println(u_);

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;

    }

    @Override
    public void keyReleased(KeyEvent e) {

        keys[e.getKeyCode()] = false;
    }
}
