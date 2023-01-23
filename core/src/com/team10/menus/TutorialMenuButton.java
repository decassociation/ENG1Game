package com.team10.menus;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.team10.game.Eng1Game;

public class TutorialMenuButton extends MenuButton {
    private final Texture TutorialImg;
    private final Eng1Game game;
    public TutorialMenuButton(int xPos, int yPos, int width, int height, String text, Eng1Game game) {
        super(xPos, yPos, width, height, text);
        TutorialImg = new Texture(Gdx.files.internal("BlurredMenu.png"));
        this.game = game;
    }

    @Override
    public void clickFunction() {
        game.setScreen(new TutorialScreen(game, camera));
    }
}