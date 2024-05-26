package Game;

import Character.Character;
import Game.difficulty.Difficulty;

public interface Game {

    void setupGame(Character player, Character computer); // 게임 시작 설정
    boolean playRound(Character player, Character computer); // 라운드 진행 후 승패 여부 반환
    void playGame(); // 게임 진행
    void handleRoundWin(int round); // 라운드 승리시 보상관리
    void endGame(); // 게임 종료
    void printBoard(); // 보드 출력
    int getScore(); // 현재까지 획득한 점수 반환
    void updateRound(); // 다음 라운드 업데이트
    void increaseScore(); // 게임 승리시 점수 증가 메서드 (난이도,라운드에 따라 다름)
    void rewardRandomItem(Character player); // 랜덤 아이템 보상 (난이도에 따라 다름)

}
