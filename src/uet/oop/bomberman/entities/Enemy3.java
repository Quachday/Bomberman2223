package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.entities.Management.*;

public class Enemy3 extends Enemy2 {
    public Enemy3(int x, int y, Image img) {
        super( x, y, img);
    }
    public void update() {
        move();
    }
    void move() {
        if (getDirect()==1 ) {
            if(intersectsPlayer(bomberman) == true )
            { if (x != bomberman.getX()) x -= (this.x - bomberman.getX())/Math.abs(this.x - bomberman.getX());
                if (y != bomberman.getY()) y -= (this.y - bomberman.getY())/Math.abs(this.y - bomberman.getY()); }
            if(intersectsPlayer(bombergirl) == true )
            { if (x != bombergirl.getX())x -= (this.x - bombergirl.getX())/Math.abs(this.x - bombergirl.getX());
                if (y != bombergirl.getY()) y += (this.y - bombergirl.getY())/Math.abs(this.y - bombergirl.getY()); }
            x -= 1;
            state++;
            img = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2,Sprite.kondoria_left3, 10+state, 3 + state).getFxImage();
            if (state == 30) state = 1;
        }
        if (getDirect()==2 ) {
            if(intersectsPlayer(bomberman) == true )
            { if (x != bomberman.getX())x -= (this.x - bomberman.getX())/Math.abs(this.x - bomberman.getX());
                if (y != bomberman.getY())  y -= (this.y - bomberman.getY())/Math.abs(this.y - bomberman.getY()); }
            if(intersectsPlayer(bombergirl) == true )
            { if (x != bombergirl.getX()) x -= (this.x - bombergirl.getX())/Math.abs(this.x - bombergirl.getX());
                if (y != bombergirl.getY()) y += (this.y - bombergirl.getY())/Math.abs(this.y - bombergirl.getY()); }
            x +=1;
            state++;
            img = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2,Sprite.kondoria_right3, 10+state, 3 + state).getFxImage();
            if (state == 30) state = 1;
        }
    }

    @Override
    int getDirect() {
        if (x <= 32 || x >= Sprite.SCALED_SIZE * 31 - 64  )
        ranNum = ranNum == 1 ? 2 : 1;
        return ranNum;
    }
}
