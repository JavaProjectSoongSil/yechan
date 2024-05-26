package Character.impl;

import Character.Character;
import Inventory.Inventory;
import Inventory.impl.InventoryImpl;
import Item.Item;


public class Player implements Character{
    private String name; // 이름
    private int level; // 레벨
    private int experience; //경험치
    private int health; // 최대 체력
    private int currentHealth; // 현재 체력
    private int attack; // 공격력
    private int defense; // 방어력
    private boolean shiled; // 방어 유무
    private Item item; // 장착한 아이템
    private Inventory inventory; // 보유 인벤토리
    private int x;
    private int y;

    public Player(String name, int level, int health, int attack, int defense) {
        this.name = name;
        this.experience = 0;
        this.level = level;
        this.health = health;
        this.currentHealth = health;
        this.attack = attack;
        this.defense = defense;
        this.shiled = false;
        this.inventory = new InventoryImpl();
    }



    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public int getAttack() {
        return this.attack;
    }

    @Override
    public int getDefense() {
        return this.defense;
    }

    @Override
    public boolean getShiled() {
        return this.shiled;
    }
    @Override
    public int getX() {
        return x;
    }
    @Override
    public int getY() {
        return y;
    }

    @Override
    public void showInformation() {
        System.out.println("\n==================== 플레이어 캐릭터 정보 ====================");
        System.out.printf("이름: %-10s\n", name);
        System.out.printf("레벨: %-10d\n", level);
        System.out.printf("경험치: %-10s\n", experience);
        System.out.printf("체력: %-10d\n", health);
        System.out.printf("공격력: %-10d\n", attack);
        System.out.printf("방어력: %-10d\n", defense);
        System.out.println("============================================================\n");
    }

    @Override
    public void setShiled(boolean shiled) {
        this.shiled = shiled;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void resetCurrentHealth() {
    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
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




    // 경험치를 추가하고, 필요한 경우 레벨을 증가시키는 메서드
    @Override
    public void addExperience(int exp) {
    }

    @Override
    public void levelUp() {

    }

}
