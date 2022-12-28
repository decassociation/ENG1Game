package com.team10.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen extends Eng1Screen {
    private Boolean paused = false;   //Useful for when we want to implement a pause function

    private ChefController chefController;
    private Texture img;

    private Boolean mainmenu;
    public GameScreen(Eng1Game game) {
        super(game);

        camera.setToOrtho(false, 1850, 1040);
        img = new Texture("layout.png"); //img is the background image

        chefController = new ChefController();

        mainmenu = false;

    }

    @Override
    public void show() {

    }


    public void drawStuff(){
        ScreenUtils.clear(1, 0, 0, 1);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(img, 0, 0);
        chefController.drawChefs(batch);
        batch.end();
    }

    private void gameLogic(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            try {
                game.changeScreen("pause");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(!mainmenu){
            chefController.update();
            drawStuff(); //Does the actual drawing of the game


        } else {
            mainmenudraw();
        }
    }



    public void mainmenudraw(){
        try {
            game.changeScreen("mainMenu");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render (float delta) { //The constantly looping function
        gameLogic(); //Extracts what is being done incase I need to do other none-game logic related things in the render function. This may be changed in future if it turns out nothing else needs doing.


    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
