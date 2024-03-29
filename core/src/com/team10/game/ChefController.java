package com.team10.game;

import java.util.ArrayList;
import java.util.Stack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.team10.ingredients.BurgerPatty;
import com.team10.ingredients.Ingredient;
import com.team10.ingredients.Tomato;

public class ChefController {
    private int foodID = 0;
    private int chefCount;
    private int currentChef;
    private ArrayList<Chef> chefs;
    private ArrayList<Texture> chefTextures;
    private TiledMap tileMap = new TmxMapLoader().load("tilemap_V3.tmx");
    private Stack<Ingredient> inventory;
    Chef chef;
    private Texture chefPointerTexture;

    FileManager fileManager = new FileManager();

    public ChefController(){
        chefCount = 2;

        chefs = new ArrayList<Chef>();
        chefs.add(new Chef(13,13));
        chefs.add(new Chef(15,15));

        currentChef = 0;

        chefTextures = new ArrayList<Texture>();
        chefTextures.add(new Texture(Gdx.files.internal("chefA.png"))); //chef1 takes chef1(version 2) texture
        chefTextures.add(new Texture(Gdx.files.internal("chefB.png"))); //chef2 takes chef2(version 2) texture

        chefPointerTexture = new Texture(Gdx.files.internal("ChefPointer.png"));
    }

    /**
     * Controls the functionality of swapping between chefs
     */
    public void swapChef(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.valueOf(fileManager.read("changeChef")))) {
            if (currentChef < chefCount - 1) currentChef += 1;
            else currentChef = 0;
        }

        for (int i = 0; i < 9; i++) {   // cycle through each number 1-9 to check if that number key has been pressed
            if (Gdx.input.isKeyJustPressed(i + 8)) {    // integer code for number keys 1-9 is 8-16 so add 8 to i to get the key code
                if (chefCount >= i + 1) currentChef = i;
            }
        }


    }

    /**
     * Controls chef movement and collisions (with stations and anything the player shouldn't be able to move through)
     */
    public void chefMovement() {
        //activeChef = Chef.getActiveChef();
        Integer up = Input.Keys.valueOf(fileManager.read("up")); //This way we can have mappable keys for the controls.
        Integer left = Input.Keys.valueOf(fileManager.read("left"));
        Integer down = Input.Keys.valueOf(fileManager.read("down"));
        Integer right = Input.Keys.valueOf(fileManager.read("right"));
        Integer discardItem = Input.Keys.valueOf(fileManager.read("discardItem"));

        if(Gdx.input.isKeyJustPressed(discardItem)){
            chef.popFood();
        }

        if (Gdx.input.isKeyPressed(up) || Gdx.input.isKeyPressed(left) || Gdx.input.isKeyPressed(down) || Gdx.input.isKeyPressed(right)) {
            // play 'step.mp3' sound file

            SoundManager.playStepSound();
        }


		/*
		Checks if the chef is touching the left side or the bottom of the map and moves them back
		near the middle of the map if they are
		 */
        if(chefs.get(currentChef).getX() <= 0){  //left side of map
            chefs.get(currentChef).setX(10);
        }
        if(chefs.get(currentChef).getY() <= 0){  //bottom of the map
            chefs.get(currentChef).setY(10);
        }

        chef=chefs.get(currentChef); // Just helpful so don't have to write this out every time

        boolean topRightCollision=false, topLeftCollision=false, bottomRightCollision=false, bottomLeftCollision=false,
                middleRightCollision=false, middleLeftCollision=false;


        // The below creates an ArrayList of all the layers that the player could collide with (identified by a
        // tile with a "Collider" property - only the layers below have tiles with this property)
        ArrayList<TiledMapTileLayer> collisionLayers = new ArrayList<TiledMapTileLayer>();
        collisionLayers.add((TiledMapTileLayer) tileMap.getLayers().get("Chairs"));
        collisionLayers.add((TiledMapTileLayer) tileMap.getLayers().get("Tables"));
        collisionLayers.add((TiledMapTileLayer) tileMap.getLayers().get("Walls"));
        collisionLayers.add((TiledMapTileLayer) tileMap.getLayers().get("Worktops"));
        collisionLayers.add((TiledMapTileLayer) tileMap.getLayers().get("Extras"));
        collisionLayers.add((TiledMapTileLayer) tileMap.getLayers().get("SideWallTops"));

        /*
         * Checks if the right movement key is pressed down and if it is, it loops through each layer in collisionLayers
         * and if the player is colliding/touching a tile that they shouldn't be able to move through, the movement is
         * stopped, otherwise the player moves as normal.
         *
         * Note: 16 is the height and the width of each tile
         */
        if (Gdx.input.isKeyPressed(right)){
            for(int i=0; i<=collisionLayers.size()-1;i++){
                /* This if statement checks if the top of the player is touching a tile at the very back of the kitchen
                    (that isn't a wall) and if they are then collision detection is ignored so the player can access
                    the stations at the back.  Otherwise, the player wouldn't be able to reach the stations */
                if((int) (chef.getY() + chef.getHeight() / 16) != 27) {
                    if (collisionLayers.get(i).getCell((int) (chef.getX() + chef.getWidth() / 16),
                            (int) (chef.getY() + chef.getHeight() / 16)) != null) {
                        // A layer may not have a tile where the player is
                        // This checks the top right corner of a chef's sprite to see if there is a collision there
                        topRightCollision = collisionLayers.get(i).getCell((int) (chef.getX() + chef.getWidth() / 16),
                                (int) (chef.getY() + chef.getHeight() / 16)).getTile().getProperties().containsKey("Collider");
                    }

                    if (collisionLayers.get(i).getCell((int) (chef.getX() + chef.getWidth() / 16),
                            (int) (chef.getY())) != null) {
                        // Checks bottom right corner
                        bottomRightCollision = collisionLayers.get(i).getCell((int) (chef.getX() + chef.getWidth() / 16),
                                (int) (chef.getY())).getTile().getProperties().containsKey("Collider");
                    }

                    if (collisionLayers.get(i).getCell((int) (chef.getX() + chef.getWidth() / 16),
                            (int) (chef.getY() + ((chef.getHeight() / 2) / 16))) != null) {
                        // Checks middle right
                        middleRightCollision = collisionLayers.get(i).getCell((int) (chef.getX() + chef.getWidth() / 16),
                                (int) (chef.getY() + ((chef.getHeight() / 2) / 16))).getTile().getProperties().containsKey("Collider");
                    }
                }

            }
            if(topRightCollision || bottomRightCollision || middleRightCollision){
                chef.setX(0); // Stops player movement
                topRightCollision=false;
                bottomRightCollision = false;
                middleRightCollision = false;
            } else{
                chefs.get(currentChef).setX(0.1f);
            }
        }
        // Collision detection on pressing the left movement key
        if (Gdx.input.isKeyPressed(left)){
            for(int i=0; i<=collisionLayers.size()-1;i++){
                if((int) (chef.getY() + chef.getHeight() / 16) != 27) {
                    if (collisionLayers.get(i).getCell((int) (chef.getX()), (int) (chef.getY())) != null) {
                        // Checks bottom left corner
                        bottomLeftCollision = collisionLayers.get(i).getCell((int) (chef.getX()),
                                (int) (chef.getY())).getTile().getProperties().containsKey("Collider");
                    }

                    if (collisionLayers.get(i).getCell((int) (chef.getX()),
                            (int) (chef.getY() + chef.getHeight() / 16)) != null) {
                        // Checks top left corner
                        topLeftCollision = collisionLayers.get(i).getCell((int) (chef.getX()),
                                (int) (chef.getY() + chef.getHeight() / 16)).getTile().getProperties().containsKey("Collider");
                    }

                    if (collisionLayers.get(i).getCell((int) (chef.getX()),
                            (int) (chef.getY() + ((chef.getHeight() / 2) / 16))) != null) {
                        // Checks middle left
                        middleLeftCollision = collisionLayers.get(i).getCell((int) (chef.getX()),
                                (int) (chef.getY() + ((chef.getHeight() / 2) / 16))).getTile().getProperties().containsKey("Collider");
                    }
                }
            }
            if(bottomLeftCollision || topLeftCollision || middleLeftCollision){
                chef.setX(0); // Stops player movement
                bottomLeftCollision=false;
                topLeftCollision = false;
                middleLeftCollision = false;
            } else{
                chefs.get(currentChef).setX(-0.1f);
            }
        }

        // Collision detection on pressing the down movement key
        if (Gdx.input.isKeyPressed(down)){
            for(int i=0; i<=collisionLayers.size()-1;i++) {
                if (collisionLayers.get(i).getCell((int) (chef.getX()), (int) (chef.getY())) != null) {
                    // Checks bottom left corner
                    bottomLeftCollision = collisionLayers.get(i).getCell((int) (chef.getX()),
                            (int) (chef.getY())).getTile().getProperties().containsKey("Collider");
                }

                if(collisionLayers.get(i).getCell((int) (chef.getX() + chef.getWidth() / 16),
                        (int) (chef.getY())) != null) {
                    // Checks bottom right corner
                    bottomRightCollision = collisionLayers.get(i).getCell((int) (chef.getX() + chef.getWidth() / 16),
                            (int) (chef.getY())).getTile().getProperties().containsKey("Collider");
                }
            }

            if(bottomLeftCollision || bottomRightCollision){
                chefs.get(currentChef).setY(0);
                bottomRightCollision = false;
                bottomLeftCollision = false;
            } else {
                chefs.get(currentChef).setY(-0.1f);
            }
        }

        // Collision detection on pressing the up movement key
        if (Gdx.input.isKeyPressed(up)) {
            for (int i = 0; i <= collisionLayers.size() - 1; i++) {
                if((int) (chef.getY() + chef.getHeight() / 16) != 27){
                    if (collisionLayers.get(i).getCell((int) (chef.getX()),
                            (int) (chef.getY() + chef.getHeight() / 16)) != null) {
                        // Checks top left corner
                        topLeftCollision = collisionLayers.get(i).getCell((int) (chef.getX()),
                                (int) (chef.getY() + chef.getHeight() / 16)).getTile().getProperties().containsKey("Collider");
                    }

                    if (collisionLayers.get(i).getCell((int) (chef.getX() + chef.getWidth() / 16),
                            (int) (chef.getY() + chef.getHeight() / 16)) != null) {
                        // Check top right corner
                        topRightCollision = collisionLayers.get(i).getCell((int) (chef.getX() + chef.getWidth() / 16),
                                (int) (chef.getY() + chef.getHeight() / 16)).getTile().getProperties().containsKey("Collider");
                    }
                }
            }
            if (topLeftCollision || topRightCollision) {
                chefs.get(currentChef).setY(0);
                topRightCollision = false;
                topLeftCollision = false;
            } else {
                chefs.get(currentChef).setY(0.1f);
            }
        }


        // Chef's current co-ordinates on the tilemap
        float chefx = chefs.get(currentChef).getX();
        float chefy = chefs.get(currentChef).getY();

        // The set of if statements control detection of whether the player is at a cooking station
        if(((TiledMapTileLayer) tileMap.getLayers().get("ingredient_stations")).getCell((int) chefx, (int) chefy) != null){
            // Do something for ingredient station
            chef.setArea("ingredients_station");
        } else if(((TiledMapTileLayer) tileMap.getLayers().get("frying_stations")).getCell((int) chefx, (int) chefy) != null){
            // Do something for frying station
            chef.setArea("frying_station");
        } else if(((TiledMapTileLayer) tileMap.getLayers().get("cutting_stations")).getCell((int) chefx, (int) chefy) != null){
            // Do something for cutting station
            chef.setArea("cutting_station");
        } else if(((TiledMapTileLayer) tileMap.getLayers().get("baking_stations")).getCell((int) chefx, (int) chefy) != null){
            // Do something for baking station
            chef.setArea("baking_station");
        } else if(((TiledMapTileLayer) tileMap.getLayers().get("serving_stations")).getCell((int) chefx, (int) chefy) != null){
            // Do something for serving station
            chef.setArea("serving_station");
        } else {chef.setArea("not");}
    }

    public void update(){
        swapChef();
        chefMovement();
        tryGetFood();
    }

    /**
     * Draws all of the chef sprites
     * @param batch - the set of sprites (rectangle objects) and their co-ordinates to be rendered/drawn on the screen
     */
    public void drawChefs(SpriteBatch batch){
        for (int i = 0; i < chefCount; i++) {
            batch.draw(chefTextures.get(i), chefs.get(i).getX(), chefs.get(i).getY(), 1.0f, 3.0f);
            // float parameters scale down the chef images
        }
        batch.draw(chefPointerTexture, chef.getX()-0.15f, chef.getY()+3.5f, 1.5f, 1.5f);

    }

    //Shows the contents of a chef's inventory on screen
    public void drawInventory(SpriteBatch batch) {
        for (int i = 0; i < chefCount; i++) { //For each chef:
            inventory = chefs.get(i).getInventory(); //Check inventory
            for (int j = 0; j < inventory.size(); j++) { //For each ingredient in inventory:
                batch.draw((inventory.get(j)).getImg(), (chefs.get(i).getX())+1, (chefs.get(i).getY())+(1*j), 1.0f, 1.0f);
            }
        }
    }


    public void tryGetFood(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.valueOf("L"))){ //outputs the inventory stack
            for(int x = 0; x < chef.getInventory().size(); x++){
                System.out.println(chef.getInventory().get(x));
            }

        }





    }


}