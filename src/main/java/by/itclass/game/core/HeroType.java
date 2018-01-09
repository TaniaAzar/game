package by.itclass.game.core;

import java.awt.image.BufferedImage;

public class HeroType {

    private BufferedImage image;
    private int maxMovementSpeed;
    private double swimFactor;
    private boolean canSwim;

    private int width;
    private int height;

    public HeroType(BufferedImage image, int maxMovementSpeed, double swimFactor, boolean canSwim) {
        if(image == null){
            throw new IllegalArgumentException("Изображение отсутствует");
        }
        if(maxMovementSpeed < 0){
            throw new IllegalArgumentException("Скорость отсутствует");
        }
        if(swimFactor < 0){
            throw new IllegalArgumentException("Отрицательный штраф за плавание");
        }
        this.image = image;
        this.maxMovementSpeed = maxMovementSpeed;
        this.swimFactor = swimFactor;
        this.canSwim = canSwim;
        this.height = image.getHeight();
        this.width = image.getWidth();
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getMaxMovementSpeed() {
        return maxMovementSpeed;
    }

    public double getSwimFactor() {
        return swimFactor;
    }

    public boolean isCanSwim() {
        return canSwim;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
