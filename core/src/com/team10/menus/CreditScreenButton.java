package com.team10.menus;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;

public class CreditScreenButton extends MenuButton {
    private Texture creditsImg;

    public CreditScreenButton(int xPos, int yPos, int width, int height, String text, SpriteBatch batch, Camera camera) {
        super(xPos, yPos, width, height, text, batch, camera);
        creditsImg = new Texture(Gdx.files.internal("credits.png"));
    }

    @Override
    public void clickFunction() {
        batch.begin();
        batch.draw(creditsImg, xPos, yPos, width, height);
        batch.end();
    }
}