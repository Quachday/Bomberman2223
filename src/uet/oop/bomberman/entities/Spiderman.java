package uet.oop.bomberman.entities;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;

import static uet.oop.bomberman.BombermanGame.*;


public class Spiderman extends Bomber {
    public Spiderman(double x, double y, Image img) {

        super( x, y, img);
    }



    public void update() {
        move();
    }
    @Override
    public void move() {
        if(input.contains("A")) {
            x-=2;
            state++;
            img = Sprite.movingSprite(Sprite.spider_left_1, Sprite.spider_left_2, 15+state, 3 + state).getFxImage();
            if (state == 20) state = 1;
            s = 'L';
        }
        if(input.contains("D")) {
            x+=2;
            state++;
            if (state == 20) state = 1;
            img = Sprite.movingSprite(Sprite.spider_right_1,Sprite.spider_right_2,15+state,3+state).getFxImage();
            s = 'R';
        }
        if(input.contains("W")) {
            y-=2;
            state++;
            if (state == 15) state = 1;
            img = Sprite.movingSprite(Sprite.spider_up_1,Sprite.spider_up_2,15+state,3+state).getFxImage();
            s = 'U';
        }
        if(input.contains("S")) {
            y+=2;
            state++;
            img = Sprite.movingSprite(Sprite.spider_down_1,Sprite.spider_down_2,15+state,3+state).getFxImage();
            if (state == 15) state = 1;
            s = 'D';
        }
        if ( x <= 80 && x >=64 && y >= 96 && y <= 112) { x = 850; y = 352; }
        if ( x <= 848 && x >= 832 && y >= 352 && y <= 368) { x = 62 ; y = 96;}
        if (input.isEmpty()) {
            switch (s) {
                case 'L':
                    img = Sprite.spider_left.getFxImage();
                    state = 1;
                    break;
                case 'R':
                    img = Sprite.spider_right.getFxImage();
                    state = 1;
                    break;
                case 'U':
                    img = Sprite.spider_up.getFxImage();
                    state = 1;
                    break;
                case 'D':
                    img = Sprite.spider_down.getFxImage();
                    state = 1;
                    break;
            }
        }
    }

}
