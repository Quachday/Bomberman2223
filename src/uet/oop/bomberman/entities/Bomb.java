package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.io.FileNotFoundException;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;


import static uet.oop.bomberman.BombermanGame.input;
import static uet.oop.bomberman.entities.Management.*;

public class Bomb extends Wall {

    int countBOMB = 75;
    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }
    int state = 1;
    public void update() {
        state++;
        img = Sprite.movingSprite(Sprite.bomb,Sprite.bomb_1, Sprite.bomb_2, 15+state, 3 + state).getFxImage();
        if (state == 50) state = 1;
        countBOMB--;
        if(countBOMB > 0 && countBOMB < 20) {
            state++;
            img = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1,Sprite.bomb_exploded2, 15+state, 3 + state).getFxImage();
            if (state == 20) state = 1;
        }
        if (countBOMB==0) {
            Iterator<Bomb> bombIterator = bombs.iterator();
            while (bombIterator.hasNext()) {
                Bomb x = bombIterator.next();
                if (x.equals(this)) {
                    try{bombIterator.remove();}
                    catch (ConcurrentModificationException fnfe) {
                        System.out.println("Khong co bom");
                    }
                    System.out.println("\nThe element Orange is removed");
                    break;
                }
            }
        }
    }
}
