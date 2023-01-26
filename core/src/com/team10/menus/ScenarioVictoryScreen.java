package com.team10.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.team10.game.Eng1Game;
import com.team10.game.Eng1Screen;

public class ScenarioVictoryScreen extends Eng1Screen{

    ChangeScreenButton mainMenuButton;
    BitmapFont font;
    Texture background;

    public ScenarioVictoryScreen(Eng1Game game){
        super(game);
        mainMenuButton = new ChangeScreenButton(25, 25, 150, 50, "Return to Main Menu", batch, camera, game, "mainMenu");
        font = new BitmapFont();
        font.getData().setScale(3, 3);
        background = new Texture(Gdx.files.internal("BlurredMenu.png"));
    }

    @Override
    public void render(float delta) {
        //ScreenUtils.clear(1f, 1f, 1f, 0f);
        batch.begin();
        batch.draw(background, 0, 0, 800, 480);
        font.setColor(Color.BLACK);
        font.draw(this.batch, "You Win", 400 - 80, 350);
        mainMenuButton.update();
        batch.end();
    }
}
