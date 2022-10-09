package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;

import static uet.oop.bomberman.entities.Management.bombergirl;
import static uet.oop.bomberman.entities.Management.bomberman;

public class MoreLives extends Item{
    public MoreLives(int x, int y, Image img) {
        super(x,y,img);
    }

    public void update() {
        if(this.intersects(bomberman)) {
            BombermanGame.collectItem.play();
            bomberman.numOfLives ++;
            System.out.println(bomberman.numOfLives);
            x = 1000;
        }
        if(this.intersects(bombergirl)) {
            BombermanGame.collectItem.play();
            bombergirl.numOfLives ++;
            System.out.println(bombergirl.numOfLives);
            x = 1000;
        }
    }
}
