package by.itclass.game.core;

//Клетка на игровом поле
public class Cell {

    private int type;

    public Cell(int type){
        if (type < 0){
            throw new IllegalArgumentException("Неверный тип клетки");
        }
        this.type = type;
    }

    public int getType(){
        return type;
    }

    public boolean isWalkable(){
        return type < 2 || type > 4;
    }
}
