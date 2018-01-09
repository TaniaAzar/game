package by.itclass.game.core;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;

public class Hero {

    private double x; // координата персонажа
    private double y;
    private BufferedImage image;//изображение персонажа

    private byte verticalMovement;
    private byte horizontalMovement;

    private float maxMovementSpeed;//максимальная скорость героя

    public Hero(BufferedImage image, double x, double y, float maxMovementSpeed){
        if(image == null){
            throw new IllegalArgumentException("Изображение отсутствует");
        }
        this.image = image;
        this.x = x;
        this.y = y;
        this.maxMovementSpeed = maxMovementSpeed;
    }

    public double getX() { return x; }

    public double getY() {
        return y;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void move(double time){

        double delta = maxMovementSpeed * time;

        if (horizontalMovement == 0){
            switch (verticalMovement){
                case -1:
                    this.y -= delta;
                    break;
                case 1:
                    this.y += delta;
                    break;
            }
        }else if (horizontalMovement == -1){
            switch (verticalMovement){
                case -1:
                    double diag = delta * Math.sqrt(2)/2;
                    this.x -= diag;
                    this.y -= diag;
                    break;
                case 1:
                    diag = delta * Math.sqrt(2)/2;
                    this.x -= diag;
                    this.y += diag;
                    break;
                case 0:
                    this.x -= delta;
                    break;
            }
        }else if (horizontalMovement == 1){
            switch (verticalMovement){
                case -1:
                    double diag = delta * Math.sqrt(2)/2;
                    this.x += diag;
                    this.y -= diag;
                    break;
                case 1:
                    diag = delta * Math.sqrt(2)/2;
                    this.x += diag;
                    this.y += diag;
                    break;
                case 0:
                    this.x += delta;
                    break;
            }
        }
    }

    public void setVerticalMovement(byte verticalMovement) {
        this.verticalMovement = verticalMovement;
    }

    public void setHorizontalMovement(byte horizontalMovement) {
        this.horizontalMovement = horizontalMovement;
    }
}
