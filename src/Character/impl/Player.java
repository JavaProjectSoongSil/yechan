package Character.impl;

import Character.Character;
import Inventory.Inventory;
import Inventory.impl.InventoryImpl;
import Item.Item;


public class Player implements Character{

    private String name; // 이름
    private int health; // 체력
    private int attack; // 공격력
    private int defense; // 방어력
    private Item item; // 장착한 아이템
    private Inventory inventory; // 보유 인벤토리

    public Player(String name, int level, int health, int attack, int defense) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.inventory = new InventoryImpl();
    }
    @Override
    public void showInformation() {
        System.out.println("\n==================== 플레이어 캐릭터 정보 ====================");
        System.out.printf("이름: %-10s\n", name);
        System.out.printf("체력: %-10d\n", health);
        System.out.printf("공격력: %-10d\n", attack);
        System.out.printf("방어력: %-10d\n", defense);
        System.out.println("============================================================\n");
    }


    @Override
    public Inventory getInventory() {
        return inventory;
    }




    @Override
    public void equipItem(Item item) {

    }

    @Override
    public void unequipItem() {

    }


    @Override
    public void resetHealth() {

    }




    // Getter, Setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }


}
