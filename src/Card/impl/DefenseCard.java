package Card.impl;

import Card.Card;
import Character.Character;

public class DefenseCard implements Card{
    private String name;

    public DefenseCard(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void use(Character player, Character opponent) {
        player.setShiled(true);
    }
}
