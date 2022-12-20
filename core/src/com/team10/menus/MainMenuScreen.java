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
    private Texture backgroundImage;

    public MainMenuScreen(Eng1Game game) {
        // Load the background image
        backgroundImage = new Texture(Gdx.files.internal("MenuTitle.png"));

        // Create a SpriteBatch object for rendering
        batch = new SpriteBatch();

        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);


        startGame = new StartGameButton(350, 200, 100, 50, batch, camera, game);



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
        // Begin rendering
        batch.begin();

        // Get the screen dimensions
        int screenWidth = Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();

        // Get the dimensions of the image
        float imageWidth = backgroundImage.getWidth();
        float imageHeight = backgroundImage.getHeight();

        // Calculate the scale factor for the image
        float scaleFactor = Math.min(screenWidth / imageWidth, screenHeight / imageHeight);

        // Calculate the scaled dimensions of the image
        float scaledWidth = imageWidth * scaleFactor;
        float scaledHeight = imageHeight * scaleFactor;

        // Calculate the position of the image on the screen
        float x = (screenWidth - scaledWidth) / 2;
        float y = (screenHeight - scaledHeight) / 2;
        // Draw the background image, scaling and positioning it to fit the screen
        batch.draw(backgroundImage, x, y, scaledWidth, scaledHeight);

        // Draw the sprite
        startGame.draw(batch);

        // End rendering
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

    }
}