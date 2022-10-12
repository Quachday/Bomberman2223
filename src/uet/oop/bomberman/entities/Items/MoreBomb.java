package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.tiles.Bomb;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.entities.createGame.Management.*;

public class MoreBomb extends Item{
    public MoreBomb(int x, int y, Image img) {
        super(x,y,img);
    }
    public void update() {
        if(this.intersects(bomberman)) {
            BombermanGame.collectItem.play();
            bomberman.numBombs++;
            items.remove(this);
        }
        if(this.intersects(bombergirl)) {
            BombermanGame.collectItem.play();
            bombergirl.numBombs++;
            items.remove(this);
        }
    }
}
