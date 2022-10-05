package uet.oop.bomberman.entities;

import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
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
import java.util.Timer;
import java.util.TimerTask;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.entities.Management.*;


public class Bomber extends Entity {

    public static String status = "alive";

    public static void setStatus(String status) {
        Bomber.status = status;
    }

    public static int numOfLives = 3;
    private static int count = 0; // count die
    public int indexOfBombs = 0;
    public int sizeOfFlame = 2; // cua chung 2 players

    public int speed = 2;
    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

  char s;




    public void update() {
        if(bombsofman.get(indexOfBombs).settled)
        {if (bomberman.indexOfBombs == bombsofman.size()-1) bomberman.indexOfBombs = 0;
        else bomberman.indexOfBombs++;}
        //if (status.equals("alive"))
        move();
        //else if (status.equals("die") && x < 1000) { ondie(); }


    }

    public void ondie() {
                animate += Sprite.DEFAULT_SIZE/10;
                img = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, animate, Sprite.DEFAULT_SIZE).getFxImage();
                count++;
                if(count == 40) {
                    numOfLives--;
                    if (numOfLives > 0) {
                        img = Sprite.player_right.getFxImage();
                    this.x = 32;
                    this.y = 32;
                    status = "alive";
                    count = 0; }
                    else {
                        this.x = 10000;
                    }
                }
    }
    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(x, y, Sprite.SCALED_SIZE-10, Sprite.SCALED_SIZE);
    }
    public void supportRow() {
        if (this.y % Sprite.SCALED_SIZE >= 2 * Sprite.SCALED_SIZE / 3) {
            this.y = Sprite.SCALED_SIZE * (this.y / Sprite.SCALED_SIZE) + Sprite.SCALED_SIZE;
        } else if (this.y % Sprite.SCALED_SIZE <= Sprite.SCALED_SIZE / 3) {
            this.y = Sprite.SCALED_SIZE * (this.y / Sprite.SCALED_SIZE);
        }
    }

    public void supportColumn() {
        if (this.x % Sprite.SCALED_SIZE >= 2 * Sprite.SCALED_SIZE / 3) {
            this.x = Sprite.SCALED_SIZE * (this.x / Sprite.SCALED_SIZE) + Sprite.SCALED_SIZE;
        } else if (this.x % Sprite.SCALED_SIZE <= Sprite.SCALED_SIZE / 3) {
            this.x = Sprite.SCALED_SIZE * (this.x / Sprite.SCALED_SIZE);
        }
    }
    public void goLeft() {
            this.x -= speed;
            if (checkBrick() || checkWall() ) {
                this.x += speed;
                supportRow();
             }
    }

    public void goRight() {
            this.x += speed;
            if (checkBrick() || checkWall() ) {
                this.x -= speed;
                supportRow();
            }
    }

    public void goUp() {
            this.y -= speed;
            if (checkBrick() || checkWall() ) {
                this.y += speed;
                supportColumn();
             }
    }

    public void goDown() {
            this.y += speed;
            if (checkBrick() || checkWall() ) {
                this.y -= speed;
                supportColumn();
            }
    }


    public void move() {
        if(input.contains("A")) {
            //x-=1;
            goLeft();
            animate += Sprite.DEFAULT_SIZE / 10;
            img = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, animate, Sprite.DEFAULT_SIZE).getFxImage();
            s = 'L';
        }
        if(input.contains("D")) {
            //x+=1;
            goRight();
            animate += Sprite.DEFAULT_SIZE / 10;
            img = Sprite.movingSprite(Sprite.player_right_1,Sprite.player_right_2,animate,Sprite.DEFAULT_SIZE).getFxImage();
            s = 'R';
        }
        if(input.contains("W")) {
            //y-=1;
            goUp();
            animate += Sprite.DEFAULT_SIZE / 10;
            img = Sprite.movingSprite(Sprite.player_up_1,Sprite.player_up_2,animate,Sprite.DEFAULT_SIZE).getFxImage();
            s = 'U';
        }
        if(input.contains("S")) {
            //y+=1;
            goDown();
            animate += Sprite.DEFAULT_SIZE / 10;
            img = Sprite.movingSprite(Sprite.player_down_1,Sprite.player_down_2,animate,Sprite.DEFAULT_SIZE).getFxImage();
            s = 'D';
        }
        if ( x <= 80 && x >=64 && y >= 96 && y <= 112) { x = 850; y = 352; }
        if ( x <= 848 && x >= 832 && y >= 352 && y <= 368) { x = 62 ; y = 96;}
        if (input.isEmpty()) {
            switch (s) {
                case 'L':
                    img = Sprite.player_left.getFxImage();
                    break;
                case 'R':
                    img = Sprite.player_right.getFxImage();
                    break;
                case 'U':
                    img = Sprite.player_up.getFxImage();
                    break;
                case 'D':
                    img = Sprite.player_down.getFxImage();
                    break;
            }
        }
    }

}
