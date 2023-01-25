package com.team10.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

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
    GiveIngredientButton giveIngredientButton;

    CookingStation fryingStation;
    CookingStation bakingStation;
    CookingStation cuttingStation;

    // second batch and camera deal with ui elements separately to game elements
    OrthographicCamera camera2;
    SpriteBatch batch2;

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

        camera2 = new OrthographicCamera();
        camera2.setToOrtho(false, 800, 480);
        batch2 = new SpriteBatch();

        chefController = new ChefController();
        customerController = new CustomerControllerScenario(5);

        // buttons for adding each ingredient to the chef's inventory
        addIngredientButtons.add(new AddIngredientButton(0, "burger", batch2, camera2, chefController));
        addIngredientButtons.add(new AddIngredientButton(48, "bun", batch2, camera2, chefController));
        addIngredientButtons.add(new AddIngredientButton(96, "cheese", batch2, camera2, chefController));
        addIngredientButtons.add(new AddIngredientButton(144, "sauce", batch2, camera2, chefController));
        addIngredientButtons.add(new AddIngredientButton(192, "lettuce", batch2, camera2, chefController));
        addIngredientButtons.add(new AddIngredientButton(240, "onion", batch2, camera2, chefController));
        addIngredientButtons.add(new AddIngredientButton(288, "tomato", batch2, camera2, chefController));
        addIngredientButtons.add(new AddIngredientButton(336, "carrot", batch2, camera2, chefController));

        // cooking stations, pass to use and retrieve buttons
        fryingStation = new CookingStation("fryingStation", 5000, 19, 25);
        bakingStation = new CookingStation("bakingStation", 5000, 29, 25);
        cuttingStation = new CookingStation("cuttingStation", 5000, 8, 20);

        // use, retrieve and give buttons
        useIngredientButton = new UseIngredientButton(0, batch2, camera2, chefController, fryingStation, bakingStation, cuttingStation);
        retrieveIngredientButton = new RetrieveIngredientButton(48, batch2, camera2, chefController, fryingStation, bakingStation, cuttingStation);
        giveIngredientButton = new GiveIngredientButton(batch2, camera2, chefController, customerController);

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
        batch2.setProjectionMatrix(camera2.combined);

        batch.begin();
        customerController.update(batch);

        
        fryingStation.update(batch);
        bakingStation.update(batch);
        cuttingStation.update(batch);

        chefController.drawChefs(batch);
        chefController.drawInventory(batch);
        

        batch.end();

        batch2.begin();
        useIngredientButton.update();
        retrieveIngredientButton.update();
        giveIngredientButton.update();
        for (AddIngredientButton addIngredientButton : addIngredientButtons) {
            addIngredientButton.update();
        }
        customerController.drawText(batch2);
        batch2.end();
    }
        

    @Override
    public void dispose() {
        batch.dispose();
        renderer.dispose();
        tileMap.dispose();
    }
}
