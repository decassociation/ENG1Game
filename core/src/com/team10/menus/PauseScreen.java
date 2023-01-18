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
import com.team10.game.FileManager;

public class PauseScreen extends Eng1Screen {
    // Load the background image
    private final Texture backgroundImage = new Texture(Gdx.files.internal("BlurredMenu.png"));
    BitmapFont font = new BitmapFont();
    MenuButton goMain;
    FileManager fileManager = new FileManager();

    public PauseScreen(Eng1Game game) {
        super(game);

        goMain = new ChangeScreenButton(350, 200, 100, 50, "Main Menu", batch, camera, game, "mainMenu");

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

        if (Gdx.input.isKeyJustPressed(Input.Keys.valueOf(fileManager.read("pause")))) {
            try {
                game.changeScreen("game");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        
    }

    @Override
    public void show() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
    }

    @Override
    public void dispose() {
        // Dispose of the background image to prevent memory leaks
        backgroundImage.dispose();

        // Dispose of the SpriteBatch object
        batch.dispose();
    }
}
