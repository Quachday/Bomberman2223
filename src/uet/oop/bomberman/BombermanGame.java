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
import uet.oop.bomberman.entities.Enemies.Enemy1;
import uet.oop.bomberman.entities.Items.Item;
import uet.oop.bomberman.graphics.Sprite;


import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.List;

import javafx.scene.paint.Color;
import uet.oop.bomberman.sound.Sound;


import static uet.oop.bomberman.entities.CreateMap.*;
import static uet.oop.bomberman.entities.Management.*;


public class BombermanGame extends Application {
    // day la file cua dat1


    public static int WIDTH = 31;
    public static int HEIGHT = 13;
    public static GraphicsContext gc;
    private Canvas canvas;
    public static List<Entity> entities = new ArrayList<>();
     public static List<Entity> stillObjects = new ArrayList<>();

    public static Scene scene;
    public Sound themeSong = new Sound("nhacnen");
    public Sound gameStart = new Sound("gameStart");
    public Sound boomSettle = new Sound("boomSettle");
    public static Sound enemyDie = new Sound("enemydie");

    public static Sound bomberDie = new Sound("bomberdie");
    public static Sound boomExplosion = new Sound("boomExplosion");

    public static Sound collectItem = new Sound("Item");
    public static Group root = new Group();
    boolean started = false;
    public static ArrayList<String> input = new ArrayList<String>();
    int levelnow;
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
        gc.fillRect(400,350,100,32);
        //ok



        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                //if (bomberman.numOfLives == 0 && numOfplayer == 1) System.out.println("you lose");
               // else if (bomberman.numOfLives == 0 && numOfplayer == 2 && bombergirl.numOfLives == 0) System.out.println("you lose");
                render();
                update();
                if (numOfEnemy == 0 ) {
                    System.out.println("boy win");
                    System.exit(0);
                    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                }
                if (levelnow == 2 && started && coinsStack.size() == 0 || bomberman.numOfLives == 0) {
                    System.out.println("girl win");
                    System.exit(0);
                }
            }
        };

        themeSong.loop();
        timer.start();

        /**
         * dieu khien di chuyen bomber.
         */
        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();

                        if (e.getCode().toString().equals("SPACE")  ){
                            // HAM DAT BOM
                            boomSettle.play();
                            bomberman.putBomb();
                        }

                        if (e.getCode().toString().equals("ENTER") && !bombsofgirl.get(bombergirl.indexOfBombs).settled ) { // HAM DAT BOM
                            boomSettle.play();
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

        Image background = new Image("background.jpg"); // luc chay thi doi dia chi nay
        gc.drawImage(background,0,0,Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        scene.setOnMouseClicked(Mouseevent);

        //Sound.play("gameStart");
    }

       EventHandler Mouseevent = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            if (e.getX() >= 515 && e.getX() <= 790 && e.getY() >= 315 && e.getY() <= 350)
            {   gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                CreateMap.createMapByLevel(2,2);
                themeSong.stop();
                gameStart.play();
                started = true;
                levelnow = 2;
            }
            if (e.getX() >= 175 && e.getX() <= 475 && e.getY() >= 315 && e.getY() <= 350)
            {
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                CreateMap.createMapByLevel(1,1);
                themeSong.stop();
                gameStart.play();
                started = true;
                levelnow = 1;
            }
            if (e.getX() >= 875 && e.getX() <= 975 && e.getY() >= 338 && e.getY() <= 413)
                System.exit(0);
        };
    };

    public void update() {
        Management.bombers.forEach(Entity::update);
        List<Bomb> another = new ArrayList<>();
        for (int i = 0; i < bomberman.bombs.size(); i++) {
            another.add(bomberman.bombs.get(i));
        }
        another.forEach(Bomb::update);
        List<Enemy1> oneother = new ArrayList<>();
        for (int i = 0; i < enemy.size(); i++) {
            oneother.add(enemy.get(i));
        }
        oneother.forEach(Enemy1::update);
        bombsofgirl.forEach(Entity::update);
        List<Item> other = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            other.add(items.get(i));
        }
        other.forEach(Item::update);
        List<Entity> twoother = new ArrayList<>();
        for (int i = 0; i < bricks.size(); i++) {
            twoother.add(bricks.get(i));
        }
        twoother.forEach(Entity::update);

        flamesvisual.forEach(Entity::update);
    }

    public void render() {
       // gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        /*Management.grasses.forEach(grass -> grass.render(gc));

        Management.grasses.forEach(grass -> grass.render(gc));
        Management.walls.forEach(wall -> wall.render(gc));
        Management.bricks.forEach(g -> g.render(gc));*/
        //gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        //stillObjects.forEach(g -> g.render(gc));
        //entities.forEach(g -> g.render(gc));
        Management.grasses.forEach(grass -> grass.render(gc));
        Management.items.forEach(g->g.render(gc));
        Management.bricks.forEach(g -> g.render(gc));
        Management.walls.forEach(wall -> wall.render(gc));
        Management.bombers.forEach(g -> g.render(gc));
        Management.portals.forEach(portal -> portal.render(gc));
        Management.enemy.forEach(g -> g.render(gc));
        Management.bombsofman.forEach(g -> g.render(gc));
        Management.bomberman.bombs.forEach(g -> g.render(gc));
        bombsofgirl.forEach(g -> g.render(gc));
        flamesvisual.forEach(g->g.render(gc));
    }
}
