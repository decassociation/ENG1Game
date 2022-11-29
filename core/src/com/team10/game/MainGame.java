package com.team10.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainGame extends Game {

	SpriteBatch batch;
	BitmapFont font;

	public void create() {
		batch = new SpriteBatch();

		font = new BitmapFont();
		this.setScreen(new MainMenuScreen(this));
	}

	public void render() {
		super.render();
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}

	public void setScreen(Screen screen) {
		super.setScreen(screen);

	}
}
