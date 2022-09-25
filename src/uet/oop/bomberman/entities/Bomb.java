package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import static uet.oop.bomberman.BombermanGame.bomb;
import static uet.oop.bomberman.BombermanGame.input;

public class Bomb extends Entity {
    public Bomb(double x,double y, Image img) {
        super(x, y, img);
    }
    public int t = 0;
    public void update() {
        }

}
