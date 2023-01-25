package com.team10.menus;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import com.team10.game.Eng1Game;
import com.team10.game.Eng1Screen;
import com.team10.game.FileManager;
import com.badlogic.gdx.graphics.Texture;

public class SettingsMenuScreen extends Eng1Screen{

    FullscreenModeButton fullscreenModeButton;
    ChangeScreenButton mainMenuButton;
    ControlChangeButton upChangeButton;
    ControlChangeButton downChangeButton;
    ControlChangeButton leftChangeButton;
    ControlChangeButton rightChangeButton;
    ControlChangeButton pauseChangeButton;
    ControlChangeButton changeChefButton;
    MenuSlider volumeSlider;
    ArrayList<MenuButton> buttons;

    FileManager fileManager;

    private final Texture Background = new Texture(Gdx.files.internal("BlurredMenu.png"));

    public SettingsMenuScreen(Eng1Game game) {
        super(game);
        fileManager = new FileManager();
        buttons = new ArrayList<>();

        buttons.add(new FullscreenModeButton(25, 405, 150, 50, "fullscreen: " + fileManager.read("fullscreen"), batch, camera));
        buttons.add(new ControlChangeButton(25, 330, 100, 50, "up: " + fileManager.read("up"), batch, camera, "up"));
        buttons.add(new ControlChangeButton(25, 255, 100, 50, "down: " + fileManager.read("down"), batch, camera, "down"));
        buttons.add(new ControlChangeButton(25, 180, 100, 50, "left: " + fileManager.read("left"), batch, camera, "left"));
        buttons.add(new ControlChangeButton(25, 105, 100, 50, "right: " + fileManager.read("right"), batch, camera, "right"));
        buttons.add(new ControlChangeButton(250, 405, 100, 50, "pause: " + fileManager.read("pause"), batch, camera, "pause"));
        buttons.add(new ControlChangeButton(250, 330, 150, 50, "changeChef: " + fileManager.read("changeChef"), batch, camera, "changeChef"));
        buttons.add(new ControlChangeButton(250, 255, 150, 50, "discardItem: " + fileManager.read("discardItem"), batch, camera, "discardItem"));
        buttons.add(new VolumeSlider(575, 20, 200, 20, "volume: " + Integer.toString(Math.round(Float.valueOf(fileManager.read("volume")))), batch, camera));
        buttons.add(new ChangeScreenButton(25, 25, 150, 50, "Return to Main Menu", batch, camera, game, "mainMenu"));

    }

    @Override
    public void render(float delta){
        ScreenUtils.clear(0, 1, 0.5f, 0);
        batch.begin();
        batch.draw(Background, 0, 0, 800, 480);
        for (MenuButton menuButton : buttons) {
            menuButton.update();
        }
        batch.end();
    }

    @Override
    public void show() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
    }
}
