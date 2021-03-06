package by.itclass.game.gui;

import by.itclass.game.core.Game;
import by.itclass.game.core.GameMap;
import by.itclass.game.core.HeroType;
import by.itclass.game.core.commands.KeyboardPressCommand;
import by.itclass.game.core.commands.KeyboardReleaseCommand;
import by.itclass.game.io.HeroTypeLoader;
import by.itclass.game.io.MapReader;
import by.itclass.game.io.CellTypeLoader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.TimerTask;
import java.util.Timer;

public class MainGameFrame extends JFrame {

    private long prevTime;
    private long TIME_TICK = 1000 / 100;
    private Timer timer;
    private Game game;

    public MainGameFrame(GameMap map){

        HeroTypeLoader heroLoader = new HeroTypeLoader(new File("heroes.txt"));
        heroLoader.load();

        if (map == null){
            throw new IllegalArgumentException("Отсутствует карта");
        }

        game = new Game(map,heroLoader);

        this.addKeyListener(new KeyboardListener());

        timer = new Timer();
        timer.schedule(new GameTimer(),0,TIME_TICK);

        int windowWidth = game.getWidth();
        int windowHeight = game.getHeight();

        this.setUndecorated(true);
        this.setSize(windowWidth,windowHeight);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

        prevTime = System.nanoTime();
    }

    public static void main(String[] args) {

        CellTypeLoader loader = new CellTypeLoader(new File("images.txt"));
        loader.load();

        MapReader reader = new MapReader(new File("map.txt"));
        GameMap map = reader.read(loader);
        new MainGameFrame(map);
    }


    @Override
    public void paint(Graphics graphics){
        long currentTime = System.nanoTime();
        double deltaTime = (currentTime - prevTime) * 1.0 / 1000000000;
        prevTime = currentTime;
        game.draw(graphics, deltaTime);
    }

    class KeyboardListener extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e) {
            KeyboardPressCommand pk = new KeyboardPressCommand(game, e.getKeyCode());
            game.sendCommand(pk);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            KeyboardReleaseCommand rc = new KeyboardReleaseCommand(game, e.getKeyCode());
            game.sendCommand(rc);
        }
    }

    class GameTimer extends TimerTask{

        long prevTime;

        public GameTimer() {
            prevTime = System.nanoTime();
        }

        @Override
        public void run() {
            long currentTime = System.nanoTime();
            double deltaTime = (currentTime - prevTime) * 1.0 / 1000000000;
            prevTime = currentTime;
            game.update(deltaTime);
            repaint();
        }
    }

}

