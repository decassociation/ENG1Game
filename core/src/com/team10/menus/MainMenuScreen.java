package com.team10.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.team10.game.Eng1Game;
import com.team10.game.Eng1Screen;
import com.team10.game.GameScreen;

public class MainMenuScreen extends Eng1Screen {
    MenuButton startGame;
    MenuButton settingsMenuButton;
    MenuButton creditsMenuButton;

    ExitGameButton exitGameButton;

    MenuButton tutorialButton;

    private final Texture backgroundImage = new Texture(Gdx.files.internal("MenuTitle.png"));

    public MainMenuScreen(Eng1Game game) {
        super(game);
        startGame = new ChangeScreenButton(350, 200, 100, 50, "Start Game", batch, camera, game, "game");
        settingsMenuButton = new ChangeScreenButton(350, 125, 100, 50, "Settings", batch, camera, game, "settings");
        creditsMenuButton = new ChangeScreenButton(350, 50, 100, 50, "Credits", batch, camera, game, "credits");
        tutorialButton = new ChangeScreenButton(100, 350, 100, 50, "Tutorial", batch, camera, game, "tutorial");
        exitGameButton = new ExitGameButton(100, 275, 100, 50, "Exit", batch, camera, game, "Exit");
    }

    private void setScreen(GameScreen gameScreen) {
        try{
            game.changeScreen("game", camera);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void render(float delta) {
        batch.begin();
        batch.draw(backgroundImage, 0, 0, 800, 480);
        startGame.update();
        settingsMenuButton.update();
        creditsMenuButton.update();
        tutorialButton.update();
        exitGameButton.update();
        batch.end();
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) Gdx.app.exit();//allows you to close the game when fullscreen
    }

    @Override
    public void show() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
    }

    public void dispose() {
        // Dispose of the background image to prevent memory leaks
        backgroundImage.dispose();

        // Dispose of the spriteBatch object
        batch.dispose();
    }
}
