public class TicTacToe {

    public enum Player { X, O }
    public enum State { IN_PROGRESS, X_WINS, O_WINS, DRAW }

    private char[][] board = new char[3][3];
    private Player next = Player.X;
    private State state = State.IN_PROGRESS;
    private int moves = 0;

    public Player getNextPlayer() {
        return next;
    }

    public State getState() {
        return state;
    }

    public void set(int row, int col) {
        if (state != State.IN_PROGRESS) {
            throw new IllegalStateException("Game already finished");
        }
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            throw new IllegalArgumentException("Out of range");
        }
        if (board[row][col] != '\0') {
            throw new IllegalArgumentException("Cell already occupied");
        }

        board[row][col] = (next == Player.X) ? 'X' : 'O';
        moves++;

        state = evaluate();

        if (state == State.IN_PROGRESS) {
            next = (next == Player.X) ? Player.O : Player.X;
        }
    }

    public State evaluate() {
        // rows
        for (int r = 0; r < 3; r++) {
            if (board[r][0] != '\0'
                    && board[r][0] == board[r][1]
                    && board[r][1] == board[r][2]) {
                return board[r][0] == 'X' ? State.X_WINS : State.O_WINS;
            }
        }

        // columns
        for (int c = 0; c < 3; c++) {
            if (board[0][c] != '\0'
                    && board[0][c] == board[1][c]
                    && board[1][c] == board[2][c]) {
                return board[0][c] == 'X' ? State.X_WINS : State.O_WINS;
            }
        }

        // diagonals
        if (board[0][0] != '\0'
                && board[0][0] == board[1][1]
                && board[1][1] == board[2][2]) {
            return board[0][0] == 'X' ? State.X_WINS : State.O_WINS;
        }

        if (board[0][2] != '\0'
                && board[0][2] == board[1][1]
                && board[1][1] == board[2][0]) {
            return board[0][2] == 'X' ? State.X_WINS : State.O_WINS;
        }

        if (moves == 9) {
            return State.DRAW;
        }

        return State.IN_PROGRESS;
    }
}
