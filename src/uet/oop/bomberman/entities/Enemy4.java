package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Enemy1;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.entities.Management.bombergirl;
import static uet.oop.bomberman.entities.Management.bomberman;

public class Enemy4 extends Enemy1 {
    public Enemy4(int x, int y, Image img) {
        super( x, y, img);
    }
    public void update() {
        move();
    }

    void move() {
        if (getDirect()==1 ) {
            x -= 1;
            state++;
            img = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2,Sprite.minvo_left3, 10+state, 3 + state).getFxImage();
            if (state == 30) state = 1;
        }
        if (getDirect()==2 ) {
            x +=1;
            state++;
            img = Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2,Sprite.minvo_right3, 10+state, 3 + state).getFxImage();
            if (state == 30) state = 1;
        }
        if (getDirect()==3 ) {
            y +=1;
            state++;
            img = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2,Sprite.minvo_left3, 10+state, 3 + state).getFxImage();
            if (state == 30) state = 1;
        }
        if (getDirect()== 4 ) {
            y -=1;
            state++;
            img = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2,Sprite.minvo_left3, 10+state, 3 + state).getFxImage();
            if (state == 30) state = 1;
        }
        if (this.intersects(bomberman)) bomberman.statusman = "die";
        if (this.intersects(bombergirl)) bombergirl.statusgirl = "die";
    }
}
