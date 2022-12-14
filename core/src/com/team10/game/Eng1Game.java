package com.team10.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.team10.menus.MainMenuScreen;
import com.team10.menus.PauseScreen;

public class Eng1Game extends Game {

    private Screen gameScreen;
    private Screen mainMenuScreen;
    private Screen pauseScreen;
    @Override
    public void create() {
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
            default:
                throw new Exception("Invalid screen name");
        }
    }

    public void setScreen(Screen screen) {
        super.setScreen(screen);
        System.out.println("Screen changed to: " + screen.getClass().getSimpleName());
    }
}
