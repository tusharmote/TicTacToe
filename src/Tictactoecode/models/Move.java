package Tictactoecode.models;

public class Move {
    private Cell cell;
    private Player player;

    public Move(int i,int j) {
        this.cell = new Cell(i,j);
    }

    public Move(int i,int j,Player player) {
        this.cell = new Cell(i,j);
        this.player=player;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
