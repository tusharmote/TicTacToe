package Tictactoecode.service.botplayingstrategy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategy(){
        //TODO: Create enum with botplayingstrategies and use switch case to return the appropriate strategy
        return new RandomBotPlayingStrategy();
    }
}
