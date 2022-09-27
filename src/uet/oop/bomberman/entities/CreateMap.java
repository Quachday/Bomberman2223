package uet.oop.bomberman.entities;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.util.Scanner;

public class CreateMap {
    /**
     * tao map theo cac level bang cach doc file txt.
     * @param level
     */
    public static void createMapByLevel(int level) {
        //Management.clear();
        Management.bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        Management.bombers.add(Management.bomberman);
        try {
            String path = "res/levels/Level" + level + ".txt";
            File file = new File(path);
            Scanner sc = new Scanner(file);
//            sc.nextInt();
            level = sc.nextInt();
            BombermanGame.HEIGHT = sc.nextInt();
            BombermanGame.WIDTH = sc.nextInt();


            char[][] maps = new char[BombermanGame.HEIGHT][BombermanGame.WIDTH];
            sc.nextLine();
            for (int i = 0; i < BombermanGame.HEIGHT; ++i) {
                String line = sc.nextLine();
                for (int j = 0; j < BombermanGame.WIDTH; ++j) {
                    maps[i][j] = line.charAt(j);
                }
            }

            for (int i = 0; i < BombermanGame.WIDTH; ++i) {
                for (int j = 0; j < BombermanGame.HEIGHT; ++j) {
                    if (j == 0 || j == BombermanGame.HEIGHT - 1 || i == 0 || i == BombermanGame.WIDTH - 1 || maps[j][i] == '#') {
                        Management.walls.add(new Wall(i, j, Sprite.wall.getFxImage()));
                    } else {
                        Management.grasses.add(new Grass(i, j, Sprite.grass.getFxImage()));
                    }
                }
            }
            sc.close();
        } catch (Exception exception) {
            System.out.println("Error: " + exception);
        }
    }
}
