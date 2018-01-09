package by.itclass.game.core;

import java.awt.image.BufferedImage;

public class Hero {

    private float x; // координата персонажа
    private float y;
    private BufferedImage image;//изображение персонажа

    public Hero(BufferedImage image, float x, float y){
        if(image == null){
            throw new IllegalArgumentException("Изображение отсутствует");
        }
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public float getX() { return x; }

    public float getY() {
        return y;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void move(float deltaX, float deltaY){
        this.x += deltaX;
        this.y += deltaY;
    }
}
