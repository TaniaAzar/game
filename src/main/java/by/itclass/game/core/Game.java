package by.itclass.game.core;

import by.itclass.game.core.commands.*;
import by.itclass.game.gui.MainGameFrame;
import by.itclass.game.io.TileImageLoader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;

/*
* Класс, в котором инкапсулированна вся логика игры
* */
public class Game {

    private TileImageLoader loader;
    private GameMap gameMap;
    private Hero hero;
    private Queue<Command> queue;

    public Game(GameMap map, BufferedImage heroImage, TileImageLoader loader){
        if (map == null){
            throw new IllegalArgumentException("Отсутствует карта");
        }
        if (loader == null){
            throw new IllegalArgumentException("Отсутствует словарь картинок");
        }
        this.gameMap = map;
        this.loader = loader;
        this.hero = new Hero(heroImage,0,0,20);
        this.queue = new LinkedList<>();
    }

    public int getWidth(){
        return this.gameMap.getWidth() * this.gameMap.CELL_WIDTH;
    }

    public int getHeight(){
        return this.gameMap.getHeight() * this.gameMap.CELL_HEIGHT;
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

    public void update(double elapsedTime){
        while (!queue.isEmpty()){
            Command command = queue.poll();
            if (command != null){
                if (command instanceof KeyboardPressCommand){
                    KeyboardPressCommand kpc = (KeyboardPressCommand)command;
                    int key = kpc.getCode();
                    switch (key){
                        case KeyEvent.VK_UP:
                            Command c = new VerticalMovementCommand((byte)-1,hero);
                            sendCommand(c);
                            break;
                        case KeyEvent.VK_DOWN:
                            c = new VerticalMovementCommand((byte)1,hero);
                            sendCommand(c);
                            break;
                        case KeyEvent.VK_LEFT:
                            c = new HorizontalMovementCommand((byte)-1,hero);
                            sendCommand(c);
                            break;
                        case KeyEvent.VK_RIGHT:
                            c = new HorizontalMovementCommand((byte)1,hero);
                            sendCommand(c);
                            break;
                    }
                }

                if (command instanceof KeyboardReleaseCommand){
                    KeyboardReleaseCommand krc = (KeyboardReleaseCommand)command;
                    int key = krc.getCode();
                    switch (key){
                        case KeyEvent.VK_UP:
                        case KeyEvent.VK_DOWN:
                            Command c = new VerticalMovementCommand((byte)0,hero);
                            sendCommand(c);
                            break;
                        case KeyEvent.VK_LEFT:
                        case KeyEvent.VK_RIGHT:
                            c = new HorizontalMovementCommand((byte)0,hero);
                            sendCommand(c);
                            break;
                    }
                }
                command.execute();
            }
        }
        hero.move(elapsedTime);
    }

    public void sendCommand(Command command){
        queue.add(command);
    }
}

