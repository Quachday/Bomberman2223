package uet.oop.bomberman.entities;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.util.Random;

public class Enemy1 extends Entity {
    public Enemy1(int x, int y, Image img) {
        super( x, y, img);
    }
    Random rand = new Random();
    int ranNum = rand.nextInt(4)+1;

    public void update() {

        if(ranNum == 1) x -= 0.2;
        else if (ranNum == 2) x += 0.5;
        else if (ranNum == 3) y += 0.5;
        else if (ranNum == 4) y -= 0.5;
    }
}
