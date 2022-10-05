package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.entities.Management.*;

public class MoreBomb extends Item{
    public MoreBomb(int x, int y, Image img) {
        super(x,y,img);
    }
    public void update() {
        if(this.intersects(bomberman)) {
            Bomb newBomb = new Bomb(1000,1000, Sprite.bomb.getFxImage(),1);
            bombsofman.add(newBomb);
            x = 1000;
        }
        if(this.intersects(bombergirl)) {
            Bomb newBomb = new Bomb(1000,1000, Sprite.bomb.getFxImage(),1);
            bombsofgirl.add(newBomb);
            x = 1000;
        }
    }
}
