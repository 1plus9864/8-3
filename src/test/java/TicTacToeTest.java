import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeTest {

    @Test
    void newGame_startsWithX() {
        TicTacToe game = new TicTacToe();
        assertEquals(TicTacToe.Player.X, game.getNextPlayer());
        assertEquals(TicTacToe.State.IN_PROGRESS, game.getState());
    }

    @Test
    void playersAlternate() {
        TicTacToe game = new TicTacToe();
        game.set(0, 0);
        assertEquals(TicTacToe.Player.O, game.getNextPlayer());
        game.set(1, 1);
        assertEquals(TicTacToe.Player.X, game.getNextPlayer());
    }

    @Test
    void xWinsByRow() {
        TicTacToe game = new TicTacToe();
        game.set(0,0);
        game.set(1,0);
        game.set(0,1);
        game.set(1,1);
        game.set(0,2);
        assertEquals(TicTacToe.State.X_WINS, game.getState());
    }

    @Test
   
    void oWinsByDiagonal() {
        TicTacToe game = new TicTacToe();
        game.set(0, 0); // X
        game.set(0, 2); // O
        game.set(0, 1); // X
        game.set(1, 1); // O
        game.set(2, 2); // X
        game.set(2, 0); // O -> O wins (0,2)(1,1)(2,0)
        assertEquals(TicTacToe.State.O_WINS, game.getState());
    }


    @Test
    void drawGame() {
        TicTacToe game = new TicTacToe();
        // X O X
        // X X O
        // O X O
        game.set(0,0);
        game.set(0,1);
        game.set(0,2);
        game.set(1,2);
        game.set(1,0);
        game.set(2,0);
        game.set(1,1);
        game.set(2,2);
        game.set(2,1);
        assertEquals(TicTacToe.State.DRAW, game.getState());
    }

    @Test
    void cannotPlaceOnOccupiedCell() {
        TicTacToe game = new TicTacToe();
        game.set(0,0);
        assertThrows(IllegalArgumentException.class,
                () -> game.set(0,0));
    }
}
