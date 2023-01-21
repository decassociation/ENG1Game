package com.team10.menus;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.team10.game.Eng1Game;

public class ChangeScreenButton extends MenuButton{
    private final Eng1Game game;
    private final String screen;

    public ChangeScreenButton(int xPos, int yPos, int width, int height, String text, SpriteBatch batch, Camera camera, Eng1Game game, String screen){
        super(xPos, yPos, width, height, text, batch, camera);
        this.game = game;
        this.screen = screen;
    }
    
    /**
     * Change the screen when clicked
     */
    @Override
    public void clickFunction(){
        try{
            game.changeScreen(screen, camera);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
