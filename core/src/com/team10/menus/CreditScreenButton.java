package com.team10.menus;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.team10.game.Eng1Game;

public class CreditScreenButton extends MenuButton {
    private final Texture creditsImg;
    private final Eng1Game game;
    public CreditScreenButton(int xPos, int yPos, int width, int height, String text, Eng1Game game) {
        super(xPos, yPos, width, height, text);
        creditsImg = new Texture(Gdx.files.internal("Credits.png"));
        this.game = game;
    }

    @Override
    public void clickFunction() {
        game.setScreen(new CreditScreen(game, camera));
    }
}