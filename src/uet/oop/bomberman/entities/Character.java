package uet.oop.bomberman.entities;

import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.graphics.Sprite;
import javafx.scene.image.Image;

public abstract class Character extends AnimatedEntity{
    protected boolean _alive = true;

    public abstract void kill();
    @Override
    public abstract void update();
    public Character(double x, double y, Image image) {
        this.x = x * Sprite.SCALED_SIZE;
        this.y = y * Sprite.SCALED_SIZE;
        this.img = img;
        rect = new Rectangle(x,y,32,32);
        rect.setVisible(false);
    }


}
