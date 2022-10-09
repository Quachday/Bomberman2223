package uet.oop.bomberman.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.io.FileNotFoundException;
import java.util.*;


import static uet.oop.bomberman.BombermanGame.input;
import static uet.oop.bomberman.entities.Management.*;

public class Bomb extends Wall {
    List<Flame> flameLeft = new ArrayList<>();
    List<Flame> flameRight = new ArrayList<>();
    List<Flame> flameUp = new ArrayList<>();
    List<Flame> flameDown = new ArrayList<>();

    private int countBOMB = 75;
    public boolean settled = false;
    private  int index;
    public Bomb(int x, int y, Image img, int index) {
        super(x, y, img);
        this.index = index;
        for (int i = 0; i < 5; i++) {
            flameLeft.add(new Flame(1000,1000,Sprite.explosion_horizontal.getFxImage(),0));
            flameRight.add(new Flame(1000,1000,Sprite.explosion_horizontal.getFxImage(),0));
            flameUp.add(new Flame(1000,1000,Sprite.explosion_vertical.getFxImage(),1));
            flameDown.add(new Flame(1000,1000,Sprite.explosion_vertical.getFxImage(),1));
            flamesvisual.add(flameLeft.get(i));
            flamesvisual.add(flameRight.get(i));
            flamesvisual.add(flameUp.get(i));
            flamesvisual.add(flameDown.get(i));
        }

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

                for (int j = 0; j < bomberman.sizeOfFlame; j++)  {
                    if (j == bomberman.sizeOfFlame-1) flameLeft.get(j).direction = 2;
                    flameLeft.get(j).setX(x-32 * (j+1));
                    flameLeft.get(j).setY(y);
                    if (flameLeft.get(j).checkWall())
                    {
                        break;
                    }
                }
                for (int j = 0; j < bomberman.sizeOfFlame; j++)  {
                    if (j == bomberman.sizeOfFlame-1) flameRight.get(j).direction = 3;
                    flameRight.get(j).setX(x+32 * (j+1));
                    flameRight.get(j).setY(y);
                    if (flameRight.get(j).checkWall())
                    {
                        break;
                    }
                }
                for (int j = 0; j < bomberman.sizeOfFlame; j++)  {
                    if (j == bomberman.sizeOfFlame-1) flameUp.get(j).direction = 5;
                    flameUp.get(j).setX(x);
                    flameUp.get(j).setY(y-32 * (j+1));
                    if (flameUp.get(j).checkWall())
                    {
                        break;
                    }
                }
                for (int j = 0; j < bomberman.sizeOfFlame; j++)  {
                    if (j == bomberman.sizeOfFlame-1) flameDown.get(j).direction = 4;
                    flameDown.get(j).setX(x);
                    flameDown.get(j).setY(y+32 * (j+1));
                    if (flameDown.get(j).checkWall())
                    {
                        break;
                    }
                }
            }
            if (countBOMB == 0) {
                BombermanGame.boomExplosion.play();
                x = 1000 + index;
                y = 1000;
                countBOMB = 200;
                img = Sprite.bomb.getFxImage();
                settled = false;
                for (int i = 0; i < bomberman.sizeOfFlame; i++) {
                    flameLeft.get(i).setX(1000);
                    flameLeft.get(i).direction = 0;
                    flameRight.get(i).setX(1000);
                    flameRight.get(i).direction =0;
                    flameDown.get(i).setX(1000);
                    flameDown.get(i).direction=1;
                    flameUp.get(i).setX(1000);
                    flameUp.get(i).direction=1;

                }
            }
            countBOMB--;


        }
    }
        }


