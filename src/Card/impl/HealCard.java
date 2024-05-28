package Card.impl;

import Card.Card;
import Character.Character;

public class HealCard implements Card{
    private String name;
    private int healPower;

    public HealCard(String name, int healPower) {
        this.name = name;
        this.healPower = healPower;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void use(Character player, Character opponent) {
        player.setHealth(player.getHealth() + this.healPower);
        System.out.println("회복 성공" + this.healPower + "의 체력을 회복했습니다.");
    }
}
