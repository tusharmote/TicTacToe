package Tictactoecode.service.botplayingstrategy;

import Tictactoecode.Exception.GameOverException;
import Tictactoecode.models.Board;
import Tictactoecode.models.Move;
import Tictactoecode.models.Player;

public interface BotPlayingStrategy {
    Move makeMove(Board board, Player player) throws GameOverException;
}
