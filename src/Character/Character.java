package Character;

import Inventory.Inventory;
import Item.Item;

public interface Character {
    int getHealth(); // 캐릭터 체력 반환
    int getAttack(); // 캐릭터 공격력 반환
    int getDefense(); // 캐릭터 방어력 반환
    boolean getShiled(); // 캐릭터 방어 유무 반환
    int getX(); // 캐릭터 현재 X 좌표 반환
    int getY(); // 캐릭터 현재 Y 좌표 반환
    Inventory getInventory(); // 캐릭터 인벤토리 반환
    void showInformation(); // 캐릭터 정보 출력


    void equipItem(Item item); // 인벤토리의 아이템 장착
    void unequipItem(); // 아이템 장착해제


    void setShiled(boolean shiled); // 캐릭터 쉴드 유무 설정 (1회 방어 가능)
    void setHealth(int health); // 캐릭터 체력 설정
    void resetCurrentHealth(); // 현재 체력을 최대 체력으로 초기화
    void setPosition(int x, int y); // 캐릭터 위치 설정


    void addExperience(int exp); // 경험치 추가

    void levelUp(); // 레벨 업 (공격력, 방어력, 체력 증가)

}
