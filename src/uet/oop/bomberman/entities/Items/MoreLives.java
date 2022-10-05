package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;

import static uet.oop.bomberman.entities.Management.bombergirl;
import static uet.oop.bomberman.entities.Management.bomberman;

public class MoreLives extends Item{
    public MoreLives(int x, int y, Image img) {
        super(x,y,img);
    }

    public void update() {
        if(this.intersects(bomberman)) {
            bomberman.numOfLives ++;
            System.out.println(bomberman.numOfLives);
            x = 1000;
        }
        if(this.intersects(bombergirl)) {
            bombergirl.numOfLives ++;
            System.out.println(bombergirl.numOfLives);
            x = 1000;
        }
    }
}
