package by.itclass.game.io;

import by.itclass.game.core.Cell;
import by.itclass.game.core.GameMap;

import java.io.*;

//чтение игрового поля из файла
public class MapReader {

    private File file;

    public MapReader(File file){
        if (file == null){
            throw new IllegalArgumentException("Отсутствует ссылка на файл");
        }
        if (file.isDirectory() || !file.exists()){
            throw new IllegalArgumentException("Файл отсутствует или является папкой");
        }
        this.file = file;
    }

    public GameMap read(TileImageLoader loader){

        GameMap gameMap;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
            String[] parts = line.split(",");
            if (parts.length != 2){
                throw new IndexOutOfBoundsException("Неверные размеры карты");
            }

            int height = Integer.parseInt(parts[0]);
            int width = Integer.parseInt(parts[1]);

            gameMap = new GameMap(width,height,loader);

            for (int i = 0; i < height; i++) {
                line = bufferedReader.readLine();
                parts = line.split(",");
                if (parts.length != width){
                    throw new IndexOutOfBoundsException("Ширина строки неверна");
                }
                for (int j = 0; j < width; j++) {
                    int type = Integer.parseInt(parts[j]);
                    Cell cell = new Cell(type);
                    gameMap.setCell(i, j, cell);
                }
            }

        }catch (IOException e) {
            return null;
        }
        return gameMap;
    }
}
