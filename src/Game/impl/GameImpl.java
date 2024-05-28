package Game.impl;

import Card.Card;
import Character.impl.Player;
import Character.Character;
import Game.Game;
import Game.difficulty.Difficulty;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class GameImpl implements Game {
    private int round; // 현재 라운드
    private int score; // 현재 총점수
    private Difficulty difficulty; // 게임 난이도
    Character player; // 플레이어 캐릭터
    Character computer; // 컴퓨터 캐릭터

    public GameImpl(Character player, Character computer , Difficulty difficulty) {
        this.round = 1;
        this.score = 0;
        this.player = player;
        this.computer = computer;
    }


    @Override
    public void setupGame(Character player, Character computer) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("게임 난이도를 선택하세요 (EASY, MEDIUM, HARD):");
        String difficultyInput = scanner.nextLine().toUpperCase();
        Difficulty difficulty;
        try {
            difficulty = Difficulty.valueOf(difficultyInput);
        } catch (IllegalArgumentException e) {
            System.out.println("잘못된 난이도입니다. EASY, MEDIUM, HARD 중 하나를 입력해주세요.");
            return;
        }
        this.difficulty = difficulty;
    }



    @Override
    public boolean playRound(Character player, Character computer) {
        boolean result = false;
        Scanner scanner = new Scanner(System.in);
        // 게임 진행
        while (true) {

            // 플레이어 턴
            System.out.println("당신이 가지고 있는 카드:");
            List<Card> cards = player.getInventory().getCards();
            for (int i = 0; i < cards.size(); i++) {
                System.out.println((i + 1) + ". " + cards.get(i).getName());
            }


            int cardIndex = -1;
            while (true) {
                System.out.println("카드를 선택하세요:");
                try {
                    cardIndex = scanner.nextInt()-1;
                    if (cardIndex < 0 || cardIndex >= cards.size()) {
                        System.out.println("잘못된 카드 번호입니다. 다시 입력해주세요.");
                        continue;
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
                    scanner.next();
                }
            }


            Card playerCard = player.getInventory().selectCard(cardIndex);
            playerCard.use(player, computer);
            if (computer.getHealth() <= 0) { // 컴퓨터 체력 확인
                result = true; // 플레이어 승리
                break;
            }

            // 컴퓨터 턴
            Card computerCard = computer.getInventory().drawRandomCard();
            computerCard.use(computer, player);
            if (player.getHealth() <= 0) { // 플레이어 체력 확인
                result = false; // 컴퓨터 승리
                break;
            }

            System.out.println(player.getHealth());
        }
        return result;
    }

    @Override
    public void playTurn(Character player, Character computer) {

    }


    @Override
    public void endGame() {
        System.out.println("게임을 종료합니다.");
        player.resetHealth();
        rewardRandomItem(player);
    }

    @Override
    public void increaseScore() {

    }

    @Override
    public void rewardRandomItem(Character player) {
    }
}
