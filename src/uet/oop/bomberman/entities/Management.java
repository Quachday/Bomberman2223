package uet.oop.bomberman.entities;

import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Management {
    public static List<Bomber> bombers = new ArrayList<>();
    public static List<Entity> grasses = new ArrayList<>();
    public static List<Entity> walls = new ArrayList<>();
    public static List<Entity> portals = new ArrayList<>();
    public static List<Entity> bricks = new ArrayList<>();
    public static List<Enemy1> enemy = new ArrayList<>();
    public static List<Bomb> bombs = new ArrayList<>();
    public static Bomber bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
    public static Bombergirl bombergirl = new Bombergirl(1, 11, Sprite.player_right.getFxImage());

}
