package by.itclass.game.gui;

import by.itclass.game.core.GameMap;
import by.itclass.game.core.Hero;
import by.itclass.game.io.MapReader;
import by.itclass.game.io.TileImageLoader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainGameFrame extends JFrame {

    private GameMap gameMap;
    private TileImageLoader loader;
    private Hero hero;
    private BufferedImage heroImage;

    public MainGameFrame(GameMap gameMap){
        if (gameMap == null){
            throw new IllegalArgumentException("Отсутствует карта");
        }

        loader = new TileImageLoader(new File("images.txt"));
        loader.load();

        this.gameMap = gameMap;

        try {
            heroImage = ImageIO.read(new File("Images/hero.png"));
        } catch (IOException e) {
            throw new IllegalArgumentException("Отсутствует картинка");
        }
        this.hero = new Hero(heroImage,0,0);

        this.addKeyListener(new KeyboardListener());

        int windowWidth = this.gameMap.getWidth() * this.gameMap.CELL_WIDTH;
        int windowHeight = this.gameMap.getHeight() * this.gameMap.CELL_HEIGHT;

        this.setUndecorated(true);
        this.setSize(windowWidth,windowHeight);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        MapReader reader = new MapReader(new File("map.txt"));
        GameMap map = reader.read();
        new MainGameFrame(map);
    }

    public void paint(Graphics g){
        for (int i = 0; i < gameMap.getHeight(); i++) {
            for (int j = 0; j < gameMap.getWidth(); j++) {

                int type = gameMap.getCell(i, j).getType();

                BufferedImage image = loader.getImage(type);
                g.drawImage(image, j * gameMap.CELL_WIDTH, i * gameMap.CELL_HEIGHT, null);
            }
        }
        g.drawImage(hero.getImage(), (int)hero.getX(), (int) hero.getY(),null);
    }

    class KeyboardListener extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_UP:
                    hero.move(0, -5);
                    break;
                case KeyEvent.VK_DOWN:
                    hero.move(0,5);
                    break;
                case KeyEvent.VK_LEFT:
                    hero.move(-5,0);
                    break;
                case KeyEvent.VK_RIGHT:
                    hero.move(5,0);
                    break;
            }
            repaint();
        }
    }

}

