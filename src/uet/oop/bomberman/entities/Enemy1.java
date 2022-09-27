package uet.oop.bomberman.entities;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.util.Duration;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Enemy1 extends Entity {

    public Enemy1(int x, int y, Image img) {
        super( x, y, img);
    }
    Random rand = new Random();
    int ranNum = rand.nextInt(2) + 1;

    public void update() {
        if (getDirect()==1 ) x -=1;
        if (getDirect()==2 ) x +=1;
        if (getDirect()==3 ) y +=1;
        if (getDirect()==4 ) y -=1;
    }
    int getDirect() {
                if (this.checkWall())
                {
                    switch (ranNum) {
                    case 1 : x += 1; break;
                    case 2 : x -=1 ; break;
                    case 3 : y -= 1; break;
                    case 4 : y += 1; break;
                }
                ranNum = rand.nextInt(4)+1;}
               return ranNum;
    }
}
