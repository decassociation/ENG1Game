package com.team10.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.team10.menus.*;
import com.badlogic.gdx.audio.Music;


public class Eng1Game extends Game {

    public SpriteBatch batch;
    private Screen gameScreen;
    private Screen mainMenuScreen;
    private Screen pauseScreen;
    private Screen settingsMenuScreen;
    private Screen creditScreen;
    private Screen tutorialScreen;
    private Screen scenarioVictoryScreen;
    private Music KitchenMusic;
    @Override
    public void create() {
        setScreen(new MainMenuScreen(this));
        SoundManager.create();      // initialise the sound manager for use throughout the game

        // Get the dimensions of the desktop screen
        int screenWidth = Gdx.graphics.getDisplayMode().width;
        int screenHeight = Gdx.graphics.getDisplayMode().height;
        // Set the window size to the dimensions of the desktop screen
        screen.render(screenWidth);
        screen.render(screenHeight);
        setScreen(new MainMenuScreen(this));

        SoundManager.playKitchenMusic();
        batch = new SpriteBatch();
        setScreen(new MainMenuScreen(this));

    }

        //this sets up each different screen, allowing us to switch between them, which is implemented using buttons
    public void changeScreen(String screenName, Camera camera) throws Exception {
        switch (screenName) {
            case "game":
                if (gameScreen == null) {
                    gameScreen = new GameScreen(this);
                }
                setScreen(gameScreen);
                
            case "mainMenu":
                if (mainMenuScreen == null) {
                    mainMenuScreen = new MainMenuScreen(this);
                }
                setScreen(mainMenuScreen);
                
            case "pause":
                if (pauseScreen == null) {
                    pauseScreen = new PauseScreen(this);
                }
                setScreen(pauseScreen);
                
            case "settings":
                if (settingsMenuScreen == null) {
                    settingsMenuScreen = new SettingsMenuScreen(this);
                }
                setScreen(settingsMenuScreen);
                
            case "credits":
                if (creditScreen == null) {
                    creditScreen = new CreditScreen(this, camera);
                }
                setScreen(creditScreen);

            case "tutorial":
                if (tutorialScreen == null) {
                    tutorialScreen = new TutorialScreen(this, camera);
                }
                setScreen(tutorialScreen);
                break;

            case "scenarioVictory":
                if (scenarioVictoryScreen == null) {
                    scenarioVictoryScreen = new ScenarioVictoryScreen(this, camera);
                }
                setScreen(scenarioVictoryScreen);
                break;

            default: throw new Exception("Invalid screen name");
        }
    }


    public void setScreen(Screen screen) {
        super.setScreen(screen);
        System.out.println("Screen changed to: " + screen.getClass().getSimpleName());
    }

    public void dispose() {
        KitchenMusic.dispose();
    }
}
