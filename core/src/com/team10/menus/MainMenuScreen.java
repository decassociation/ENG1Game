package com.team10.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.team10.game.Eng1Game;
import com.team10.game.GameScreen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;



public class MainMenuScreen implements Screen {
    OrthographicCamera camera;
    private Eng1Game game;
    SpriteBatch batch;
    MenuButton startGame;
    BitmapFont font;
    public MainMenuScreen(Eng1Game game) {
        this.game = game;

        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        startGame = new StartGameButton(350, 200, 100, 50, batch, camera, game);
        

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
        ScreenUtils.clear(100, 0, 0, 0);

        batch.begin();
        startGame.draw();
        batch.end();

        /*
        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
        */

        startGame.onClick();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) Gdx.app.exit();//allows you to close the game when fullscreen
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