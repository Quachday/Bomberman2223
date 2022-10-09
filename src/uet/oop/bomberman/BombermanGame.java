package uet.oop.bomberman;

import com.sun.javafx.font.directwrite.RECT;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.EventType;
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
import static uet.oop.bomberman.entities.CreateMap.numOfEnemy;
import static uet.oop.bomberman.entities.CreateMap.numOfplayer;
import static uet.oop.bomberman.entities.Management.*;


public class BombermanGame extends Application {
    public boolean start = false;
    // day la file cua quach

    public static int WIDTH = 31;
    public static int HEIGHT = 13;
    private GraphicsContext gc;
    private Canvas canvas;
    public static Group root = new Group();
    public static List<Entity> entities = new ArrayList<>();
     public static List<Entity> stillObjects = new ArrayList<>();

    public static Scene scene;
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
        root.getChildren().add(canvas);

        // Tao scene
        scene = new Scene(root);
        // Them scene vao stage
        stage.setScene(scene);
        stage.setTitle("BOMBERMAN GAME");
        welcomeGame(scene);
        stage.show();



        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (bomberman.numOfLives == 0 && numOfplayer == 1) System.out.println("you lose");
                else if (bomberman.numOfLives == 0 && numOfplayer == 2 && bombergirl.numOfLives == 0) System.out.println("you lose");
                if (numOfEnemy == 0 ) {
                    System.out.println("you win");
                    System.exit(0);
                }
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
                        if (e.getCode().toString().equals("SPACE") && !bombsofman.get(bomberman.indexOfBombs).settled ) { // HAM DAT BOM
                           boolean checkduplicate = false;
                            bombsofman.get(bomberman.indexOfBombs).setX((int)(bomberman.getX()+5)/32*32);
                            bombsofman.get(bomberman.indexOfBombs).setY((int)(bomberman.getY()+16)/32*32);
                            for (int i = 0; i < bombsofman.size() ; i++){
                                if ( bomberman.indexOfBombs != i &&
                                        bombsofman.get(bomberman.indexOfBombs).getX() == bombsofman.get(i).getX()
                                        && bombsofman.get(bomberman.indexOfBombs).getY() == bombsofman.get(i).getY() ) {
                                    checkduplicate = true; break;
                                }
                            }
                            if (checkduplicate == true) {
                                Random rand = new Random();
                                int ranNum = rand.nextInt(2) + 1000;
                                bombsofman.get(bomberman.indexOfBombs).setX(ranNum);
                            }
                            else if (checkduplicate == false) {
                                bombsofman.get(bomberman.indexOfBombs).settled = true;
                            }
                            {if (bomberman.indexOfBombs == bombsofman.size()-1) bomberman.indexOfBombs = 0;
                            else bomberman.indexOfBombs++;}
                        }


                        if (e.getCode().toString().equals("ENTER") && !bombsofgirl.get(bombergirl.indexOfBombs).settled ) { // HAM DAT BOM
                            boolean checkduplicate = false;
                            bombsofgirl.get(bombergirl.indexOfBombs).setX((int)(bombergirl.getX()+5)/32*32);
                            bombsofgirl.get(bombergirl.indexOfBombs).setY((int)(bombergirl.getY()+16)/32*32);
                            for (int i = 0; i < bombsofgirl.size() ; i++){
                                if ( bombergirl.indexOfBombs != i &&
                                        bombsofgirl.get(bombergirl.indexOfBombs).getX() == bombsofgirl.get(i).getX()
                                        && bombsofgirl.get(bombergirl.indexOfBombs).getY() == bombsofgirl.get(i).getY() ) {
                                    checkduplicate = true; break;
                                }
                            }
                            if (checkduplicate == true) {
                                Random rand = new Random();
                                int ranNum = rand.nextInt(2) + 1000;
                                bombsofgirl.get(bombergirl.indexOfBombs).setX(ranNum);
                            }
                            else if (checkduplicate == false) {
                                bombsofgirl.get(bombergirl.indexOfBombs).settled = true;
                            }
                            {if (bombergirl.indexOfBombs == bombsofgirl.size()-1) bombergirl.indexOfBombs = 0;
                            else bombergirl.indexOfBombs++;}
                        }
                        // only add once... prevent duplicates
                        if (!input.contains(code) && !code.equals("SPACE") && !code.equals("ENTER"))
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
                CreateMap.createMapByLevel(2,2);
            start = true;
            }
            if (e.getX() >= 175 && e.getX() <= 475 && e.getY() >= 315 && e.getY() <= 350)
            {gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); CreateMap.createMapByLevel(1,1);
                start = true;
            }
            if (e.getX() >= 875 && e.getX() <= 975 && e.getY() >= 338 && e.getY() <= 413)
                System.exit(0);
        }
    };

    public void update() {
        Management.bombers.forEach(Entity::update);
        Management.enemy.forEach(Entity::update);
        Management.bombsofman.forEach(Entity::update);
        bombsofgirl.forEach(Entity::update);
        Management.items.forEach(Entity::update);
        bricks.forEach(Entity::update);
        flamesvisual.forEach(Entity::update);
        grasses.forEach(Entity::update);
    }

    public void render() {
        if (start) gc.clearRect(0,0, canvas.getWidth(),canvas.getHeight());
        Management.grasses.forEach(grass -> grass.render(gc));
        Management.items.forEach(g->g.render(gc));
        Management.bricks.forEach(g -> g.render(gc));
        Management.walls.forEach(wall -> wall.render(gc));
        Management.bombers.forEach(g -> g.render(gc));
        Management.portals.forEach(portal -> portal.render(gc));
        Management.enemy.forEach(g -> g.render(gc));
        Management.bombsofman.forEach(g -> g.render(gc));
        bombsofgirl.forEach(g -> g.render(gc));
        flamesvisual.forEach(g->g.render(gc));
    }
}
