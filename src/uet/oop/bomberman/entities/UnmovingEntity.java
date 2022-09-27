package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.graphics.Sprite;

public abstract class UnmovingEntity extends Entity {
    public UnmovingEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    //public UnmovingEntity(int x, int y, Image img) {
      //  this.x = x;
      //  this.y = y;
     //   this.img = img;
   // }


    public boolean collide(Entity e) {
        return false;
    }
    @Override
    public void update() {}
}

