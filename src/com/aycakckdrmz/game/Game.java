package com.aycakckdrmz.game;

import com.aycakckdrmz.game.graphics.Screen;
import com.aycakckdrmz.game.input.Keyboard;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable {
    //private static final long serialVersionUID = 1L; //convention?

    public static int width = 300;
    public static int height = (width / 16) * 9;
    public static int scale = 3;
    public static String title = "Sokoban";

    private Thread thread;
    private JFrame frame;
    private boolean running = false;

    private Screen screen;
    private Keyboard key;

    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int [] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();


    public Game (){
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);

        screen = new Screen(width, height);

        frame = new JFrame();

        key = new Keyboard();
        addKeyListener(key);
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();


    }


    public synchronized void stop(){
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }


    public void run (){
        long now, lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0.0;
        int frames = 0, updates = 0;
        requestFocus();
        while(running) {
            now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                updates++;
                delta--;
            }
            render();
            frames++;


            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frame.setTitle(title + "   | " + updates + " ups  " + frames + " fps");
                frames = 0;
                updates = 0;
            }

        }
        stop();
    }

    int x = 0, y = 0;
    public void update(){

        key.update();
        if(key.u_) y--;
        if(key.d_) y++;
        if(key.r_) x--;
        if(key.l_) x++;

    }

    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if (bs == null){
            createBufferStrategy(3);
            return;
        }
        screen.clear();
        screen.render(x, y);

        for(int i = 0; i < pixels.length; i++){
            pixels[i] = screen.pixels[i];
        }


        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getWidth(), null);


        g.dispose();
        bs.show();


    }


    public static void main(String[] args){
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle(game.title);
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();

    }



}
