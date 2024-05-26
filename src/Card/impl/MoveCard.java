package Card.impl;

import Card.Card;
import Card.direction.Direction;
import Character.Character;

public class MoveCard implements Card{
    String name;
    Direction direction;
    int movePower;

    public MoveCard(String name,Direction direction, int movePower) {
        this.name = name;
        this.direction = direction;
        this.movePower = movePower;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void use(Character player, Character opponent) {
        switch (direction) {
            case UP:
                player.setPosition(player.getX(), player.getY() - movePower);
                break;
            case DOWN:
                player.setPosition(player.getX(), player.getY() + movePower);
                break;
            case LEFT:
                player.setPosition(player.getX() - movePower, player.getY());
                break;
            case RIGHT:
                player.setPosition(player.getX() + movePower, player.getY());
                break;
        }
    }
}
