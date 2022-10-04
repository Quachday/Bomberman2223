package uet.oop.bomberman.entities;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;

public class Brick extends Wall{
    public Brick(int x, int y, Image img) {
        super(x, y, img);
    }
    Bounds bound = new BoundingBox(x,y,32,32);
    @Override
    public void update() {}
}
