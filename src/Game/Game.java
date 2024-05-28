package Game;

import Character.Character;
import Game.difficulty.Difficulty;

public interface Game {

    void setupGame(Character player, Character computer); // 게임 시작 설정
    boolean playRound(Character player, Character computer); // 라운드 진행 후 승패 여부 반환
    void playTurn(Character player, Character computer); // 턴 진행 (각자 카드 뽑고, 행동진행)
    void endGame(); // 게임 종료 (리워드 보상 및 최종 점수 출력)
    void increaseScore(); // 게임 승리시 점수 증가 메서드 (난이도,라운드에 따라 다름)
    void rewardRandomItem(Character player); // 랜덤 아이템 보상 (난이도에 따라 다름)

}
