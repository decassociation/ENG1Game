package com.team10.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Screen;
import com.team10.menus.MainMenuScreen;
import com.team10.menus.PauseScreen;
import com.team10.menus.SettingsMenuScreen;

public class Eng1Game extends Game {

    private Screen gameScreen;
    private Screen mainMenuScreen;
    private Screen pauseScreen;
    private Screen settingsMenuScreen;


    @Override
    public void create() {
        setScreen(new MainMenuScreen(this));

        // Get the dimensions of the desktop screen
        int screenWidth = Gdx.graphics.getDisplayMode().width;
        int screenHeight = Gdx.graphics.getDisplayMode().height;
        // Set the window size to the dimensions of the desktop screen
        screen.render(screenWidth);
        screen.render(screenHeight);
        setScreen(new MainMenuScreen(this));
    }

    public void changeScreen(String screenName) throws Exception {
        switch (screenName){
            case "game":
                if(gameScreen == null){
                    gameScreen = new GameScreen(this);
                }
                setScreen(gameScreen);
                break;
            case "mainMenu":
                if(mainMenuScreen == null){
                    mainMenuScreen = new MainMenuScreen(this);
                }
                setScreen(mainMenuScreen);
                break;
            case "pause":
                if(pauseScreen == null){
                    pauseScreen = new PauseScreen(this);
                }
                setScreen(pauseScreen);
                break;
            case "settings":
                if(settingsMenuScreen == null){
                    settingsMenuScreen = new SettingsMenuScreen(this);
                }
                setScreen(settingsMenuScreen);
                break;
            default:
                throw new Exception("Invalid screen name");
        }
    }

    public void setScreen(Screen screen) {
        super.setScreen(screen);
        System.out.println("Screen changed to: " + screen.getClass().getSimpleName());
    }
}
