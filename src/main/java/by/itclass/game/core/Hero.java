package by.itclass.game.core;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;

public class Hero implements Drawable, Updatable{

    private HeroType heroType;
    private double x; // координата персонажа
    private double y;
    private byte verticalMovement;
    private byte horizontalMovement;

    private GameMap gameMap;

    public Hero(HeroType heroType, double x, double y, GameMap map){
        if(heroType == null){
            throw new IllegalArgumentException("Отсутствует тип героя");
        }
        if(map == null){
            throw new IllegalArgumentException("Карта отсутствует");
        }
        this.heroType = heroType;
        this.x = x;
        this.y = y;
        this.gameMap = map;
    }

    private void move(double time){

        double nextX = this.x;
        double nextY = this.y;

        double centerX = this.x + heroType.getWidth() / 2;
        double centerY = this.y + heroType.getHeight() / 2;

        double speed = heroType.getMaxMovementSpeed();
        if (checkPointIsSwimable(centerX,centerY)){
            speed *= heroType.getSwimFactor();
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
            double downY = nextY + heroType.getHeight();
            double leftX = nextX;
            double rightX = nextX + heroType.getWidth();

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

        return i >= 0 && j >= 0 && i < gameMap.getWidth() && j < gameMap.getHeight();
    }

    private boolean checkPointIsWalkable(double x, double y){
        int j = (int)(x / gameMap.CELL_WIDTH);
        int i = (int)(y / gameMap.CELL_HEIGHT);

        int type = gameMap.getCell(i, j).getType();
        return gameMap.getType(type).isSwimmable() && heroType.isCanSwim();
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
        g.drawImage(heroType.getImage(),(int)x, (int)y, null);
    }

    @Override
    public void update(double deltaTime) {
        move(deltaTime);
    }
}
