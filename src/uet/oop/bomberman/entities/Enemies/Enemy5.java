package uet.oop.bomberman.entities.Enemies;

import javafx.scene.image.Image;

import java.util.Random;

public class Enemy5 extends Enemy4{
    int count = 50;

    public Enemy5(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (this.status.equals("alive")) move();
        else if (this.status.equals("die")) onDie();
        count--;
        if (count == 0) {
            Random num = new Random();
        }
    }

}
