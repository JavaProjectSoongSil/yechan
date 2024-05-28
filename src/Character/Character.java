package Character;

import Game.difficulty.Difficulty;
import Inventory.Inventory;
import Item.Item;

public interface Character {


    Inventory getInventory(); // 캐릭터 인벤토리 반환
    void showInformation(); // 캐릭터 정보 출력



    void equipItem(Item item); // 아이템 장착
    void unequipItem(); // 아이템 장착해제



    void resetHealth(); // 캐릭터 체력 초기화 (죽었을때 + 추후 레벨업 구현할때?)




    // getter 및 setter
    int getHealth(); // 캐릭터 체력 반환
    int getAttack(); // 캐릭터 공격력 반환
    int getDefense(); // 캐릭터 방어력 반환
    void setHealth(int health); // 캐릭터 체력 설정


}
