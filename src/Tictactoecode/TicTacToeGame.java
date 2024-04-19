package Tictactoecode;

import Tictactoecode.Controller.GameController;
import Tictactoecode.Exception.GameOverException;
import Tictactoecode.models.*;
import Tictactoecode.service.winningstrategy.WinningStrategies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {
    public static void main(String[] args) throws GameOverException {
        Scanner sc = new Scanner(System.in);
        GameController gameController = new GameController();
       //gameController.gameReplay(game);
        System.out.println("Enter the dimensions of the board ");
        int dimension = sc.nextInt();
        System.out.println("Will there be a BOT  (Y/N)");
        String isBotPresent = sc.next();
        List<Player> players = new ArrayList<>();
        int iteratorNumber = dimension - 1;
        if (isBotPresent.equalsIgnoreCase("y"))
            iteratorNumber -= 1;
        for (int i = 1; i <= iteratorNumber; i++) {
            System.out.println("Enter the name of the Player " + i);
            String playerName = sc.next();
            System.out.println("Enter the symbol of the Player " + i);
            String symbol = sc.next();
            players.add(new Player(i, playerName, symbol.charAt(0), PlayerType.HUMAN));

        }
        if (isBotPresent.equalsIgnoreCase("y")) {
            System.out.println("Enter the name of the Bot");
            String playerName = sc.next();
            System.out.println("Enter the symbol of the Bot");
            String symbol = sc.next();
            //TODO : get the bot difficulty level input and return the strategy accordingly

            players.add(new Bot(dimension, playerName, symbol.charAt(0), PlayerType.BOT, BotDifficultyLevel.EASY));
        }
        // randomises who will play first and the order
        Collections.shuffle(players);
        Game game = gameController.createGame(dimension, players, WinningStrategies.ORDERONEWINNINGSTRATEGY);
        //gameController.gameReplay(game);
       // System.out.println(game.getBoardStates().size());
        int playerIndex = -1;
        String isUndoRequired="n";
        //gameController.gameReplay(game);
        while (gameController.getgameStatus(game).equals(GameStatus.IN_PROGRESS)) {
            System.out.println("Current Board Status");
            gameController.displayBoard(game);
            gameController.updateBoardStates(game);
            playerIndex++;
            playerIndex = playerIndex % players.size();
            //System.out.println(players.get(playerIndex) instanceof Bot);
            Move movePlayed = gameController.executeMove(game, players.get(playerIndex));
            if(players.get(playerIndex) instanceof Bot ) continue;
            System.out.println("Do you want to undo (Y/N)");
            isUndoRequired = sc.next();

            if (isUndoRequired.equalsIgnoreCase("y")) {
                gameController.undoMove(game, movePlayed);
            }
            Player winner = gameController.checkWinner(game, movePlayed);
            if (winner != null) {
                System.out.println("Winner is :" + winner.getName());
                break;

            }//System.out.println( "total boards "+ game.getBoardStates().size());
        }
            System.out.println("FINAL GAME STATE");
            gameController.displayBoard(game);
            System.out.println("Do you want a replay? Y/N");
            String isReplayRequired = sc.next();
            if (isReplayRequired.equalsIgnoreCase("y"))
                gameController.gameReplay(game);


        }

}
