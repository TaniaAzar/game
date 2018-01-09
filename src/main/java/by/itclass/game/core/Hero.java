package by.itclass.game.core;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;

public class Hero implements Drawable, Updatable{

    private double x; // координата персонажа
    private double y;
    private BufferedImage image;//изображение персонажа

    private final double SWIM_MODIFIER = 0.2;

    private byte verticalMovement;
    private byte horizontalMovement;

    private int width;
    private int height;

    private GameMap gameMap;

    private float maxMovementSpeed;//максимальная скорость героя

    public Hero(BufferedImage image, double x, double y, float maxMovementSpeed, GameMap map){
        if(image == null){
            throw new IllegalArgumentException("Изображение отсутствует");
        }
        if(maxMovementSpeed < 0){
            throw new IllegalArgumentException("Скорость отсутствует");
        }
        if(map == null){
            throw new IllegalArgumentException("Карта отсутствует");
        }
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.maxMovementSpeed = maxMovementSpeed;
        this.gameMap = map;
    }

    public double getX() { return x; }

    public double getY() {
        return y;
    }

    public BufferedImage getImage() {
        return image;
    }

    private void move(double time){

        double nextX = this.x;
        double nextY = this.y;

        double centerX = this.x + width / 2;
        double centerY = this.y + height / 2;

        double speed = maxMovementSpeed;
        if (checkPointIsSwimable(centerX,centerY)){
            speed *= SWIM_MODIFIER;
        }

        double delta = speed * time;
        if (horizontalMovement == 0){
            switch (verticalMovement){
                case -1:
                    nextY = this.y - delta;
                    break;
                case 1:
                    nextY = this.y + delta;
                    break;
            }
        }else if (horizontalMovement == -1){
            switch (verticalMovement){
                case -1:
                    double diag = delta * Math.sqrt(2)/2;
                    nextX = this.x - diag;
                    nextY = this.y - diag;
                    break;
                case 1:
                    diag = delta * Math.sqrt(2)/2;
                    nextX = this.x - diag;
                    nextY = this.y + diag;
                    break;
                case 0:
                    nextX= this.x - delta;
                    break;
            }
        }else if (horizontalMovement == 1){
            switch (verticalMovement){
                case -1:
                    double diag = delta * Math.sqrt(2)/2;
                    nextX = this.x + diag;
                    nextY = this.y - diag;
                    break;
                case 1:
                    diag = delta * Math.sqrt(2)/2;
                    nextX = this.x + diag;
                    nextY = this.y + diag;
                    break;
                case 0:
                    nextX = this.x + delta;
                    break;
            }
        }
            double upY = nextY;
            double downY = nextY + height;
            double leftX = nextX;
            double rightX = nextX + width;

            if((checkPointInMap(leftX, upY) && (checkPointIsWalkable(leftX, upY) || checkPointIsSwimable(leftX, upY))) &&
                    (checkPointInMap(rightX, upY) && (checkPointIsWalkable(rightX, upY) || checkPointIsSwimable(rightX, upY))) &&
                    (checkPointInMap(leftX, downY) && (checkPointIsWalkable(leftX, downY) || checkPointIsSwimable(leftX, downY))) &&
                    (checkPointInMap(rightX, downY) && (checkPointIsWalkable(rightX, downY) || checkPointIsSwimable(rightX, downY)))){
                this.x = nextX;
                this.y = nextY;
            }
        }

    private boolean checkPointInMap(double x, double y){
        int j = (int)(x / gameMap.CELL_WIDTH);
        int i = (int)(y / gameMap.CELL_HEIGHT);

        if (i < 0 || j < 0){
            return false;
        }
        if (i >= gameMap.getWidth() || j >= gameMap.getHeight()){
            return false;
        }
        return true;
    }

    private boolean checkPointIsWalkable(double x, double y){
        int j = (int)(x / gameMap.CELL_WIDTH);
        int i = (int)(y / gameMap.CELL_HEIGHT);

        int type = gameMap.getCell(i, j).getType();
        return gameMap.getType(type).isWalkable();
    }

    private boolean checkPointIsSwimable(double x, double y) {
        int j = (int)(x / gameMap.CELL_WIDTH);
        int i = (int)(y / gameMap.CELL_HEIGHT);

        int type = gameMap.getCell(i, j).getType();
        return gameMap.getType(type).isSwimmable();
    }

    public void setVerticalMovement(byte verticalMovement) {
        this.verticalMovement = verticalMovement;
    }

    public void setHorizontalMovement(byte horizontalMovement) {
        this.horizontalMovement = horizontalMovement;
    }

    @Override
    public void draw(Graphics g, double deltaTime) {
        g.drawImage(image,(int)x, (int)y, null);
    }

    @Override
    public void update(double deltaTime) {
        move(deltaTime);
    }
}
