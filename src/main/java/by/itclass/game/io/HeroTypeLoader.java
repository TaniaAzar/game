package by.itclass.game.io;

import by.itclass.game.core.HeroType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

//класс, отвечающий за загрузку информации о героях
public class HeroTypeLoader {

    private File listFile;
    private Map<Integer, HeroType> heroTypes;

    public HeroTypeLoader(File listFile) {
        if (listFile == null){
            throw new IllegalArgumentException("Файла не существует");
        }
        this.listFile = listFile;
        this.heroTypes = new HashMap<>();
    }

    public void load(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(listFile));

            String line = reader.readLine();
            while (line != null){
                String[] info = line.split(",");

                int type = Integer.parseInt(info[0]);
                String path = info[1];

                BufferedImage img = ImageIO.read(new File(path));

                int maxMovementSpeed = Integer.parseInt(info[2]);
                double swimFactor = Double.parseDouble(info[3]);
                Boolean canSwim = Boolean.parseBoolean(info[4]);

                HeroType heroType = new HeroType(img, maxMovementSpeed, swimFactor, canSwim);
                heroTypes.put(type, heroType);

                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            throw new IllegalArgumentException("Список поврежден или отсутствует картинка");
        }

    }

    public HeroType getHeroType(int type){
        if (type < 0){
            throw new IllegalArgumentException("Неверный тип героя");
        }
        return heroTypes.get(type);
    }

}
