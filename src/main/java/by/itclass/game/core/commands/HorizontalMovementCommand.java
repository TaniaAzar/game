package by.itclass.game.core.commands;

import by.itclass.game.core.Hero;

public class HorizontalMovementCommand implements Command {

    private byte direction;
    private Hero hero;

    public HorizontalMovementCommand(byte direction, Hero hero) {
        this.direction = direction;
        this.hero = hero;
    }

    @Override
    public void execute() {
        if (hero != null){
            hero.setHorizontalMovement(direction);
        }
    }
}
