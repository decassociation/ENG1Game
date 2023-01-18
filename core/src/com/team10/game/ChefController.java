package com.team10.game;

import java.awt.*;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class ChefController {
    private int chefCount;
    private int currentChef;
    private ArrayList<Chef> chefs;
    private ArrayList<Texture> chefTextures;
    private boolean topRightCollision, topLeftCollision, bottomRightCollision, bottomLeftCollision,
            middleRightCollision, middleLeftCollision;
    private TiledMap tileMap = new TmxMapLoader().load("pp_assessment_1_tilemap_V2.tmx");
    Chef chef;

    FileManager fileManager = new FileManager();

    public ChefController(){
        chefCount = 2;

        chefs = new ArrayList<Chef>();
        chefs.add(new Chef(5,5));
        chefs.add(new Chef(10,10));

        currentChef = 0;

        chefTextures = new ArrayList<Texture>();
        chefTextures.add(new Texture(Gdx.files.internal("chefA.png"))); //chef1 takes chef1(version 2) texture
        chefTextures.add(new Texture(Gdx.files.internal("chefB.png"))); //chef2 takes chef2(version 2) texture
    }

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

    public void chefMovement() {
        //activeChef = Chef.getActiveChef();
        Integer up = Input.Keys.valueOf(fileManager.read("up")); //This way we can have mappable keys for the controls.
        Integer left = Input.Keys.valueOf(fileManager.read("left"));
        Integer down = Input.Keys.valueOf(fileManager.read("down"));
        Integer right = Input.Keys.valueOf(fileManager.read("right"));

        if (Gdx.input.isKeyPressed(up) || Gdx.input.isKeyPressed(left) || Gdx.input.isKeyPressed(down) || Gdx.input.isKeyPressed(right)) {
            // play 'step.mp3' sound file
            
            SoundManager.playStepSound();
        }


		/*
		This section checks if the chef is touching the left side or the bottom of the map and moves them back
		into the middle of the map if they are; can be taken out be staying in for now.
		 */
        if(chefs.get(currentChef).getX() <= 0){  //left side of map
            chefs.get(currentChef).setX(10);
        }
        if(chefs.get(currentChef).getY() <= 0){  //bottom of the map
            chefs.get(currentChef).setY(10);
        }

        chef=chefs.get(currentChef); // Just helpful so don't have to write this out every time

        topLeftCollision=false;
        topRightCollision=false;
        bottomLeftCollision=false;
        bottomRightCollision=false;
        middleLeftCollision=false;
        middleRightCollision=false;
        

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
                if(collisionLayers.get(i).getCell((int) (chef.getX() + chef.getWidth() / 16),
                        (int) (chef.getY() + chef.getHeight() / 16)) != null) {
                    // A layer may not have a tile where the player is
                    // This checks the top right corner of a chef's sprite to see if there is a collision there
                    topRightCollision = collisionLayers.get(i).getCell((int) (chef.getX() + chef.getWidth() / 16),
                            (int) (chef.getY() + chef.getHeight() / 16)).getTile().getProperties().containsKey("Collider");
                }

                if(collisionLayers.get(i).getCell((int) (chef.getX() + chef.getWidth() / 16),
                        (int) (chef.getY())) != null) {
                    // Checks bottom right corner
                    bottomRightCollision = collisionLayers.get(i).getCell((int) (chef.getX() + chef.getWidth() / 16),
                            (int) (chef.getY())).getTile().getProperties().containsKey("Collider");
                }

                if(collisionLayers.get(i).getCell((int) (chef.getX() + chef.getWidth() / 16),
                        (int) (chef.getY() + ((chef.getHeight() / 2) / 16))) != null) {
                    // Checks middle right
                    middleRightCollision = collisionLayers.get(i).getCell((int) (chef.getX() + chef.getWidth() / 16),
                            (int) (chef.getY() + ((chef.getHeight() / 2) / 16))).getTile().getProperties().containsKey("Collider");
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
                if(collisionLayers.get(i).getCell((int) (chef.getX()), (int) (chef.getY())) != null) {
                    // Checks bottom left corner
                    bottomLeftCollision = collisionLayers.get(i).getCell((int) (chef.getX()),
                            (int) (chef.getY())).getTile().getProperties().containsKey("Collider");
                }

                if(collisionLayers.get(i).getCell((int) (chef.getX()),
                        (int) (chef.getY() + chef.getHeight() / 16)) != null) {
                    // Checks top left corner
                    topLeftCollision = collisionLayers.get(i).getCell((int) (chef.getX()),
                            (int) (chef.getY() + chef.getHeight() / 16)).getTile().getProperties().containsKey("Collider");
                }

                if(collisionLayers.get(i).getCell((int) (chef.getX()),
                        (int) (chef.getY() + ((chef.getHeight() / 2) / 16))) != null) {
                    // Checks middle left
                    middleLeftCollision = collisionLayers.get(i).getCell((int) (chef.getX()),
                            (int) (chef.getY() + ((chef.getHeight() / 2) / 16))).getTile().getProperties().containsKey("Collider");
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
            if (topLeftCollision || topRightCollision) {
                chefs.get(currentChef).setY(0);
                topRightCollision = false;
                topLeftCollision = false;
            } else {
                chefs.get(currentChef).setY(0.1f);
            }
        }


        ArrayList<TiledMapTileLayer> stationLayers = new ArrayList<TiledMapTileLayer>();
        stationLayers.add((TiledMapTileLayer) tileMap.getLayers().get("ingredient_stations"));
        stationLayers.add((TiledMapTileLayer) tileMap.getLayers().get("frying stations"));
        stationLayers.add((TiledMapTileLayer) tileMap.getLayers().get("cutting_stations"));
        stationLayers.add((TiledMapTileLayer) tileMap.getLayers().get("baking_stations"));
        stationLayers.add((TiledMapTileLayer) tileMap.getLayers().get("serving_stations"));

        // Below will check if a chef is at a cooking station or not

    }

    public void update(){
        swapChef();
        chefMovement();
    }

    public void drawChefs(SpriteBatch batch){
        for (int i = 0; i < chefCount; i++) {
            batch.draw(chefTextures.get(i), chefs.get(i).getX(), chefs.get(i).getY(), 1.0f, 3.0f);
            // float parameters scale down the chef images
        }
    }
}
