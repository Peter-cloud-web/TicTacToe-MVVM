package husaynhakeem.io.tictactoe_mvvm.viewmodel;


import android.databinding.ObservableArrayMap;
import android.util.Log;

import java.util.Observable;

import husaynhakeem.io.tictactoe_mvvm.model.Cell;
import husaynhakeem.io.tictactoe_mvvm.model.Game;
import husaynhakeem.io.tictactoe_mvvm.model.Player;

import static husaynhakeem.io.tictactoe_mvvm.utilities.StringUtility.stringFromNumbers;

public class GameViewModel extends Observable {

    private static final String TAG = GameViewModel.class.getSimpleName();
    public ObservableArrayMap<String, String> cells = new ObservableArrayMap<>();
    public Game game;


    public GameViewModel(String player1, String player2) {
        game = new Game();
        beginGame(new Player(player1, "x"), new Player(player2, "o"));
    }


    public void beginGame(Player player1, Player player2) {
        game.player1 = player1;
        game.player2 = player2;
        game.currentPlayer = player1;
    }


    public void onClickedCellAt(int row, int column) {
        game.cells[row][column] = new Cell(game.currentPlayer);
        cells.put(stringFromNumbers(row, column), game.currentPlayer.value);
        if (game.hasGameEnded()) {
            game.winner = game.currentPlayer;
            Log.e(TAG, "Winner is: " + game.winner.name);
            setChanged();
            notifyObservers(game.winner.name);
        }
        game.switchPlayer();
    }


    public void reset() {
        cells.clear();
        game.restart();
    }
}
