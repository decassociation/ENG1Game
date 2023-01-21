package com.team10.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.team10.menus.ChangeScreenButton;
import com.team10.menus.MenuButton;

public class Eng1Screen implements Screen{

    protected SpriteBatch batch;
    protected OrthographicCamera camera;
    protected MenuButton startGame;
    protected Eng1Game game;


    public Eng1Screen(Eng1Game game) {
        // Create a SpriteBatch object for rendering
        batch = new SpriteBatch();
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void show() {
        
    }

    @Override
    public void render(float delta) {
        batch = new SpriteBatch();
        
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        
    }
    
}
