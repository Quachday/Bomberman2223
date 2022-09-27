package uet.oop.bomberman.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;


// ONEAL : đặc điểm : nếu nó thấy bạn, nó lao về phía bạn với vận tốc bàn thờ. Còn bạn đuổi theo phía sau nó thì nó cũng chạy đi mất.

public class Enemy2 extends Enemy1{
    protected int speed = 1;
    public Enemy2(int x, int y, Image img) {
    super(x,y,img);
    }


    public void update() {
    move();
    }

    // Check Player is arround
    public Rectangle2D getBoundaryBIG() {
        return new Rectangle2D(x-32 , y-32 , Sprite.SCALED_SIZE + 64 , Sprite.SCALED_SIZE+64 );
    }
    public boolean intersectsPlayer(Entity s) {
        return this.getBoundaryBIG().intersects(s.getBoundary());
    }


    public boolean checkPlayer() {
        for (Entity e : Management.bombers) {
            if (this.intersectsPlayer(e)) return true;
        }
        return false;
    }
    @Override
    void move() {
        if (getDirect()==1) {
            if (checkPlayer()) x-= speed;
            x -= speed;
            state++;
            img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2,Sprite.oneal_left3, 10+state, 3 + state).getFxImage();
            if (state == 30) state = 1;
        }
        if (getDirect()==2 ) {
            if (checkPlayer()) x+= speed ;
            x += speed;
            state++;
            img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2,Sprite.oneal_right3, 10+state, 3 + state).getFxImage();
            if (state == 30) state = 1;
        }
        if (getDirect()==3 ) {
            if (checkPlayer()) y += speed;
            y += speed;
            state++;
            img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2,Sprite.oneal_left3, 10+state, 3 + state).getFxImage();
            if (state == 30) state = 1;
        }
        if (getDirect()== 4 ) {
            if (checkPlayer()) y -= speed;
            y -= speed;
            state++;
            img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2,Sprite.oneal_left3, 10+state, 3 + state).getFxImage();
            if (state == 30) state = 1;
        }

    }
}
