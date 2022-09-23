package uet.oop.bomberman.entities;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.BombermanGame;

import java.util.ArrayList;





public class Bomber extends Entity {

    public Bomber(double x, double y, Image img) {

        super( x, y, img);
        rect.setX(x);
        rect.setY(y);
        rect.setWidth(20);
        rect.setHeight(32);

    }
    Rectangle rect = new Rectangle();


    public void update() {
    }
}
