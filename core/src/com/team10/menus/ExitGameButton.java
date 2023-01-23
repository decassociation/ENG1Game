package com.team10.menus;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.team10.game.Eng1Game;

public class ExitGameButton extends MenuButton {

    public ExitGameButton(int xPos, int yPos, int width, int height, String text, SpriteBatch batch, Camera camera, Eng1Game game, String exit) {
        super(xPos, yPos, width, height, text, batch, camera);
    }
}