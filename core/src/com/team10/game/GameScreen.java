package com.team10.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.team10.menus.MenuButton;

public class GameScreen extends Eng1Screen {
    private Boolean paused = false;   //Useful for when we want to implement a pause function

    private ChefController chefController;
    private CustomerControllerScenario customerController;
    FileManager fileManager = new FileManager();

    private OrthogonalTiledMapRenderer renderer;

    private TiledMap tileMap;

    float unitScale;

    private Boolean mainmenu;

    ArrayList<AddIngredientButton> addIngredientButtons = new ArrayList<>();
    UseIngredientButton useIngredientButton;
    RetrieveIngredientButton retrieveIngredientButton;

    CookingStation fryingStation;
    CookingStation bakingStation;
    CookingStation cuttingStation;

    /**
     * Sets the tilemap as the main screen for the game
     * @param game - game object for which the tilemap is set for
     */
    public GameScreen(Eng1Game game) {
        super(game);

        tileMap = new TmxMapLoader().load("tilemap_V3.tmx");
        unitScale = 1 / 16f; // 1 tile is 16x16 pixels hence 1/16f which means 16 pixels = 1 world unit
        renderer = new OrthogonalTiledMapRenderer(tileMap, unitScale);

        camera.setToOrtho(false, 35, 30);

        chefController = new ChefController();
        customerController = new CustomerControllerScenario(5);

        addIngredientButtons.add(new AddIngredientButton(0, "burger", batch, camera, chefController));
        addIngredientButtons.add(new AddIngredientButton(3, "bun", batch, camera, chefController));
        addIngredientButtons.add(new AddIngredientButton(6, "cheese", batch, camera, chefController));
        addIngredientButtons.add(new AddIngredientButton(9, "sauce", batch, camera, chefController));
        addIngredientButtons.add(new AddIngredientButton(12, "lettuce", batch, camera, chefController));
        addIngredientButtons.add(new AddIngredientButton(15, "onion", batch, camera, chefController));
        addIngredientButtons.add(new AddIngredientButton(18, "tomato", batch, camera, chefController));
        addIngredientButtons.add(new AddIngredientButton(21, "carrot", batch, camera, chefController));

        fryingStation = new CookingStation("fryingStation", 5000, 19, 25);
        bakingStation = new CookingStation("bakingStation", 5000, 29, 25);
        cuttingStation = new CookingStation("cuttingStation", 5000, 8, 20);

        useIngredientButton = new UseIngredientButton(32, 0, 3, 3, batch, camera, chefController, fryingStation, bakingStation, cuttingStation);
        retrieveIngredientButton = new RetrieveIngredientButton(32, 3, 3, 3, batch, camera, chefController, fryingStation, bakingStation, cuttingStation);

        mainmenu = false;
    }

    @Override
    public void show() {

    }


    private void gameLogic(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.valueOf(fileManager.read("pause")))) {
            try {
                game.changeScreen("pause", camera);
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
            game.changeScreen("mainMenu", camera);
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
        customerController.update(batch);

        useIngredientButton.update();
        retrieveIngredientButton.update();
        fryingStation.update(batch);
        bakingStation.update(batch);
        cuttingStation.update(batch);

        chefController.drawChefs(batch);
        chefController.drawInventory(batch);
        for (AddIngredientButton addIngredientButton : addIngredientButtons) {
            addIngredientButton.update();
        }

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        renderer.dispose();
        tileMap.dispose();
    }
}
