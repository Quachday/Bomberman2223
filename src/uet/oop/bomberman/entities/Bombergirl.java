package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.input;
import static uet.oop.bomberman.entities.Management.*;
import static uet.oop.bomberman.entities.Management.bomberman;


public class Bombergirl extends Bomber {
    public static String status = "alive";

    public static void setStatus(String status) {
        Bombergirl.status = status;
    }

    private static int count = 0;
    public static int numOfLives = 3;
    public Bombergirl(int x, int y, Image img) {

        super( x, y, img);
    }

    char s;

    int state = 1;



    public void update() {
        if(bombsofgirl.get(indexOfBombs).settled)
        {if (bombergirl.indexOfBombs == bombsofgirl.size()-1) bombergirl.indexOfBombs = 0;
        else bombergirl.indexOfBombs++;}
        if (status.equals("alive"))
            move();
        else if (status.equals("die") && x < 1000) {  ondie(); }
    }
    public void move() {
        if(input.contains("LEFT")) {
            //x-=1;
            goLeft();
            state++;
            img = Sprite.movingSprite(Sprite.spider_left_1, Sprite.spider_left_2, 15+state, 3 + state).getFxImage();
            if (state == 20) state = 1;
            s = 'L';
        }
        if(input.contains("RIGHT")) {
            //x+=1;
            goRight();
            state++;
            if (state == 20) state = 1;
            img = Sprite.movingSprite(Sprite.spider_right_1,Sprite.spider_right_2,15+state,3+state).getFxImage();
            s = 'R';
        }
        if(input.contains("UP")) {
            //y-=1;
            goUp();
            state++;
            if (state == 15) state = 1;
            img = Sprite.movingSprite(Sprite.spider_up_1,Sprite.spider_up_2,15+state,3+state).getFxImage();
            s = 'U';
        }
        if(input.contains("DOWN")) {
            //y+=1;
            goDown();
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
    public void ondie() {
        state++;
        img = Sprite.movingSprite(Sprite.spider_dead1, Sprite.spider_dead2, Sprite.spider_dead3, 15 + state, 3 + state).getFxImage();
        count++;
        if(count == 40) {
            numOfLives--;
            if (numOfLives > 0)
            {img = Sprite.spider_right.getFxImage();
            this.x = 32;
            this.y = 32 * 11;
            status = "alive";
            this.count = 0;}
            else {
                this.x = 1000000;
            }
        }
    }

}
