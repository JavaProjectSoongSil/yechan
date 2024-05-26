package Inventory.impl;

import Card.direction.Direction;
import Card.impl.AttackCard;
import Card.impl.DefenseCard;
import Card.impl.HealCard;
import Card.impl.MoveCard;
import Character.impl.Player;
import Inventory.Inventory;


import java.util.ArrayList;
import java.util.List; // List를 위한 임포트
import java.util.Random;
import Character.Character;

import Card.Card; // Card를 위한 임포트
import Item.Item;
import Item.impl.Weapon;

public class InventoryImpl implements Inventory {
    private List<Card> cards; // 카드 리스트
    private List<Item> items; // 아이템 리스트
    public InventoryImpl() {
        this.cards = new ArrayList<Card>();
        this.items = new ArrayList<Item>();

        // 기본 아이템 추가
        Item basicItem = new Weapon("기본 공격무기",2);
        this.items.add(basicItem);

        // 이동 카드 추가
        Card moveUpCard = new MoveCard("위로 이동 카드", Direction.UP, 1);
        Card moveDownCard = new MoveCard("아래로 이동 카드", Direction.DOWN, 1);
        Card moveLeftCard = new MoveCard("왼쪽으로 이동 카드", Direction.LEFT, 1);
        Card moveRightCard = new MoveCard("오른쪽으로 이동 카드", Direction.RIGHT, 1);
        this.cards.add(moveUpCard);
        this.cards.add(moveDownCard);
        this.cards.add(moveLeftCard);
        this.cards.add(moveRightCard);

        // 공격, 방어, 회복 카드 추가
        Card attackCard = new AttackCard("기본 공격 카드", 10);
        Card defenseCard = new DefenseCard("기본 방어 카드");
        Card healCard = new HealCard("기본 회복 카드", 5);
        this.cards.add(attackCard);
        this.cards.add(defenseCard);
        this.cards.add(healCard);
    }


    @Override
    public Card drawRandomCard() { // 랜덤 카드 뽑기
        Random random = new Random();
        int index = random.nextInt(cards.size());
        return cards.get(index);
    }
    @Override
    public Card selectCard(int index) {
        if (index >= 0 && index < cards.size()) {
            return cards.get(index);
        } else {
            return null;
        }
    }

    @Override
    public void printInventory() {
        // 인벤토리 출력
        System.out.println("\n==================== 인벤토리 ====================");
        System.out.println("카드:");
        int i = 1;
        for (Card card : cards) {
            System.out.printf("%d. %-10s\n", i++, card.getName());
        }
        System.out.println("\n아이템:");
        i = 1;
        for (Item item : items) {
            System.out.printf("%d. %-10s\n", i++, item.getName());
        }
        System.out.println("\n=================================================\n");
    }

    @Override
    public void addCard(Card card) {
        cards.add(card);
    }
    @Override
    public void removeCard(Card card) {

    }

    @Override
    public List<Card> getCards() {
        return cards;
    }

    @Override
    public void addItem(Item item) {

    }

    @Override
    public List<Item> getItems() {
        return items;
    }
    @Override
    public void removeItem(Item item) {

    }

    @Override
    public void handleUserInput(Character player, String input) {

        // 카드 삭제
        if (input.startsWith("delete card ")) {
            int cardIndex = Integer.parseInt(input.substring(12));
            if (cardIndex > 0 && cardIndex <= cards.size()) {
                Card card = cards.get(cardIndex - 1);
                removeCard(card);
                System.out.println("\n" + card.getName() + " 카드가 삭제되었습니다.\n");
            } else {
                System.out.println("\n잘못된 카드 번호입니다.\n");
            }
        }

        // 아이템 삭제
        else if (input.startsWith("delete item ")) {
            int itemIndex = Integer.parseInt(input.substring(12));
            if (itemIndex > 0 && itemIndex <= items.size()) {
                Item item = items.get(itemIndex - 1);
                removeItem(item);
                System.out.println("\n" + item.getName() + " 아이템이 삭제되었습니다.\n");
            } else {
                System.out.println("\n잘못된 아이템 번호입니다.\n");
            }
        }

        // 아이템 장착
        else if (input.startsWith("equip ")) {
            int itemIndex = Integer.parseInt(input.substring(6));
            if (itemIndex > 0 && itemIndex <= items.size()) {
                Item item = items.get(itemIndex - 1);
                player.equipItem(item);
                System.out.println("\n" + item.getName() + " 아이템이 장착되었습니다.\n");
            } else {
                System.out.println("\n잘못된 아이템 번호입니다.\n");
            }
        }

        //아이템 장착 해제
        else if (input.startsWith("unequip")) {
            player.unequipItem();
            System.out.println("\n아이템이 장착 해제되었습니다.\n");
        }

        // 잘못된 입력
        else {
            System.out.println("\n잘못된 입력입니다.\n");
        }
    }


}
