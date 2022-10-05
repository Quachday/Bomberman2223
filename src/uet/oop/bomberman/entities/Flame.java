package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Flame extends Entity{
    int direction;
    public Flame(int x, int y, Image img,int direction) {
        super(x,y,img);
        this.direction = direction;

    }
    @Override
    public void update() {
        if (direction == 0) {
            animate+= Sprite.DEFAULT_SIZE/10;
            img = Sprite.movingSprite(Sprite.explosion_horizontal1,Sprite.explosion_horizontal2,animate,Sprite.DEFAULT_SIZE).getFxImage();
        }
        else if (direction == 1) {
            animate+= Sprite.DEFAULT_SIZE/10;
            img = Sprite.movingSprite(Sprite.explosion_vertical1,Sprite.explosion_vertical2,animate,Sprite.DEFAULT_SIZE).getFxImage();
        }
        for (Entity e : Management.enemy) {
            if (this.intersects(e)) e.setX(1000);
        }
        for (Entity e : Management.bricks) {
            if (this.intersects(e)) e.setX(1000);
        }
    }
}
