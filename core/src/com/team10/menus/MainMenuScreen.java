package com.team10.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.team10.game.Eng1Game;
import com.team10.game.GameScreen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class MainMenuScreen implements Screen {
    OrthographicCamera camera;
    private Eng1Game game;
    SpriteBatch batch;
    MenuButton startGame;
    BitmapFont font;

    private final Texture backgroundImage = new Texture(Gdx.files.internal("MenuTitle.png"));

    public MainMenuScreen(Eng1Game game) {
        // Create a SpriteBatch object for rendering
        batch = new SpriteBatch();
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        startGame = new ChangeScreenButton(350, 200, 100, 50, "Start Game", batch, camera, game, "game");
    }

    public void show() {
        // Set the background image as the current screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
    }

    private void setScreen(GameScreen gameScreen) {
        try{
            game.changeScreen("game");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void render(float delta) {
        batch.begin();
        batch.draw(backgroundImage, 0, 0, 800, 480);
        startGame.draw();
        batch.end();
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
        // Dispose of the background image to prevent memory leaks
        backgroundImage.dispose();

        // Dispose of the SpriteBatch object
        batch.dispose();
    }
}