package Item;

import Character.Character;

public interface Item {
    String getName(); // 아이템 이름 반환
    int getAttackPower(); // 아이템 공격력 반환


    void enforceItem(Character player); // 아이템 강화
}
