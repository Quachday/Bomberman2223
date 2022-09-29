package uet.oop.bomberman.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Enemy1;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.entities.Management.*;


// ONEAL : đặc điểm : nếu nó thấy bạn, nó đi về phía bạn.
public class Enemy2 extends Enemy1 {
    protected int speed = 1;
    public Enemy2(int x, int y, Image img) {
    super(x,y,img);
    }


    public void update() {
    move();
    }







    // Check Player is arround
    public Rectangle2D getBoundarybyRow() {
        return new Rectangle2D(x-48 , y+1 , Sprite.SCALED_SIZE + 96 , Sprite.SCALED_SIZE - 2 );
    }
    public boolean intersectsPlayerbyRow(Entity s) {
        return this.getBoundarybyRow().intersects(s.getBoundary());
    }
    public Rectangle2D getBoundarybyColumn() {
        return new Rectangle2D(x+1 , y-32 , Sprite.SCALED_SIZE -2 , Sprite.SCALED_SIZE + 64);
    }
    public boolean intersectsPlayerbyColumn(Entity s) {
        return this.getBoundarybyColumn().intersects(s.getBoundary());
    }


    void move() {
        if(intersectsPlayerbyRow(bomberman)) {
            if (bomberman.getX() < this.x) {
                ranNum = 1;
                speed = 2;
            } else {
                ranNum = 2;
                speed = 2;

            }
        }
            else if (intersectsPlayerbyRow(bombergirl)) {
                if (bombergirl.getX() < this.x) { ranNum = 1;  }
                else { ranNum = 2;  }
            }
        /*else if(intersectsPlayerbyColumn(bomberman)) {
            if (bomberman.getY() < this.y) {
                ranNum = 4;
            } else {
                ranNum = 3;
            }
        }
        else if (intersectsPlayerbyColumn(bombergirl)) {
            if (bombergirl.getY() < this.y) { ranNum = 4;  }
            else { ranNum = 3;  }
        }*/
        else speed = 1;

        if (getDirect()==1) {
            x -= speed;
            state++;
            img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2,Sprite.oneal_left3, 10+state, 3 + state).getFxImage();
            if (state == 30) state = 1;
        }
        if (getDirect()==2 ) {
            x += speed;
            state++;
            img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2,Sprite.oneal_right3, 10+state, 3 + state).getFxImage();
            if (state == 30) state = 1;
        }
        if (getDirect()==3 ) {
            y += speed;
            state++;
            img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2,Sprite.oneal_left3, 10+state, 3 + state).getFxImage();
            if (state == 30) state = 1;
        }
        if (getDirect()== 4 ) {
            y -= speed;
            state++;
            img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2,Sprite.oneal_left3, 10+state, 3 + state).getFxImage();
            if (state == 30) state = 1;
        }
        if (this.intersects(bomberman)) bomberman.statusman = "die";
        if (this.intersects(bombergirl)) bombergirl.statusgirl = "die";
    }
}
