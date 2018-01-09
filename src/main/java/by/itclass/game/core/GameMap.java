package by.itclass.game.core;

import by.itclass.game.io.CellTypeLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

//класс описывает поле, на котором происходит основное действие игры
public class GameMap implements Drawable {

    public final int CELL_WIDTH = 64; //ширина клетки в пикселях
    public final int CELL_HEIGHT = 64; //высотоа клетки в пикселях

    private int width; //ширина поля в клетках
    private int height; //высота поля в клетках

    private Cell[][] cells; //массив ячеек
    private CellTypeLoader loader;

    public GameMap(int width, int height, CellTypeLoader loader){
        if (width <= 0 || height <= 0){
            throw new IllegalArgumentException("Неправильные размеры карты");
        }
        this.loader = loader;
        this.width = width;
        this.height = height;

        //создаем поле клеток и заполняем его клетками по умолчанию
        cells = new Cell[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = new Cell(0);
            }
        }
    }

    public int getWidth(){return width;}
    public int getHeight(){return height;}

    public int getPixelWidth(){
        return width * CELL_WIDTH;
    }

    public int getPixelHeight(){
        return height * CELL_HEIGHT;
    }

    public Cell getCell(int i, int j){
        if (i < 0 || i >= height){
            throw new IllegalArgumentException("Неправильная i-координата");
        }
        if (j < 0 || j >= width){
            throw new IllegalArgumentException("Неправильная j-координата");
        }
        return cells[i][j];
    }

    public void setCell(int i, int j, Cell cell){
        if (i < 0 || i >= height){
            throw new IllegalArgumentException("Неправильная i-координата");
        }
        if (j < 0 || j >= width){
            throw new IllegalArgumentException("Неправильная j-координата");
        }
        if (cell == null){
            throw new IllegalArgumentException("Отсутствует ячейка");
        }
        cells[i][j] = cell;
    }

    public CellType getType(int type){
        return loader.getCellType(type);
    }

    @Override
    public void draw(Graphics g, double deltaTime) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                int type = cells[i][j].getType();
                BufferedImage image = loader.getCellType(type).getCellImage();
                g.drawImage(image,j * CELL_WIDTH, i* CELL_HEIGHT, null);
            }
        }
    }
}
