package by.itclass.game.core;

import java.awt.image.BufferedImage;

public class CellType {

    private boolean walkable;
    private boolean swimmable;
    private BufferedImage cellImage;

    public CellType(boolean walkable, boolean swimmable, BufferedImage cellImage) {
        if (cellImage == null){
            throw new IllegalArgumentException("Отсутствует изображение");
        }
        this.walkable = walkable;
        this.swimmable = swimmable;
        this.cellImage = cellImage;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public boolean isSwimmable() {
        return swimmable;
    }

    public BufferedImage getCellImage() {
        return cellImage;
    }
}
