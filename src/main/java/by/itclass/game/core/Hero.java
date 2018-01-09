package by.itclass.game.core;

import java.awt.image.BufferedImage;

public class Hero {

    private float x; // координата персонажа
    private float y;
    private BufferedImage image;//изображение персонажа

    private byte verticalMovement;
    private byte horizontalMovement;

    private float maxMovementSpeed;//максимальная скорость героя

    public Hero(BufferedImage image, float x, float y, float maxMovementSpeed){
        if(image == null){
            throw new IllegalArgumentException("Изображение отсутствует");
        }
        this.image = image;
        this.x = x;
        this.y = y;
        this.maxMovementSpeed = maxMovementSpeed;
    }

    public float getX() { return x; }

    public float getY() {
        return y;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void move(){
        if (horizontalMovement == 0){
            switch (verticalMovement){
                case -1:
                    this.y -= maxMovementSpeed;
                    break;
                case 1:
                    this.y += maxMovementSpeed;
                    break;
            }
        }else if (horizontalMovement == -1){
            switch (verticalMovement){
                case -1:
                    float diag = maxMovementSpeed * (float)Math.sqrt(2)/2;
                    this.x -= diag;
                    this.y -= diag;
                    break;
                case 1:
                    diag = maxMovementSpeed * (float)Math.sqrt(2)/2;
                    this.x -= diag;
                    this.y += diag;
                    break;
                case 0:
                    this.x -= maxMovementSpeed;
                    break;
            }
        }else if (horizontalMovement == 1){
            switch (verticalMovement){
                case -1:
                    float diag = maxMovementSpeed * (float)Math.sqrt(2)/2;
                    this.x += diag;
                    this.y -= diag;
                    break;
                case 1:
                    diag = maxMovementSpeed * (float)Math.sqrt(2)/2;
                    this.x += diag;
                    this.y += diag;
                    break;
                case 0:
                    this.x += maxMovementSpeed;
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
