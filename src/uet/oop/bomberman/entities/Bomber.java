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

import static uet.oop.bomberman.BombermanGame.*;


public class Bomber extends Entity {


    public Bomber(double x, double y, Image img) {

        super( x, y, img);
    }



    public void update() {

        if(input.contains("LEFT")) {
            x--;
        }
        if(input.contains("RIGHT")) {
            x++;
        }
        if(input.contains("UP")) {
            y--;
        }
        if(input.contains("DOWN")) {
            y++;
        }

    }


}
