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
import java.util.Timer;
import java.util.TimerTask;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.entities.Management.bomberman;
import static uet.oop.bomberman.entities.Management.bombers;


public class Bomber extends Entity {

    public static String statusman = "alive";
    private static int numOfLives = 3;
    private static int count = 0;
    private int speed = Sprite.SCALED_SIZE / 5;
    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    char s;

    int state = 1;



    public void update() {
        if (statusman.equals("alive"))
        move();
        else if (statusman.equals("die") && x < 1000) { ondie(); }

    }

    public void ondie() {
                state++;
                img = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, 15 + state, 3 + state).getFxImage();
                count++;
                if(count == 40) {
                    numOfLives--;
                    if (numOfLives > 0) {
                        img = Sprite.player_right.getFxImage();
                    this.x = 32;
                    this.y = 32;
                    statusman = "alive";
                    count = 0; }
                    else {
                        this.x = 10000;
                    }
                }
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
            this.x -= 2;
            //if (checkBrick() || checkWall() || checkBomb()) {
            if(checkWall()) {
                this.x += 2;
                supportRow();
             }
    }

    public void goRight() {
            this.x += 2;
            //if (checkBrick() || checkWall() || checkBomb()) {
            if(checkWall()) {
                this.x -= 2;
                supportRow();
            }
    }

    public void goUp() {
            this.y -= 2;
            //if (checkBrick() || checkWall() || checkBomb()) {
            if(checkWall()) {
                this.y += 2;
                supportColumn();
             }
    }

    public void goDown() {
            this.y += 2;
            //if (checkBrick() || checkWall() || checkBomb()) {
            if(checkWall()) {
                this.y -= 2;
                supportColumn();
            }
    }
    public void move() {
        if(input.contains("LEFT")) {
            //x-=1;
            goLeft();
            state++;
            img = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, 15+state, 3 + state).getFxImage();
            if (state == 20) state = 1;
            s = 'L';
        }
        if(input.contains("RIGHT")) {
            //x+=1;
            goRight();
            state++;
            if (state == 20) state = 1;
            img = Sprite.movingSprite(Sprite.player_right_1,Sprite.player_right_2,15+state,3+state).getFxImage();
            s = 'R';
        }
        if(input.contains("UP")) {
            //y-=1;
            goUp();
            state++;
            if (state == 15) state = 1;
            img = Sprite.movingSprite(Sprite.player_up_1,Sprite.player_up_2,15+state,3+state).getFxImage();
            s = 'U';
        }
        if(input.contains("DOWN")) {
            //y+=1;
            goDown();
            state++;
            img = Sprite.movingSprite(Sprite.player_down_1,Sprite.player_down_2,15+state,3+state).getFxImage();
            if (state == 15) state = 1;
            s = 'D';
        }
        if ( x <= 80 && x >=64 && y >= 96 && y <= 112) { x = 850; y = 352; }
        if ( x <= 848 && x >= 832 && y >= 352 && y <= 368) { x = 62 ; y = 96;}
        if (input.isEmpty()) {
            switch (s) {
                case 'L':
                    img = Sprite.player_left.getFxImage();
                    state = 1;
                    break;
                case 'R':
                    img = Sprite.player_right.getFxImage();
                    state = 1;
                    break;
                case 'U':
                    img = Sprite.player_up.getFxImage();
                    state = 1;
                    break;
                case 'D':
                    img = Sprite.player_down.getFxImage();
                    state = 1;
                    break;
            }
        }
    }

}
