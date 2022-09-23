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

import static uet.oop.bomberman.BombermanGame.input;
import static uet.oop.bomberman.BombermanGame.stillObjects;


public class Bomber extends Entity {


    public Bomber(double x, double y, Image img) {

        super( x, y, img);
        rect.setWidth(20);
        rect.setHeight(32);
    }



    public void update() {
        if(rect.intersects(0,0,990,29)) System.out.println("va cham");
        {if(input.contains("LEFT")) {
            this.setX(x-1);
        }
        if(input.contains("RIGHT")) {
            this.setX(x+1);
        }
        if(input.contains("UP")) {
            this.setY(y-1);
        }
        if(input.contains("DOWN")) {
            this.setY(y+1);
        }
        rect.setX(x);
        rect.setY(y);}
        //rect.setVisible(false);
    }


}
