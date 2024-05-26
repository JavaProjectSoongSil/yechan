package Card;

import Character.Character;

public interface Card {
    String getName(); // 카드의 이름 반환
    void use(Character player, Character opponent); // 카드 사용 (공격, 방어, 회복, 이동에 따라 다름)
}
