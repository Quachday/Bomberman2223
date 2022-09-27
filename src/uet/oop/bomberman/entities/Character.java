package uet.oop.bomberman.entities;

import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.graphics.Sprite;
import javafx.scene.image.Image;

public abstract class Character extends Entity{
    protected boolean _alive = true;

    public abstract void kill();
    @Override
    public abstract void update();
    public Character(int x, int y, Image image) {
        super(x,y,image);
        rect = new Rectangle(x,y,32,32);
        rect.setVisible(false);
    }


}
