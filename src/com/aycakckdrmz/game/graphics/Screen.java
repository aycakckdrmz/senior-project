package com.aycakckdrmz.game.graphics;

import java.util.Random;

public class Screen {

    private  int width, height;
    public final int SIZE_MAP = 64;
    public final int SIZE_MAP_MASK = SIZE_MAP-1; //tile size

    public int [] pixels;
    public int [] tiles =  new int [SIZE_MAP * SIZE_MAP];

    private Random rand = new Random();

    public Screen(int width, int height){
        this.width = width;
        this.height = height;
        pixels = new int[width * height]; //54400

        for (int i = 0; i < SIZE_MAP*SIZE_MAP; i++){
            tiles[i] = rand.nextInt(0xffffff);
            tiles[0] = 0;
        }
    }

    public void render(int xOffset, int yOffset){

        for (int y = 0; y < height ; y++){
            //if(y < 0 || y >= height) break;
            int yy = y + yOffset;

            for(int x = 0; x < width; x++){
                //if(x < 0 || x >= width) break;

                int xx = x + xOffset;
                int tileIndex = ((xx >> 4) & SIZE_MAP_MASK) + ((yy >> 3) & SIZE_MAP_MASK) * SIZE_MAP;
                pixels[ x + y * width] = tiles[tileIndex];
            }
        }

    }

    public void clear(){
        for (int i = 0; i < pixels.length; i++ ){
            pixels[i] = 0;
        }

    }

}
