package com.team10.menus;

import com.team10.game.Eng1Game;
import com.team10.game.GameScreen;

public class StartGameButton extends MenuButton{
    private Eng1Game game;
    public StartGameButton(int xPos, int yPos, int width, int height, String text, Eng1Game game){
        super(xPos, yPos, width, height, text);
        this.game = game;
    }
    
    @Override
    public void clickFunction(){
        game.setScreen(new GameScreen(game));
    }
}
