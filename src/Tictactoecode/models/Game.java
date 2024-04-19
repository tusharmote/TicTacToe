package Tictactoecode.models;

import Tictactoecode.Exception.DupliacteSymbolException;
import Tictactoecode.Exception.InvalidBoardSizeException;
import Tictactoecode.Exception.InvalidBotCountException;
import Tictactoecode.Exception.InvalidNoofPlayersException;
import Tictactoecode.service.winningstrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;



public class Game {

    private Board currentBoard;
    private List<Player> players;
    private Player currentPlayer;
    private GameStatus gameStatus;
    private Player Winner;
    private List<Move> moves;
    private List<Board> boardStates;
    private WinningStrategy winningStrategy;
    private BotPlayingStrategy botPlayingStrategy;
    private int noOfSymbols;
    private Game(Board currentBoard, List<Player> players, WinningStrategy winningStrategy) {
        this.currentBoard = currentBoard;
        this.players = players;
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.moves = new ArrayList<Move>();
        this.boardStates = new ArrayList<Board>();
        this.winningStrategy = winningStrategy;
        this.noOfSymbols=0;
    }

    public Board getCurrentBoard() {
        return currentBoard;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public Player getWinner() {
        return Winner;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public List<Board> getBoardStates() {
        return boardStates;
    }

    public WinningStrategy getWinningStrategy() {
        return winningStrategy;
    }

    public BotPlayingStrategy getBotPlayingStrategy() {
        return botPlayingStrategy;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void setWinner(Player winner) {
        Winner = winner;
    }

    public int getNoOfSymbols() {
        return noOfSymbols;
    }

    public void setNoOfSymbols(int noOfSymbols) {
        this.noOfSymbols = noOfSymbols;
    }

    public static Builder builder(){
        return new Builder();
    }
    public static class Builder{
        private int dimension;
        private List<Player> players;
        private WinningStrategy winningStrategy;

        public Builder dimension(int dimension) {
            this.dimension = dimension;
            return this;

        }

        public Builder players(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder winningStrategy(WinningStrategy winningStrategy) {
            this.winningStrategy = winningStrategy;
            return this;
        }

        public void validateBotCount () throws InvalidBotCountException {
            int botCount=0;
            for(Player player:players){
                if(player.getPlayertype().equals(PlayerType.BOT)){
                    botCount++;
                }
            }
            if (botCount>1){
                throw new InvalidBotCountException("Bot count cannot be more than 1. Current Count is "+ botCount);
            }
        }
        public void validateBoardSize () throws InvalidBoardSizeException{
            if(dimension<3 || dimension>10){
                throw new InvalidBoardSizeException("Board size cannot be <3 and >10.Current size "+ dimension);
            }

        }
        public void validatePlayerSymbol() throws DupliacteSymbolException{
            HashSet<Character> set=new HashSet<>();
            for(Player player:players){
                set.add(player.getSymbol());
            }
            if(set.size()!=players.size()){
                throw new DupliacteSymbolException("Duplicate Symbols not allowed");
            }
        }
        public void validateNoofPlayers() throws InvalidNoofPlayersException{
            if(players.size() != dimension -1){
                throw new InvalidNoofPlayersException("Number of payers is invalid");
            }
        }

        public  void validate() throws InvalidBotCountException, InvalidBoardSizeException, DupliacteSymbolException, InvalidNoofPlayersException {
            validateBotCount() ;
            validateBoardSize();
            validatePlayerSymbol();
            validateNoofPlayers();

        }

        public Game build() throws InvalidBotCountException, InvalidNoofPlayersException, InvalidBoardSizeException, DupliacteSymbolException {
            validate();
            return  new Game(new Board(dimension),players,winningStrategy);


        }
    }


}
