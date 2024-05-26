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
    private Character[][] board; // 게임 보드

    public GameImpl(Character player, Character computer , Difficulty difficulty) {
        this.round = 1;
        this.score = 0;
        this.player = player;
        this.computer = computer;
        this.board = new Character[5][5]; // 보드 크기를 6x6으로 변경
        resetBoard();
    }

    private void resetBoard() {
        // 모든 셀을 null로 설정
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = null;
            }
        }

        // 플레이어와 컴퓨터를 보드의 중앙에 배치
        player.setPosition(2, 0); // 위치를 업데이트
        computer.setPosition(2, 4); // 위치를 업데이트
        board[player.getX()][player.getY()] = player;
        board[computer.getX()][computer.getY()] = computer;
    }

    @Override
    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == null) {
                    System.out.print("-");
                } else if (board[i][j] == player) {
                    System.out.print("P");
                } else if (board[i][j] == computer) {
                    System.out.print("C");
                }
            }
            System.out.println();
        }
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
       resetBoard();
        // 게임 진행
        while (true) {
            printBoard();

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
    public void playGame() {
        System.out.println("게임을 시작합니다!");

        for (int round = 1; round <= 15; round++) {
            System.out.println("라운드 " + round + " 시작!");
            boolean result = playRound(player, computer);

            if (result) {
                handleRoundWin(round);
            } else {
                System.out.println("당신이 졌습니다!");
                player.resetCurrentHealth();
                break;
            }
        }
    }

    @Override
    public void handleRoundWin(int round) {
        System.out.println("당신이 이겼습니다!");
        increaseScore();

        if (round == 15) {
            endGame();
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("다음 라운드로 진행하시겠습니까? (yes/no)");
        String input = scanner.nextLine().toLowerCase();

        if (input.equals("no")) {
            endGame();
        } else if (!input.equals("yes")) {
            System.out.println("잘못된 입력입니다. 'yes' 또는 'no'를 입력해주세요.");
        }
    }

    @Override
    public void endGame() {
        System.out.println("게임을 종료합니다.");
        player.addExperience(getScore());
        player.resetCurrentHealth();
        rewardRandomItem(player);
    }

    @Override
    public int getScore() {
       return this.score;
    }

    @Override
    public void updateRound() {
        this.round++;
    }

    @Override
    public void increaseScore() {

    }

    @Override
    public void rewardRandomItem(Character player) {
    }
}
