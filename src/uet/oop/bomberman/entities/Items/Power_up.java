package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;

import static uet.oop.bomberman.entities.Management.bombergirl;
import static uet.oop.bomberman.entities.Management.bomberman;

public class Power_up extends Item {
    public Power_up(int x, int y, Image img) {
        super(x,y,img);
    }

    public void update() {
        if(this.intersects(bomberman)) {
            bomberman.sizeOfFlame++;
            x = 1000;
        }
        if(this.intersects(bombergirl)) {
            bomberman.sizeOfFlame++;
            x = 1000;
        }
    }
}
