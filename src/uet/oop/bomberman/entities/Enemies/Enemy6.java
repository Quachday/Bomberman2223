package uet.oop.bomberman.entities.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.entities.CreateMap.numOfEnemy;
import static uet.oop.bomberman.entities.Management.bombergirl;
import static uet.oop.bomberman.entities.Management.bomberman;

public class Enemy6 extends Enemy1{

    public Enemy6(int x, int y, Image img) {
        super(x, y, img);
    }

        public void update() {
            if (this.status.equals("alive")) move();
            else if (this.status.equals("die")) onDie();
        }
        //Thuat toan tim duong ?




    @Override
    public void onDie() {
        if (this.count_die > 50) { img = Sprite.minvo_dead.getFxImage(); count_die--; }
        else super.onDie();
    }
    void move() {  // duoi day la di chuyen random
        if (getDirect()==1 ) {
            x -= 1;
            animate += Sprite.DEFAULT_SIZE/10;
            img = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2,Sprite.doll_left3, animate, Sprite.DEFAULT_SIZE).getFxImage();
        }
        if (getDirect()==2 ) {
            x += 1;
            animate += Sprite.DEFAULT_SIZE/10;
            img = Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2,Sprite.doll_right3, animate, Sprite.DEFAULT_SIZE).getFxImage();
        }
        if (getDirect()==3 ) {
            y += 1;
            animate += Sprite.DEFAULT_SIZE/10;
            img = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2,Sprite.doll_left3, animate, Sprite.DEFAULT_SIZE).getFxImage();
        }
        if (getDirect()== 4 ) {
            y -= 1;
            animate += Sprite.DEFAULT_SIZE/10;
            img = Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2,Sprite.doll_right3, animate, Sprite.DEFAULT_SIZE).getFxImage();
        }
        if (this.intersects(bomberman)) bomberman.status = "die";
        if (this.intersects(bombergirl)) bombergirl.status = "die";
    }
}
