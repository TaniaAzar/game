package by.itclass.game.core.commands;

import by.itclass.game.core.Game;

public class KeyboardPressCommand implements Command {

    private Game game;
    private  int code;

    public KeyboardPressCommand(Game game, int code) {
        this.game = game;
        this.code = code;
    }

    @Override
    public void execute() {

    }

    public int getCode() {
        return code;
    }
}
