package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.LinkedList;
import java.util.Queue;


import static uet.oop.bomberman.BombermanGame.input;

public class Bomb extends Entity {
    public static Queue<Entity> bomb = new LinkedList<>();
    private int numOfBomb = 1;

    private int countBomb = 5;

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }

    public void setNumOfBomb(int numOfBomb) {
        this.numOfBomb = numOfBomb;
    }

    public void update() {

    }
}
