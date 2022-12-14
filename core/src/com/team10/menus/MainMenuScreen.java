package com.team10.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.team10.game.Eng1Game;
import com.team10.game.Eng1Screen;
import com.team10.game.GameScreen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class MainMenuScreen extends Eng1Screen {
    MenuButton startGame;
    MenuButton settingsMenuButton;
    BitmapFont font;

    private final Texture backgroundImage = new Texture(Gdx.files.internal("MenuTitle.png"));

    public MainMenuScreen(Eng1Game game) {
        super(game);
        startGame = new ChangeScreenButton(350, 200, 100, 50, "Start Game", batch, camera, game, "game");
        settingsMenuButton = new ChangeScreenButton(350, 125, 100, 50, "Settings", batch, camera, game, "settings");
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
        settingsMenuButton.draw();
        batch.end();
        startGame.onClick();
        settingsMenuButton.onClick();
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) Gdx.app.exit();//allows you to close the game when fullscreen
    }

    @Override
    public void show() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
    }

    public void dispose() {
        // Dispose of the background image to prevent memory leaks
        backgroundImage.dispose();

        // Dispose of the SpriteBatch object
        batch.dispose();
    }
}