package com.team10.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.team10.game.Eng1Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
public class PauseScreen implements Screen {
    private final Eng1Game game;
    private Texture backgroundImage;
    private SpriteBatch batch;
    public PauseScreen(Eng1Game game) {
        this.game = game;
        // Load the background image
        backgroundImage = new Texture(Gdx.files.internal("MenuTitle.png"));

        // Create a SpriteBatch object for rendering
        batch = new SpriteBatch();

    }

    @Override
    public void show() {
        // Set the background image as the current screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
    }

    @Override
    public void render(float delta) {
        // Begin rendering
        batch.begin();

        // Get the screen dimensions
        int screenWidth = Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();

        // Draw the background image, scaling it to fit the screen
        batch.draw(backgroundImage, 0, 0, screenWidth, screenHeight);

        // End rendering
        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            try {
                game.changeScreen("game");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) Gdx.app.exit();//allows you to close the game when fullscreen
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
        // Dispose of the background image to prevent memory leaks
        backgroundImage.dispose();

        // Dispose of the SpriteBatch object
        batch.dispose();
    }
}
