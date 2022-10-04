package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;

import static uet.oop.bomberman.entities.Management.bomberman;

public class Speedup extends Item{
    public Speedup(int x, int y, Image img) {
        super(x,y,img);
    }
    public void update() {
        if(this.intersects(bomberman)) {
            bomberman.speed++;
            x = 1000;
        }
    }
}
