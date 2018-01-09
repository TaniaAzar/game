package by.itclass.game.core.commands;

import by.itclass.game.core.Hero;

public class VerticalMovementCommand implements Command {

    private byte direction;
    private Hero hero;

    public VerticalMovementCommand(byte direction, Hero hero) {
        this.direction = direction;
        this.hero = hero;
    }

    @Override
    public void execute() {
        if (hero != null){
            hero.setVerticalMovement(direction);
        }
    }
}
