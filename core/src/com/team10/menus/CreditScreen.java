package com.team10.menus;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Screen;
import com.team10.game.Eng1Game;

public class CreditScreen implements Screen {
    public SpriteBatch batch;
    public Texture creditsImg;
    public CreditScreen(Eng1Game game) {
        this.batch = game.batch;
        creditsImg = new Texture(Gdx.files.internal("Credits.png"));
    }



    @Override
    public void show() {
        // called when this screen becomes the current screen
    }

    @Override
    public void render(float delta) {
        // called every frame, use this to update and draw the screen
        batch.begin();
        batch.draw(creditsImg, 0, 0);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        // called when this screen is no longer the current screen
        creditsImg.dispose();

    }
}




