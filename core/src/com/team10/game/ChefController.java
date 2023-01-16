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
    private boolean topRightCollision;
    private TiledMap tileMap;

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
		This section checks if the chef is in a "good" place, i.e. not in a wall.
		If the chef is in a wall, denoted by them being in the given boundaries shown below,
		they are moved to be not in that wall.

		This is an initial and somewhat brutal method, but for now it will stick

		If later a better option is found, then we can amend this.

		For now the collisions code is commented while the tilemap collisions are being implemented

		 */
        if(chefs.get(currentChef).getX() <= 0){  //left side of map
            chefs.get(currentChef).setX(10);
        }
        if(chefs.get(currentChef).getY() <= 0){  //bottom of the map
            chefs.get(currentChef).setY(10);
        }
//        if(chefs.get(currentChef).getX() >= -100 && chefs.get(currentChef).getX() < 873 && chefs.get(currentChef).getY() > 650){  //bottom of counter
//            chefs.get(currentChef).setY(-10);
//        }
//        if(chefs.get(currentChef).getY() < 840 && chefs.get(currentChef).getY() > 560 && chefs.get(currentChef).getX() > 740){  //side of Burger area
//            chefs.get(currentChef).setX(-10);
//        }
//        if(chefs.get(currentChef).getX() >= 770 && chefs.get(currentChef).getX() < 1850 && chefs.get(currentChef).getY() > 540){  //bottom of Burger area
//            chefs.get(currentChef).setY(-10);
//        }
//        if(chefs.get(currentChef).getY() < 830 && chefs.get(currentChef).getY() > 0 && chefs.get(currentChef).getX() > 1500){  //left side of Salad area
//            chefs.get(currentChef).setX(-10);
//        }
//        if(chefs.get(currentChef).getX() >= 0 && chefs.get(currentChef).getX() < 1850 && chefs.get(currentChef).getY() < 230){  //top of lower counter
//            chefs.get(currentChef).setY(10);
//        }


        Chef chef=chefs.get(currentChef); // Just helpful so don't have to write this out every time
        topRightCollision=false;
        tileMap = new TmxMapLoader().load("pp_assessment_1_tilemap_V2.tmx");

        // The below creates an ArrayList of all the layers that the player could collide with (identified by a
        // tile with a "Collider" property - only the layers below have tiles with this property)
        ArrayList<TiledMapTileLayer> collisionLayers = new ArrayList<TiledMapTileLayer>();
        collisionLayers.add((TiledMapTileLayer) tileMap.getLayers().get("Chairs"));
        collisionLayers.add((TiledMapTileLayer) tileMap.getLayers().get("Tables"));
        collisionLayers.add((TiledMapTileLayer) tileMap.getLayers().get("Walls"));
        collisionLayers.add((TiledMapTileLayer) tileMap.getLayers().get("Worktops"));
        collisionLayers.add((TiledMapTileLayer) tileMap.getLayers().get("Extras"));
        collisionLayers.add((TiledMapTileLayer) tileMap.getLayers().get("SideWallTops"));

        /**
         * Checks if the right movement key is pressed down and if it is, it loops through each layer in collisionLayers
         * and if the player is colliding/touching a tile that they shouldn't be able to move through, the movement is
         * stopped, otherwise the player moves as normal.
         */
        if (Gdx.input.isKeyPressed(right)){
            for(int i=0; i<=collisionLayers.size()-1;i++){
                if(collisionLayers.get(i).getCell((int) (chef.getX() + chef.getWidth() / 16),
                        (int) (chef.getY() + chef.getHeight() / 16)) != null) { // A layer may not have a tile where the player is
                    topRightCollision = collisionLayers.get(i).getCell((int) (chef.getX() + chef.getWidth() / 16),
                            (int) (chef.getY() + chef.getHeight() / 16)).getTile().getProperties().containsKey("Collider");
                    // 16 is the height and the width of each tile
                }
            }
            if(topRightCollision){
                chef.setX(0); // Stops player movement
                topRightCollision=false;
            } else{
                chefs.get(currentChef).setX(0.1f);
            }
        }
        if (Gdx.input.isKeyPressed(left)) chefs.get(currentChef).setX(-0.1f);
        if (Gdx.input.isKeyPressed(down)) chefs.get(currentChef).setY(-0.1f);
        if (Gdx.input.isKeyPressed(up)) chefs.get(currentChef).setY(0.1f);

		/*
		This whole section just checks which chef is activated and uses their section, and moves them by 250 units each loop.
		If one were to add more Chef, then they could use an elseif structure to check between Chef,
		but in this case, since there are only two option, an if/ else is sufficient
		*/
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
