package Tictactoecode.service.winningstrategy;

public class WinningStrategyFactory {
    public static WinningStrategy getWinningStrategy(WinningStrategies winningStrategy,int dimension){
        //TODO: Create enum with Winningstrategies and use switch case to return the appropriate strategy
        return new OrderOneWinningStrategy(dimension);

    }
}
