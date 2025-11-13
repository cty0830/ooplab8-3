import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.*;

public class tictactoetest {

    @Test
    public void testInitialState() {
        tictactoe game = new tictactoe();
        assertFalse(game.isGameOver(), "遊戲剛開始不應該結束");
        assertEquals('X', game.getCurrentPlayer(), "第一位玩家應該是 X");
    }

    @Test
    public void testXWinsRow() {
        tictactoe game = new tictactoe();
        game.set(0, 0); // X
        game.set(1, 0); // O
        game.set(0, 1); // X
        game.set(1, 1); // O
        game.set(0, 2); // X 勝利

        assertTrue(game.isGameOver(), "X 應該勝利");
        assertEquals('X', game.getWinner(), "勝者應為 X");
    }

    @Test
    public void testOWinsDiagonal() {
        tictactoe game = new tictactoe();
        game.set(0, 0); // X
        game.set(0, 2); // O
        game.set(1, 0); // X
        game.set(1, 1); // O
        game.set(2, 1); // X
        game.set(2, 0); // O 勝利（對角線）

        assertTrue(game.isGameOver(), "O 應該勝利");
        assertEquals('O', game.getWinner(), "勝者應為 O");
    }

    @Test
    public void testDrawGame() {
        tictactoe game = new tictactoe();

        // 填滿棋盤但無人勝利
        game.set(0, 0); // X
        game.set(0, 1); // O
        game.set(0, 2); // X
        game.set(1, 1); // O
        game.set(1, 0); // X
        game.set(1, 2); // O
        game.set(2, 1); // X
        game.set(2, 0); // O
        game.set(2, 2); // X

        assertTrue(game.isGameOver(), "棋盤填滿應該結束");
        assertEquals(' ', game.getWinner(), "平手時 winner 應為空白字元");
    }

    @Test
    public void testInvalidMove() {
        tictactoe game = new tictactoe();
        game.set(0, 0); // X
        boolean result = game.set(0, 0); // 同一格
        assertFalse(result, "同一格不應該能再下");
    }

    @Test
    public void testGameStopsAfterWin() {
        tictactoe game = new tictactoe();
        game.set(0, 0); // X
        game.set(1, 0); // O
        game.set(0, 1); // X
        game.set(1, 1); // O
        game.set(0, 2); // X 勝利

        assertTrue(game.isGameOver());
        assertEquals('X', game.getWinner());

        // 嘗試在結束後再下
        boolean result = game.set(2, 2);
        assertFalse(result, "遊戲結束後不應該能再下棋");
    }
}
