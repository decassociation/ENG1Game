package com.team10.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import com.team10.game.Eng1Game;
import com.team10.game.Eng1Screen;
import com.team10.game.FileManager;

public class SettingsMenuScreen extends Eng1Screen{

    FullscreenModeButton fullscreenModeButton;
    ChangeScreenButton mainMenuButton;
    FileManager fileManager;

    public SettingsMenuScreen(Eng1Game game) {
        super(game);
        fileManager = new FileManager("config/desktop_settings.txt");
        fullscreenModeButton = new FullscreenModeButton(25, 405, 100, 50, "fullscreen: " + fileManager.read("fullscreen"), batch, camera);
        mainMenuButton = new ChangeScreenButton(25, 25, 100, 50, "Return to Main Menu", batch, camera, game, "mainMenu");
    }

    @Override
    public void render(float delta){
        ScreenUtils.clear(0, 1, 0.5f, 0);

        batch.begin();
        fullscreenModeButton.draw();
        mainMenuButton.draw();
        batch.end();

        fullscreenModeButton.onClick();
        mainMenuButton.onClick();
    }

    @Override
    public void show() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
    }
    
}
