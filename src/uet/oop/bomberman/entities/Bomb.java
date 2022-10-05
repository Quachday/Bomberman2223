package uet.oop.bomberman.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.io.FileNotFoundException;
import java.util.*;


import static uet.oop.bomberman.BombermanGame.input;
import static uet.oop.bomberman.entities.Management.*;

public class Bomb extends Wall {
    Flame flameLeft = new Flame(1000,1000,Sprite.explosion_horizontal.getFxImage(),0);
    Flame flameRight = new Flame(1000,1000,Sprite.explosion_horizontal.getFxImage(),0);
    Flame flameUp = new Flame(1000,1000,Sprite.explosion_vertical.getFxImage(),1);
    Flame flameDown = new Flame(1000,1000,Sprite.explosion_vertical.getFxImage(),1);
    private int countBOMB = 200;
    public boolean settled = false;
    private  int index;
    public Bomb(int x, int y, Image img, int index) {
        super(x, y, img);
        this.index = index;
        flamesvisual.add(flameLeft);
        flamesvisual.add(flameRight);
        flamesvisual.add(flameUp);
        flamesvisual.add(flameDown);
    }
    int state = 1;

    public int getCountBOMB() {
        return countBOMB;
    }


    public void update() {
        if (settled) {
            if (countBOMB > 20) {
                animate += Sprite.DEFAULT_SIZE / 10;
                img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, animate, Sprite.DEFAULT_SIZE).getFxImage();
            }
            if (countBOMB > 0 && countBOMB <= 20) {
                animate += Sprite.DEFAULT_SIZE / 10;
                img = Sprite.movingSprite(Sprite.bomb_exploded1, Sprite.bomb_exploded2, animate, Sprite.SCALED_SIZE).getFxImage();
                flameLeft.setX(x-32);
                flameLeft.setY(y);
                flameRight.setX(x+32);
                flameRight.setY(y);
                flameDown.setX(x);
                flameDown.setY(y+32);
                flameUp.setX(x);
                flameUp.setY(y-32);
            }
            if (countBOMB == 0) {
                x = 1000 + index;
                y = 1000;
                countBOMB = 200;
                settled = false;
                flameLeft.setX(1000);
                flameRight.setX(1000);
                flameUp.setX(1000);
                flameDown.setX(1000);
            }
            countBOMB--;
        }
    }
        }


