package Tictactoecode.Controller;

import Tictactoecode.Exception.GameOverException;
import Tictactoecode.models.*;
import Tictactoecode.service.winningstrategy.WinningStrategies;
import Tictactoecode.service.winningstrategy.WinningStrategyFactory;

import java.util.List;

public class GameController {
    public Game createGame(int dimension, List<Player> players, WinningStrategies winningStrategy){
          try {
              return Game.builder().
                      dimension(dimension).
                      players(players).
                      winningStrategy(WinningStrategyFactory.getWinningStrategy(winningStrategy, dimension)).
                      build();
          }
          catch(Exception e){
              System.out.println(e.getMessage());
              System.out.println("Cant start game something went wrong");
          }
          return null;
    }

    public void displayBoard(Game game){
         game.getCurrentBoard().printBoard();
    }
    public GameStatus getgameStatus(Game game){
        return game.getGameStatus();
    }
    public Player getGameWinner(Game game){
        return game.getWinner();
    }
    public Move executeMove(Game game, Player player) throws GameOverException {
        //System.out.println(player instanceof Bot);
        Move move=player.makeMove(game.getCurrentBoard(),player);
        game.setNoOfSymbols(game.getNoOfSymbols()+1);
        updateGameStatus(game);
        updateMoves(game,move);
        updateBoardStates(game);
        return move;
    }
    public Board undoMove(Game game,Move lastMove){
        //TODO : Undo Logic here
        return null;
    }
    public void gameReplay(Game game){
        int size=game.getBoardStates().size();
        for(int i=0;i<size;i++){
            game.getBoardStates().get(i).printBoard();
            System.out.println(" " +
                    "------------------------------------" +
                    " ");
        }
    }
    public Player checkWinner(Game game,Move lastPlayedMove){
        Player player=game.getWinningStrategy().checkWinner(game.getCurrentBoard(),lastPlayedMove);
        if(player!=null){
            game.setWinner(player);
            game.setGameStatus(GameStatus.COMPLETED);
           return player;
        }
        return null;
    }
    public void updateMoves(Game game,Move move){
        game.getMoves().add(move);
    }
    public void updateBoardStates(Game game){
         game.getBoardStates().add(new Board(game.getCurrentBoard()));
    }
    public void updateGameStatus(Game game){
        int noOfSymbols=game.getNoOfSymbols();
        int dimension=game.getCurrentBoard().getSize();
        if(noOfSymbols==dimension*dimension){
            game.setGameStatus(GameStatus.DRAW);
        }
    }
}
