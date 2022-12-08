package com.team10.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
public class PauseScreen implements Screen {
    private final Eng1Game game;

    public PauseScreen(Eng1Game game) {
        this.game = game;


    }

    @Override
    public void show() {
        //when p is pressed show an image
        if (Gdx.input.isKeyPressed(Input.Keys.P)) {

        }
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 100, 0);

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            try {
                game.changeScreen("game");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
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

    }
}
