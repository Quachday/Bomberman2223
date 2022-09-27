package uet.oop.bomberman;

import com.sun.javafx.font.directwrite.RECT;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;


import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.List;

import javafx.scene.paint.Color;
import static uet.oop.bomberman.entities.Bomb.bomb;


public class BombermanGame extends Application {
    // day la file cua quach


    public static int WIDTH = 31;
    public static int HEIGHT = 13;
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
        stage.setTitle("BOMBERMAN GAME");
        welcomeGame(scene);
        stage.show();



        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        /**
         * dieu khien di chuyen bomber.
         */
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

    }
    public void welcomeGame(Scene scene) {

        Image background = new Image("C:\\Users\\LTC\\Desktop\\Bomberman2223\\res\\background.jpg"); // luc chay thi doi dia chi nay
        gc.drawImage(background,0,0,Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        scene.setOnMouseClicked(Mouseevent);
    }

       EventHandler Mouseevent = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            if (e.getX() >= 515 && e.getX() <= 790 && e.getY() >= 315 && e.getY() <= 350)
            {   gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                CreateMap.createMapByLevel(1,2);}
            if (e.getX() >= 175 && e.getX() <= 475 && e.getY() >= 315 && e.getY() <= 350)
            {gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); CreateMap.createMapByLevel(1,1);}
            if (e.getX() >= 875 && e.getX() <= 975 && e.getY() >= 338 && e.getY() <= 413)
                System.exit(0);
        }
    };

    public void update() {
        Management.bombers.forEach(Entity::update);
        Management.ballom.forEach(Entity::update);

    }

    public void render() {
       // gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        /*Management.grasses.forEach(grass -> grass.render(gc));

        Management.grasses.forEach(grass -> grass.render(gc));
        Management.walls.forEach(wall -> wall.render(gc));
        Management.bricks.forEach(g -> g.render(gc));*/

        entities.forEach(g -> g.render(gc));
        stillObjects.forEach(g -> g.render(gc));
        Management.bombers.forEach(bomber -> bomber.render(gc));
        Management.portals.forEach(portal -> portal.render(gc));
        Management.ballom.forEach(g -> g.render(gc));

    }
}
