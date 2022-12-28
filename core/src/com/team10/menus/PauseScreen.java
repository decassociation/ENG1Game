package com.team10.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.team10.game.Eng1Game;
import com.team10.game.Eng1Screen;

public class PauseScreen extends Eng1Screen {
    // Load the background image
    private final Texture backgroundImage = new Texture(Gdx.files.internal("PauseTitle.png"));
    BitmapFont font = new BitmapFont();
    MenuButton goMain;

    public PauseScreen(Eng1Game game) {
        super(game);

        goMain = new ChangeScreenButton(350, 200, 100, 50, "Return to Main Menu", batch, camera, game, "mainMenu");

    }

    @Override
    public void render(float delta) {
        // Begin rendering
        batch.begin();

        // Draw the background image
        batch.draw(backgroundImage, 0, 0, 800, 480);

        // Draw return to main menu button
        goMain.draw();


        // End rendering
        batch.end();

        goMain.onClick();

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
    public void dispose() {
        // Dispose of the background image to prevent memory leaks
        backgroundImage.dispose();

        // Dispose of the SpriteBatch object
        batch.dispose();
    }
}
