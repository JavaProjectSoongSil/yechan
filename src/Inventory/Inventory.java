package Inventory;

import Card.Card;
import Character.impl.Player;
import Item.Item;
import Character.Character;

import java.util.List;

public interface Inventory {

    void printInventory(); // 인벤토리 보기



    void addCard(Card card); // 인벤토리에 카드추가
    List<Card> getCards(); // 인벤토리에 있는 카드 리스트 반환
    void removeCard(Card card); // 인벤토리에 있는 카드 제거
    Card drawRandomCard(); // 랜덤 카드 뽑기 (컴퓨터)
    Card selectCard(int index); // 직접 카드 뽑기 (사용자)




    void addItem(Item item); // 인벤토리에 아이템 추가
    List<Item> getItems(); // 인벤토리에 있는 아이템 리스트 반환
    void removeItem(Item item); // 인벤토리에 있는 아이템 제거




    void handleInventory(Character player, String input); // 인벤토리 핸들링 (카드관리, 아이템 관리)

}
