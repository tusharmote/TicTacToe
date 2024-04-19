package Tictactoecode.service.winningstrategy;

import Tictactoecode.models.Board;
import Tictactoecode.models.Move;
import Tictactoecode.models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneWinningStrategy implements WinningStrategy {

    private int dimension;
    private List<HashMap<Character,Integer>> rowHashMaps;
    private List<HashMap<Character,Integer>> colHashMaps;
    private HashMap<Character,Integer> topLeftHashMap;
    private HashMap<Character,Integer> topRightHashMap;
    private HashMap<Character,Integer> cornerHashMap;

    public OrderOneWinningStrategy(int dimension){
        this.dimension = dimension;
        this.rowHashMaps = new ArrayList<>();
        this.colHashMaps = new ArrayList<>();
        this.topLeftHashMap = new HashMap<>();
        this.topRightHashMap = new HashMap<>();
        this.cornerHashMap = new HashMap<>();
        for(int i=0;i<dimension;i++){
            rowHashMaps.add(new HashMap<>());
            colHashMaps.add(new HashMap<>());
        }
    }

    public boolean isTopLeftDiagonal(int row,int col){
        return row==col;
    }
    public boolean isTopRightDiagonal(int row,int col){
        return (row+col)==dimension-1;
    }
    public boolean isCorner(int row,int col){
        if(row==0 || row==dimension-1)
           return col==0 || col==dimension-1;
        return false;
    }
    @Override
    public Player checkWinner(Board board, Move lastMove) {
        Player player=lastMove.getPlayer();
        char symbol=player.getSymbol();
        int row=lastMove.getCell().getRow();
        int col=lastMove.getCell().getCol();
        if(checkRowWin(row,col,symbol))
            return player;
        else if (checkColWin(row,col,symbol))
            return player;
        else if(isTopLeftDiagonal(row, col) && checkTopLeftDiagonalWin(row,col,symbol) )
            return player;
        else if(isTopRightDiagonal(row, col) && checkTopRightDiagonalWin(row,col,symbol) )
            return player;
        else if(isCorner(row, col) && checkCornerWin(row,col,symbol) )
            return player;

        return null;

    }
    private boolean checkRowWin(int row,int col,char symbol){
        // if value is not present then insert
        rowHashMaps.get(row).putIfAbsent(symbol,0);
        //for new entry,update the count in hashmap
        rowHashMaps.get(row).put(symbol,rowHashMaps.get(row).get(symbol)+1);
        return rowHashMaps.get(row).get(symbol)==dimension;
    }
    private boolean checkColWin(int row,int col,char symbol){
        colHashMaps.get(col).putIfAbsent(symbol,0);
        colHashMaps.get(col).put(symbol,colHashMaps.get(col).get(symbol)+1);
        return colHashMaps.get(col).get(symbol)==dimension;

    }
   private boolean checkTopLeftDiagonalWin(int row,int col,char symbol){
        topLeftHashMap.putIfAbsent(symbol,0);
        topLeftHashMap.put(symbol,topLeftHashMap.get(symbol)+1);
        return topLeftHashMap.get(symbol)==dimension;

    }
    private boolean checkTopRightDiagonalWin(int row,int col,char symbol){
        topRightHashMap.putIfAbsent(symbol,0);
        topRightHashMap.put(symbol,topRightHashMap.get(symbol)+1);
        return topRightHashMap.get(symbol)==dimension;

    }
    private boolean checkCornerWin(int row,int col,char symbol){
        cornerHashMap.putIfAbsent(symbol,0);
        cornerHashMap.put(symbol,cornerHashMap.get(symbol)+1);
        return cornerHashMap.get(symbol)==4;

    }

}
