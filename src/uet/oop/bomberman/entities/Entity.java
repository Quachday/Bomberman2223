package uet.oop.bomberman.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.graphics.Sprite;


public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected Image img;

    public Rectangle rect;

    protected int animate;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
        rect = new Rectangle(x,y,32,32);
        rect.setVisible(false);
        this.animate =  this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);

    }
    public abstract void update();

    public Rectangle2D getBoundary() {
        return new Rectangle2D(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    }

    public boolean intersects(Entity s) {
        return s.getBoundary().intersects(this.getBoundary());
    }

    public boolean checkBounds() {
        for (Entity e : Management.walls) {
            if (this.intersects(e)) return true;
        }

        return false;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    /**
     * check va cham voi bom.
     */
    /*public boolean checkBomb() {
        for (Entity e : Management.bomberman.bombs) {
            if (this.intersects(e)) return true;
        }
        return false;
    }*/

    /**
     * check va cham voi tuong.
     */
    public boolean checkWall() {
        for (Entity e : Management.walls) {
            if (this.intersects(e)) return true;
        }
        for (Entity e : Management.bricks) {
            if (this.intersects(e)) return true;
        }
        return false;
    }

}
