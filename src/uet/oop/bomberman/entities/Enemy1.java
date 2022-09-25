package uet.oop.bomberman.entities;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.awt.*;
import java.util.Random;

public class Enemy1 extends Character {
    public Enemy1(double x, double y, Image img) {
        super( x, y, img);
    }
    Random rand = new Random();
    int ranNum = rand.nextInt(2)+1;

    public void update() {
        if(ranNum==1) x--;
        else if (ranNum == 2) x++;
       // else if (ranNum == 3) y++;
       // else if (ranNum == 4) y--;
    }

    public boolean collide(Entity e) {


        // xử lý va chạm với Bomber
        if (e instanceof Bomber)
        {
            ((Bomber) e).kill();
            return false;
        }
        return true;
    }

    @Override
    public void kill() {
        if(!_alive) return;
        _alive = false;


    }
}
