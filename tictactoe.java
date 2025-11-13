public class tictactoe {
    private char[][] board;   // 棋盤
    private char currentPlayer; // 當前玩家 ('X' 或 'O')
    private boolean gameOver;   // 是否結束
    private char winner;        // 'X'、'O' 或 ' '（平手/未結束）

    // 建構子：初始化棋盤
    public tictactoe() {
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
        currentPlayer = 'X';
        gameOver = false;
        winner = ' ';
    }

    // 玩家設定位置
    public boolean set(int row, int col) {
        if (gameOver) {
            System.out.println("遊戲已結束。");
            return false;
        }

        if (row < 0 || row >= 3 || col < 0 || col >= 3) {
            System.out.println("無效的位置！");
            return false;
        }

        if (board[row][col] != ' ') {
            System.out.println("該位置已被佔用！");
            return false;
        }

        board[row][col] = currentPlayer; // 設定棋子
        evaluate(); // 檢查是否結束

        if (!gameOver) { // 換玩家
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
        return true;
    }

    // 判斷遊戲狀態
    private void evaluate() {
        // 檢查橫列、直行、斜線
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != ' ' &&
                board[i][0] == board[i][1] &&
                board[i][1] == board[i][2]) {
                winner = board[i][0];
                gameOver = true;
                return;
            }

            if (board[0][i] != ' ' &&
                board[0][i] == board[1][i] &&
                board[1][i] == board[2][i]) {
                winner = board[0][i];
                gameOver = true;
                return;
            }
        }

        // 主對角線
        if (board[0][0] != ' ' &&
            board[0][0] == board[1][1] &&
            board[1][1] == board[2][2]) {
            winner = board[0][0];
            gameOver = true;
            return;
        }

        // 副對角線
        if (board[0][2] != ' ' &&
            board[0][2] == board[1][1] &&
            board[1][1] == board[2][0]) {
            winner = board[0][2];
            gameOver = true;
            return;
        }

        // 檢查平手
        boolean full = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    full = false;
                    break;
                }
            }
        }
        if (full) {
            gameOver = true;
            winner = ' '; // 平手
        }
    }

    // 顯示棋盤
    public void printBoard() {
        System.out.println("目前棋盤：");
        for (int i = 0; i < 3; i++) {
            System.out.println(" " + board[i][0] + " | " + board[i][1] + " | " + board[i][2]);
            if (i < 2) System.out.println("---+---+---");
        }
    }

    // 查看目前狀態
    public boolean isGameOver() {
        return gameOver;
    }

    // 查看勝者（'X', 'O', 或 ' ' 表示平手）
    public char getWinner() {
        return winner;
    }

    // 查看目前該誰下
    public char getCurrentPlayer() {
        return currentPlayer;
    }
}
