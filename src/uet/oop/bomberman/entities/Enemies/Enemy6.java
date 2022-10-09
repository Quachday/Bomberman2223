package uet.oop.bomberman.entities.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.entities.CreateMap.numOfEnemy;
import static uet.oop.bomberman.entities.Management.*;

public class Enemy6 extends Enemy1{
    int speed = 1;
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
        speed = 5;
        if (numOfEnemy == 1) {
        if (count_die > 20)
        {img = Sprite.doll_dead.getFxImage(); count_die--;}
        else super.onDie(); }
        else status = "alive";
    }
    void move() {  // duoi day la di chuyen random

        if (getDirect()==1 ) {
            x -= speed;
            animate += Sprite.DEFAULT_SIZE/10;
            img = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2,Sprite.doll_left3, animate, Sprite.DEFAULT_SIZE).getFxImage();
        }
        if (getDirect()==2 ) {
            x += speed;
            animate += Sprite.DEFAULT_SIZE/10;
            img = Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2,Sprite.doll_right3, animate, Sprite.DEFAULT_SIZE).getFxImage();
        }
        if (getDirect()==3 ) {
            y += speed;
            animate += Sprite.DEFAULT_SIZE/10;
            img = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2,Sprite.doll_left3, animate, Sprite.DEFAULT_SIZE).getFxImage();
        }
        if (getDirect()== 4 ) {
            y -= speed;
            animate += Sprite.DEFAULT_SIZE/10;
            img = Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2,Sprite.doll_right3, animate, Sprite.DEFAULT_SIZE).getFxImage();
        }
        if (this.intersects(bomberman)) bomberman.status = "die";
        if (this.intersects(bombergirl)) bombergirl.status = "die";
    }

}
