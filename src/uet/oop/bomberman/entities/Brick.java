package uet.oop.bomberman.entities;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Wall{
    private String status = "remain";

    public void setStatus(String status) {
        this.status = status;
    }

    int count_destroy = 75;
    public Brick(int x, int y, Image img) {
        super(x, y, img);
    }
    Bounds bound = new BoundingBox(x,y,32,32);
    @Override
    public void update() {
        if (this.status.equals("destroy")) {
            if (count_destroy > 20) {
                animate += Sprite.DEFAULT_SIZE/10;
                img = Sprite.movingSprite(Sprite.brick_exploded,Sprite.brick_exploded1,Sprite.brick_exploded2,animate,
                        Sprite.DEFAULT_SIZE).getFxImage();
            }
            count_destroy--;
            if (count_destroy == 0) x = 1000;
        }
    }
}
