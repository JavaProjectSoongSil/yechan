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

        Character player = new Player(playerName,1,  100, 20, 5);

        boolean running = true;

        while (running) {
            System.out.println("1. 게임 시작");
            System.out.println("2. 캐릭터 정보");
            System.out.println("3. 인벤토리 보기");
            System.out.println("4. 종료");
            System.out.print("선택: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    startGame(player);
                    break;
                case 2:
                    player.showInformation();
                    break;
                case 3:
                    showInventory(player);
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }

        scanner.close();
    }

    public static void startGame(Character player) {
        Character computer = new Player("컴퓨터", 1, 100, 20, 5);
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
        Game game = new GameImpl(player, computer, difficulty);

        System.out.println("게임을 시작합니다!");

        for (int round = 1; round <= 15; round++) {
            System.out.println("라운드 " + round + " 시작!");
            boolean result = false;

            result = game.playRound(player, computer); // 라운드 진행 ( 1=승리, 0=패배)

            if (result) { // 라운드 우승 OR 마지막 라운드
                System.out.println("당신이 이겼습니다!");
                game.increaseScore(); // 점수 증가

                if(round == 15){
                    System.out.println("게임을 종료합니다.");
                    player.addExperience(game.getScore()); // Score를 경험치로 추가
                    player.resetCurrentHealth(); // 플레이어 체력 초기화
                    game.rewardRandomItem(player);
                }

                System.out.println("다음 라운드로 진행하시겠습니까? (yes/no)");
                String input = scanner.nextLine().toLowerCase();
                switch (input) {
                    case "yes": // 다음 라운드 진행
                        game.updateRound();
                        break;
                    case "no": // 게임 종료
                        System.out.println("게임을 종료합니다.");
                        player.addExperience(game.getScore()); // Score를 경험치로 추가
                        player.resetCurrentHealth(); // 플레이어 체력 초기화
                        return;
                    default: // 잘못된 입력
                        System.out.println("잘못된 입력입니다. 'yes' 또는 'no'를 입력해주세요.");
                        break;
                }
            }
            else { // 라운드 패배
                System.out.println("당신이 졌습니다!");
                player.resetCurrentHealth(); // 플레이어 체력 초기화
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
            inventory.handleUserInput(player, input);
        }
    }
}
