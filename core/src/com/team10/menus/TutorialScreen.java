package com.team10.menus;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Screen;
import com.team10.game.Eng1Game;

public class TutorialScreen implements Screen {
    public SpriteBatch batch;
    public Texture TutorialImg;
    private final ChangeScreenButton mainMenuButton;
    private int currentImage = 1;

    public TutorialScreen(Eng1Game game, Camera camera) {
        this.batch = game.batch;
        TutorialImg = new Texture(Gdx.files.internal("Tutorial1.png"));
        mainMenuButton = new ChangeScreenButton(20, 20, 100, 50, "Main Menu", batch, camera, game, "mainMenu");
    }

    @Override
    public void show() {
        // called when this screen becomes the current screen
    }

    @Override
    public void render(float delta) {
        // called every frame, use this to update and draw the screen
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            if (currentImage < 3) {
                currentImage++;
                TutorialImg = new Texture(Gdx.files.internal("Tutorial" + currentImage + ".png"));
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            if (currentImage > 1) {
                currentImage--;
                TutorialImg = new Texture(Gdx.files.internal("Tutorial" + currentImage + ".png"));
            }
        }
        batch.begin();
        batch.draw(TutorialImg, 0, 0, 800, 480);
        mainMenuButton.update();
        batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        // called when this screen is no longer the current screen
        TutorialImg.dispose();
    }
}
