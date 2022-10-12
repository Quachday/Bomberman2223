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
            Bomb newBomb = new Bomb(1000,1000, Sprite.bomb.getFxImage());
            bomberman.bombs.add(newBomb);
            items.remove(this);
        }
        if(this.intersects(bombergirl)) {
            BombermanGame.collectItem.play();
            Bomb newBomb = new Bomb(1000,1000, Sprite.bomb.getFxImage());
            bombergirl.bombs.add(newBomb);
            items.remove(this);
        }
    }
}
