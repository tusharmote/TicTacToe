package Tictactoecode.models;

import Tictactoecode.Exception.GameOverException;

import java.util.Scanner;

public class Player {
    private String name;
    private char symbol;
    private int id;
    private PlayerType Playertype;

    public Player(int id, String name, char symbol, PlayerType playertype) {
        this.name = name;
        this.symbol = symbol;
        this.id = id;
        Playertype = playertype;
    }

    public Move makeMove(Board board,Player player) throws GameOverException {
        Scanner sc=new Scanner(System.in);
        int dimension=board.getSize();
        System.out.println("Enter the row for the move " + player.getName());
        int row=sc.nextInt();
        if(row<0 || row>=dimension){
            System.out.println("Invalid row number please enter valid row number");
             row=sc.nextInt();
        }
        System.out.println("Enter the col for the move " + player.getName());
        int col=sc.nextInt();
        if(col<0 || col>=dimension){
            System.out.println("Invalid row number please enter valid row number");
            col=sc.nextInt();
        }
        //TODO- validations for row,col and cellstate
        board.getBoard().get(row).get(col).setCellState(CellState.FILLED);
        board.getBoard().get(row).get(col).setPlayer(this);
        return new Move(row,col,this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PlayerType getPlayertype() {
        return Playertype;
    }

    public void setPlayertype(PlayerType playertype) {
        Playertype = playertype;
    }
}
