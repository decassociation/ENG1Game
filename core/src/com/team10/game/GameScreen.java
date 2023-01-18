package com.team10.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen extends Eng1Screen {
    private Boolean paused = false;   //Useful for when we want to implement a pause function

    private ChefController chefController;
    private CustomerController customerController;
    FileManager fileManager = new FileManager();

    private OrthogonalTiledMapRenderer renderer;

    private TiledMap tileMap;

    float unitScale;

    private Boolean mainmenu;

    /**
     * Sets the tilemap as the main screen for the game
     * @param game - game object for which the tilemap is set for
     */
    public GameScreen(Eng1Game game) {
        super(game);

        tileMap = new TmxMapLoader().load("pp_assessment_1_tilemap_V2.tmx");
        unitScale = 1 / 16f; // 1 tile is 16x16 pixels hence 1/16f which means 16 pixels = 1 world unit
        renderer = new OrthogonalTiledMapRenderer(tileMap, unitScale);

        camera.setToOrtho(false, 35, 30);

        chefController = new ChefController();

        mainmenu = false;
    }

    @Override
    public void show() {

    }


    private void gameLogic(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.valueOf(fileManager.read("pause")))) {
            try {
                game.changeScreen("pause");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(!mainmenu){
            chefController.update();


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

    /**
     * Renders the tilemap
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render (float delta) { //The constantly looping function
        gameLogic(); //Extracts what is being done incase I need to do other none-game logic related things in the render function. This may be changed in future if it turns out nothing else needs doing.
        ScreenUtils.clear(1, 0, 0, 1);

        renderer.setView(camera);
        renderer.render();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        chefController.drawChefs(batch);
        customerController.drawCustomers(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        renderer.dispose();
        tileMap.dispose();
    }
}
