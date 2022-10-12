package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static java.awt.Color.RED;
import static java.awt.SystemColor.window;
import static uet.oop.bomberman.entities.Management.bomberman;


public class Grass extends Entity {
    public boolean isCovered = true;
    public double gCost, hCost, fCost;





    public Grass(int x, int y, Image img, boolean isCovered) {
        super(x, y, img); this.isCovered = isCovered;
    }

    @Override
    public void update() {

    }

}
