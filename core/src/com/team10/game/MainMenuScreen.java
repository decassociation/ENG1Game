package com.team10.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class MainMenuScreen implements Screen {
    private Eng1Game game;
    public MainMenuScreen(Eng1Game game) {
        this.game = game;
    }

    public void show() {

    }

    private void setScreen(GameScreen gameScreen) {
        try{
            game.changeScreen("game");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void render(float delta) {
        // TODO: Set screen to game screen when the mouse is clicked
        if(Gdx.input.isTouched()){
            setScreen(new GameScreen(game));
        }
    }

    public void resize(int width, int height) {

    }

    public void pause() {

    }

    public void resume() {

    }

    public void hide() {

    }

    public void dispose() {

    }
}