package Tictactoecode.service.winningstrategy;

import Tictactoecode.models.Board;
import Tictactoecode.models.Move;
import Tictactoecode.models.Player;

public interface WinningStrategy {
    Player checkWinner(Board board, Move lastMove);

}
