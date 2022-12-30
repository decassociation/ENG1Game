package com.team10.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import com.team10.game.Eng1Game;
import com.team10.game.Eng1Screen;
import com.team10.game.FileManager;

public class SettingsMenuScreen extends Eng1Screen{

    FullscreenModeButton fullscreenModeButton;
    ChangeScreenButton mainMenuButton;
    ControlChangeButton upChangeButton;
    ControlChangeButton downChangeButton;
    ControlChangeButton leftChangeButton;
    ControlChangeButton rightChangeButton;
    ControlChangeButton pauseChangeButton;
    ControlChangeButton changeChefButton;

    FileManager fileManager;

    public SettingsMenuScreen(Eng1Game game) {
        super(game);
        fileManager = new FileManager("ENG1Game/config/desktop_settings.txt");

        fullscreenModeButton = new FullscreenModeButton(25, 405, 100, 50, "fullscreen: " + fileManager.read("fullscreen"), batch, camera);
        upChangeButton = new ControlChangeButton(25, 330, 100, 50, "up: " + fileManager.read("up"), batch, camera, "up");
        downChangeButton = new ControlChangeButton(25, 255, 100, 50, "down: " + fileManager.read("down"), batch, camera, "down");
        leftChangeButton = new ControlChangeButton(25, 180, 100, 50, "left: " + fileManager.read("left"), batch, camera, "left");
        rightChangeButton = new ControlChangeButton(25, 105, 100, 50, "right: " + fileManager.read("right"), batch, camera, "right");
        pauseChangeButton = new ControlChangeButton(150, 405, 100, 50, "pause: " + fileManager.read("pause"), batch, camera, "pause");
        changeChefButton = new ControlChangeButton(150, 330, 100, 50, "changeChef: " + fileManager.read("changeChef"), batch, camera, "changeChef");

        mainMenuButton = new ChangeScreenButton(25, 25, 100, 50, "Return to Main Menu", batch, camera, game, "mainMenu");
    }

    @Override
    public void render(float delta){
        ScreenUtils.clear(0, 1, 0.5f, 0);

        batch.begin();
        fullscreenModeButton.draw();
        upChangeButton.draw();
        downChangeButton.draw();
        leftChangeButton.draw();
        rightChangeButton.draw();
        pauseChangeButton.draw();
        changeChefButton.draw();
        mainMenuButton.draw();
        batch.end();

        fullscreenModeButton.onClick();
        upChangeButton.onClick();
        upChangeButton.getInput();
        downChangeButton.onClick();
        downChangeButton.getInput();
        leftChangeButton.onClick();
        leftChangeButton.getInput();
        rightChangeButton.onClick();
        rightChangeButton.getInput();
        pauseChangeButton.onClick();
        pauseChangeButton.getInput();
        changeChefButton.onClick();
        changeChefButton.getInput();
        mainMenuButton.onClick();
    }

    @Override
    public void show() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
    }
    
}
