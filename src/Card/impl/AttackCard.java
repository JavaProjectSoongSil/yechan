package Card.impl;

import Card.Card;
import Character.Character;

public class AttackCard implements Card{
    private String name;
    private int attackPower;

    public AttackCard(String name, int attackPower) {
        this.name = name;
        this.attackPower = attackPower;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void use(Character player, Character opponent) {
        int damage = player.getAttack() + this.attackPower - opponent.getDefense(); // 본인 공격력 + 카드 공격력 - 상대방 방어력

        if (damage > 0) { // 데미지가 0보다 클 경우 공격성공
            opponent.setHealth(opponent.getHealth() - damage);
            System.out.println("공격 성공" + damage + "의 데미지를 입혔습니다.");
        } else  // 데미지가 0보다 작을 경우 공격 실패
            System.out.println("공격 성공" + "0의 데미지를 입혔습니다.");
    }
}
