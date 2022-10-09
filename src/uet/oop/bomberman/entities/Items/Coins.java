package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.entities.Management.*;

public class Coins extends  Item {
    public Coins(int x, int y, Image img) {
        super(x, y, img);
    }

    public void update() {

        //if (x < 10000) {
            //animate += Sprite.DEFAULT_SIZE / 11;
            //this.img = Sprite.movingSprite(Sprite.coin_left1, Sprite.coin_left2, Sprite.coin_left3, animate / 2, Sprite.DEFAULT_SIZE).getFxImage();
           // if (animate == 200) animate = 1;
            if (this.intersects(bomberman)) {
                x = 10000;
            }
            if (this.intersects(bombergirl)) {
                x = 10000;
            }
        //}
    }
}

