package by.itclass.game.io;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

//класс загрузки картинок для игрового поля
public class TileImageLoader {

    public File listFile;
    private Map<Integer,BufferedImage> imageMap;

    public TileImageLoader(File listFile){
        if (listFile == null){
            throw new IllegalArgumentException("Файл с картинками отсутствует");
        }
        if (listFile.isDirectory() || !listFile.exists()){
            throw new IllegalArgumentException("Файда не существует или является папкой");
        }
        this.listFile = listFile;
        this.imageMap = new HashMap<>();
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
                imageMap.put(type,image);

                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new IllegalArgumentException("Список повережден или отсутствует картинка");
        }
    }

    public BufferedImage getImage(int type){
        if (type < 0){
            throw new IllegalArgumentException("Неверный тип картинки");
        }
        return imageMap.get(type);
    }
}
