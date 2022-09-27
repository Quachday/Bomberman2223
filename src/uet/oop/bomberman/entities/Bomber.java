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


public class Bomber extends Entity {
    private int speed = Sprite.SCALED_SIZE / 5;
    public Bomber(int x, int y, Image img) {

        super( x, y, img);
    }

    char s;

    int state = 1;



    public void update() {
        move();
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
