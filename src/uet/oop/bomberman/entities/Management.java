package uet.oop.bomberman.entities;

import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Management {
    public static List<Bomber> bombers = new ArrayList<>();
    public static List<Entity> grasses = new ArrayList<>();
    public static List<Entity> walls = new ArrayList<>();
    public static List<Entity> portals = new ArrayList<>();
    public static Bomber bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());

}
