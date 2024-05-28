import Card.Card;

import Character.Character;
import Character.impl.Player;
import Game.difficulty.Difficulty;
import Inventory.Inventory;
import Inventory.impl.InventoryImpl;
import Item.Item;
import Game.Game;
import Game.impl.GameImpl;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("캐릭터 이름을 입력하세요: ");
        String playerName = scanner.nextLine();

        Character player = new Player(playerName, 1, 100, 20, 5);

        boolean running = true;

        while (running) {
            System.out.println("1. 게임 시작");
            System.out.println("2. 캐릭터 정보");
            System.out.println("3. 인벤토리 보기");
            System.out.println("4. 종료");
            System.out.print("선택: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: // 게임진행
                    startGame(player);
                    break;
                case 2: // 캐릭터 정보
                    player.showInformation();
                    break;
                case 3: // 인벤토리
                    showInventory(player);
                    break;
                case 4: // 게임 종료
                    running = false;
                    break;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }

        scanner.close();
    }

    public static void startGame(Character player) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("게임 난이도를 선택하세요 (EASY, MEDIUM, HARD):"); // 난이도 설정
        String difficultyInput = scanner.nextLine().toUpperCase();
        Difficulty difficulty;
        try {
            difficulty = Difficulty.valueOf(difficultyInput);
        } catch (IllegalArgumentException e) {
            System.out.println("잘못된 난이도입니다. EASY, MEDIUM, HARD 중 하나를 입력해주세요.");
            return;
        }

        Character computer = createComputerCharacter(difficulty); // 난이도에 따라, 컴퓨터 캐릭터 생성

        Game game = new GameImpl(player, computer, difficulty); // 게임 생성

        System.out.println("게임을 시작합니다!");

        for (int round = 1; round <= 15; round++) {
            System.out.println("라운드 " + round + " 시작!");
            boolean result = game.playRound(player, computer); // 한 라운드에선 사용자와 컴퓨터가 턴제 게임 진행

            if (result) {
                System.out.println("당신이 이겼습니다!");
                game.increaseScore(); // 이겼을땐, 점수 증가

                if (round == 15)  // 만약 마지막 라운드일 경우, 바로 종료
                    game.endGame(); // (리워드 보상 및 최종 점수 출력)

                System.out.println("다음 라운드로 진행하시겠습니까? (yes/no)");
                String input = scanner.nextLine().toLowerCase();

                if (input.equals("no")) {
                    game.endGame(); // 다음 라운드 이동 안할 시, 리워드 보상 및 최종 점수 출력
                } else if (!input.equals("yes")) {
                    System.out.println("잘못된 입력입니다. 'yes' 또는 'no'를 입력해주세요.");
                }

            } else {
                System.out.println("당신이 졌습니다!");
                player.resetHealth(); // 졌을땐, 체력 초기화
                break;
            }
        }
    }

    private static void showInventory(Character player) {
        Inventory inventory = player.getInventory();
        Scanner scanner = new Scanner(System.in);

        // 인벤토리 출력
        inventory.printInventory();

        // 사용자 입력 처리
        while (true) {
            System.out.println("\n********** 알림 **********");
            System.out.println("* 카드를 삭제하려면 'delete card <번호>'를 입력하세요.");
            System.out.println("* 아이템을 삭제하려면 'delete item <번호>'를 입력하세요.");
            System.out.println("* 아이템을 장착하려면 'equip <번호>'를 입력하세요.");
            System.out.println("* 아이템을 장착해제하려면 'unequip'를 입력하세요.");
            System.out.println("* 종료하려면 'exit'를 입력하세요.");
            System.out.println("**************************\n");
            String input = scanner.nextLine();

            // 종료 조건
            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            inventory.handleInventory(player, input); // 사용자 입력에 따라, 인벤토리 제어
        }
    }

    private static Character createComputerCharacter(Difficulty difficulty) {
        switch (difficulty) {
            case EASY: // 쉬움
                return new Player("computer", 1, 80, 15, 3);
            case MEDIUM: // 중간
                return new Player("computer", 1, 100, 20, 5);
            case HARD: // 어려움
                return new Player("computer", 1, 120, 25, 7);
            default:
                throw new IllegalArgumentException(); // 잘못된 난이도가 입력된 경우 예외 발생
        }
    }
}
