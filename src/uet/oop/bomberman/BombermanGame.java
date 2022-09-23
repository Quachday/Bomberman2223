package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class BombermanGame extends Application {
    // day la file cua quach
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    private  double[][] arr = new double[13][31];
    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();

    private ArrayList<String> input = new ArrayList<String>();
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
        final long startNanoTime = System.nanoTime();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 100000.0;
                int posX = (int)bomberman.getX()/32;
                int posY = (int)bomberman.getY()/32;
                if(input.contains("LEFT")) {
                    if(arr[posY][posX-1] == 0
                    || arr[posY][posX-1] == 1 && bomberman.getX()-1 >= posX*32)  bomberman.setX(bomberman.getX()-1);
                }
                if(input.contains("RIGHT")) {
                    if(arr[posY][posX+1] == 0
                            || arr[posY][posX+1] == 1 && bomberman.getX()+1 <= (posX+1)*32)  bomberman.setX(bomberman.getX()+1);
                }
                if(input.contains("UP")) {
                    bomberman.setY(bomberman.getY()-1);
                }
                if(input.contains("DOWN")) {
                    bomberman.setY(bomberman.getY()+1);
                }
                update();
                render();
            }
        };
        timer.start();

        createMap();
        for (int i = 1; i < (Sprite.SCALED_SIZE * WIDTH)/32; i++) {
            Line line = new Line();
            line.setStartX(32*i);
            line.setStartY(0);
            line.setEndX(32*i);
            line.setEndY(Sprite.SCALED_SIZE*HEIGHT);
            root.getChildren().add(line); }
        for (int i = 1; i < (Sprite.SCALED_SIZE * HEIGHT)/32; i++) {
            Line line = new Line();
            line.setStartX(0);
            line.setStartY(32*i);
            line.setEndX(Sprite.SCALED_SIZE*WIDTH);
            line.setEndY(32*i);
            root.getChildren().add(line); }


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
                            arr[i][j] = 1;
                            break;
                        case '*':
                            object = new Wall(j, i, Sprite.brick.getFxImage()); // can tao lop Brick
                            arr[i][j] = 1;
                            break;
                        case '1':
                            object = new Wall(j, i, Sprite.balloom_left1.getFxImage()); // Can tao lop Balloom
                            arr[i][j] = 1;
                            break;
                        default:
                            object = new Grass(j, i, Sprite.grass.getFxImage());
                            arr[i][j] = 0;
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
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
