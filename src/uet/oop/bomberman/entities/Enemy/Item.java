package uet.oop.bomberman.entities.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

import static uet.oop.bomberman.entities.Management.bomberman;

public class Item extends Entity { // se lam abstract sau
    public Item(int x, int y, Image img) {
        super(x,y,img);
    }
    public void update() {
        if (this.intersects(bomberman)) bomberman.speed++;
    }


}
