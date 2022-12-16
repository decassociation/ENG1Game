package com.team10.menus;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.team10.game.Eng1Game;
import com.team10.game.GameScreen;

public class StartGameButton extends MenuButton{
    private Eng1Game game;

    /**
     * Constructor for StartGameButton, text is removed as is hardcoded
     * 
     * @param game the Eng1Game object
     */
    public StartGameButton(int xPos, int yPos, int width, int height, SpriteBatch batch, Camera camera, Eng1Game game){
        super(xPos, yPos, width, height, "Start Game", batch, camera);
        this.game = game;
    }
    
    /**
     * Change the screen to the GameScreen when clicked
     */
    @Override
    public void clickFunction(){
        game.setScreen(new GameScreen(game));
    }
}
