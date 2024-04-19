package Tictactoecode.models;

import Tictactoecode.Exception.GameOverException;
import Tictactoecode.service.botplayingstrategy.BotPlayingStrategy;
import Tictactoecode.service.botplayingstrategy.BotPlayingStrategyFactory;

public class Bot extends Player {
    private BotDifficultyLevel botDifficultyLevel;

    public Bot(int id, String name, char symbol, PlayerType playertype) {
        super(id, name, symbol, playertype);
    }

    public Bot(int id, String name, char symbol, PlayerType playertype, BotDifficultyLevel botDifficultyLevel) {
        super(id, name, symbol, playertype);
        this.botDifficultyLevel = botDifficultyLevel;
    }
    public Move makeMove(Board board,Player player) throws GameOverException {
        BotPlayingStrategy botPlayingStrategy= BotPlayingStrategyFactory.getBotPlayingStrategy();
        return botPlayingStrategy.makeMove(board,this);
    }
}
