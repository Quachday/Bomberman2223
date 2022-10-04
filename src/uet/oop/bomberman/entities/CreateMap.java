package uet.oop.bomberman.entities;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.BombermanGame;

import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static uet.oop.bomberman.BombermanGame.entities;
import static uet.oop.bomberman.BombermanGame.stillObjects;
import static uet.oop.bomberman.entities.Management.bombergirl;
import static uet.oop.bomberman.entities.Management.bombs;

public class CreateMap {
    /**
     * tao map theo cac level bang cach doc file txt.
     * @param level
     */
    public static void createMapByLevel(int level, int numOfPlayer) {
        //Management.clear();
        Management.bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        Management.bombers.add(Management.bomberman);
        if (numOfPlayer == 2) {
            bombergirl = new Bombergirl(1, 11, Sprite.spider_right.getFxImage());
            Management.bombers.add(bombergirl);
        }
        try {
            String path = "res/levels/Level" + level + ".txt";
            FileReader file = new FileReader ("res/levels/Level1.txt");
                Scanner sc = new Scanner(file);
                level = sc.nextInt();
                int rows = sc.nextInt();
                int columns = sc.nextInt();
                String first = sc.nextLine();

                for (int i = 0; i < rows; i++) {
                    String s = sc.nextLine();
                    for (int j = 0; j < columns; j++) {
                        Entity object;
                        switch (s.charAt(j)) {
                            case '#':
                                object = new Wall(j, i, Sprite.wall.getFxImage());
                                Management.walls.add(object);
                                break;
                            case '*':
                                object = new Brick(j, i, Sprite.brick.getFxImage()); // can tao lop Brick
                                Management.bricks.add(object);
                                break;
                            case '1':
                                Enemy1 enemy1 = new Enemy1(j,i,Sprite.balloom_left1.getFxImage());
                                Management.enemy.add(enemy1);
                                object = new Grass(j, i, Sprite.grass.getFxImage()); // Can tao lop Balloom
                                break;
                            case '2' :
                                Entity moveGate = new Grass(j,i,Sprite.portal.getFxImage());
                                object = new Grass(j, i, Sprite.grass.getFxImage());
                                Management.grasses.add(moveGate);
                                Management.portals.add(moveGate);
                                break;
                            case '3' :
                                object = new Grass(j, i, Sprite.sign1.getFxImage());
                                Management.grasses.add(object);
                                break;
                            case '4' :
                                object = new Grass(j, i, Sprite.sign2.getFxImage());
                                Management.grasses.add(object);
                                break;
                            case '5' :
                                Enemy2 enemy2 = new Enemy2(j,i,Sprite.oneal_right1.getFxImage());
                                Management.enemy.add(enemy2);
                                object = new Grass(j,i,Sprite.grass.getFxImage());
                                break;
                            case '6' :
                                Enemy3 enemy3 = new Enemy3(j,i,Sprite.kondoria_left1.getFxImage());
                                Management.enemy.add(enemy3);
                                object = new Grass(j,i,Sprite.grass.getFxImage());
                                break;
                            case '7' :
                                Enemy4 enemy4 = new Enemy4(j,i,Sprite.minvo_left1.getFxImage());
                                Management.enemy.add(enemy4);
                                object = new Grass(j,i,Sprite.grass.getFxImage());
                                break;
                            default:
                                object = new Grass(j, i, Sprite.grass.getFxImage());
                                Management.grasses.add(object);
                                break;
                        }
                        stillObjects.add(object);
                    }
                }
            }
            catch (FileNotFoundException fnfe) {
                fnfe.printStackTrace();
            }
        Bomb firstBomb = new Bomb(1000,1000,Sprite.bomb.getFxImage());
        bombs.add(firstBomb);
    }
}
