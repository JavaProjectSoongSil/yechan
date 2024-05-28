package Item.impl;

import Character.Character;
import Item.Item;

public class Weapon implements Item{
    private String name; // 아이템 이름
    private int attackPower; // 아이템 공격력

    public Weapon(String name, int attackPower) {
        this.name = name;
        this.attackPower = attackPower;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getAttackPower() {
        return this.attackPower;
    }


    @Override
    public void enforceItem(Character player) {

    }



}
