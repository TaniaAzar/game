package by.itclass.game.io;

import by.itclass.game.core.CellType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

//класс загрузки ячеек для игрового поля
public class CellTypeLoader {

    public File listFile;
    private Map<Integer, CellType> cellTypeMap;

    public CellTypeLoader(File listFile){
        if (listFile == null){
            throw new IllegalArgumentException("Файл с картинками отсутствует");
        }
        if (listFile.isDirectory() || !listFile.exists()){
            throw new IllegalArgumentException("Файда не существует или является папкой");
        }
        this.listFile = listFile;
        this.cellTypeMap = new HashMap<>();
    }

    public void load(){

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(listFile));
            String line = bufferedReader.readLine();

            while (line != null){
                String[] info = line.split(",");

                int type = Integer.parseInt(info[0]);
                String path = info[1];

                BufferedImage image = ImageIO.read(new File(path));
                Boolean walkable = Boolean.parseBoolean(info[2]);
                Boolean swimmable = Boolean.parseBoolean(info[3]);

                CellType cellType = new CellType(swimmable,walkable,image);

                cellTypeMap.put(type, cellType);

                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new IllegalArgumentException("Список повережден или отсутствует картинка");
        }
    }

    public CellType getCellType(int type){
        if (type < 0){
            throw new IllegalArgumentException("Неверный тип картинки");
        }
        return cellTypeMap.get(type);
    }
}
