package uet.oop.bomberman;

import com.sun.javafx.font.directwrite.RECT;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static uet.oop.bomberman.entities.Bomb.bomb;


public class BombermanGame extends Application {
    // day la file cua quach


    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    private GraphicsContext gc;
    private Canvas canvas;
    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();


    public static ArrayList<String> input = new ArrayList<String>();
    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();

                        // only add once... prevent duplicates
                        if (!input.contains(code))
                            input.add(code);
                    }
                });
        scene.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        input.remove(code);
                    }
                });


        Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);
        Entity spiderman = new Spiderman(1, 11, Sprite.spider_right.getFxImage());
        entities.add(spiderman);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime ) {

                update();
                render();

            }
        };
        timer.start();

        createMap();
    }


    public void createMap() {
        try {
            FileReader map = new FileReader("C:\\Users\\LTC\\Desktop\\Bomberman2223\\res\\levels\\Level1.txt");
            Scanner sc = new Scanner(map);
            int level = sc.nextInt();
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
                            break;
                        case '*':
                            object = new Wall(j, i, Sprite.brick.getFxImage()); // can tao lop Brick
                            break;
                        case '1':
                            Entity enemy1 = new Enemy1(j,i,Sprite.balloom_left1.getFxImage());
                            entities.add(enemy1);
                            object = new Grass(j, i, Sprite.grass.getFxImage()); // Can tao lop Balloom
                            break;
                        case '2' :
                            Entity moveGate = new Wall(j,i,Sprite.portal.getFxImage());
                            entities.add(moveGate);
                            object = new Grass(j, i, Sprite.grass.getFxImage()); // Can tao lop Balloom
                            break;
                        case '3' :
                            object = new Grass(j, i, Sprite.sign1.getFxImage());
                            break;
                        case '4' :
                            object = new Grass(j, i, Sprite.sign2.getFxImage());
                            break;
                        default:
                            object = new Grass(j, i, Sprite.grass.getFxImage());
                            break;
                    }
                    stillObjects.add(object);


                }
            }
        }
        catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }

        } // create map xong.- chua day du



    public void update() {
        entities.forEach(Entity::update);
        bomb.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        bomb.forEach(g -> g.render(gc));
    }
}
