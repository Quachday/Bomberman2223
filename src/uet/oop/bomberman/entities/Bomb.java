package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import static uet.oop.bomberman.BombermanGame.bomb;
import static uet.oop.bomberman.BombermanGame.input;

public class Bomb extends AnimatedEntity {
    public Bomb(double x,double y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;

    }
    public int t = 0;
    public void update() {
    }

    @Override
    public boolean collide(Entity e) {
        return true;
    }

}
