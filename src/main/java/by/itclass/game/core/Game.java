package by.itclass.game.core;

import by.itclass.game.core.commands.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;

/*
* Класс, в котором инкапсулированна вся логика игры
* */
public class Game implements Drawable,Updatable{

    private GameMap gameMap;
    private Hero hero;
    private Queue<Command> queue;

    public Game(GameMap map, BufferedImage heroImage){
        if (map == null){
            throw new IllegalArgumentException("Отсутствует карта");
        }
        this.gameMap = map;
        this.hero = new Hero(heroImage,0,0,50, gameMap);
        this.queue = new LinkedList<>();
    }

    public int getWidth(){
        return this.gameMap.getWidth() * this.gameMap.CELL_WIDTH;
    }

    public int getHeight(){
        return this.gameMap.getHeight() * this.gameMap.CELL_HEIGHT;
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
        hero.update(elapsedTime);
    }

    public void sendCommand(Command command){
        queue.add(command);
    }

    @Override
    public void draw(Graphics g, double deltaTime) {
        gameMap.draw(g,deltaTime);
        hero.draw(g,deltaTime);
    }
}

