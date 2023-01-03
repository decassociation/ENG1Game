package com.team10.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ChefController {
    private int chefCount;
    private int currentChef;
    private ArrayList<Chef> chefs;
    private ArrayList<Texture> chefTextures;

    FileManager fileManager = new FileManager();

    public ChefController(){
        chefCount = 2;

        chefs = new ArrayList<Chef>();
        chefs.add(new Chef(500,500));
        chefs.add(new Chef(873,400));

        currentChef = 0;

        chefTextures = new ArrayList<Texture>();
        chefTextures.add(new Texture(Gdx.files.internal("chef12.png"))); //chef1 takes chef1(version 2) texture
        chefTextures.add(new Texture(Gdx.files.internal("chef22.png"))); //chef2 takes chef2(version 2) texture
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

		If later a better option is found, then we can ammend this.

		 */
        if(chefs.get(currentChef).getX() <= 0){  //left side of map
            chefs.get(currentChef).setX(10);
        }
        if(chefs.get(currentChef).getY() <= 0){  //bottom of the map
            chefs.get(currentChef).setY(10);
        }
        if(chefs.get(currentChef).getX() >= -100 && chefs.get(currentChef).getX() < 873 && chefs.get(currentChef).getY() > 650){  //bottom of counter
            chefs.get(currentChef).setY(-10);
        }
        if(chefs.get(currentChef).getY() < 840 && chefs.get(currentChef).getY() > 560 && chefs.get(currentChef).getX() > 740){  //side of Burger area
            chefs.get(currentChef).setX(-10);
        }
        if(chefs.get(currentChef).getX() >= 770 && chefs.get(currentChef).getX() < 1850 && chefs.get(currentChef).getY() > 540){  //bottom of Burger area
            chefs.get(currentChef).setY(-10);
        }
        if(chefs.get(currentChef).getY() < 830 && chefs.get(currentChef).getY() > 0 && chefs.get(currentChef).getX() > 1500){  //left side of Salad area
            chefs.get(currentChef).setX(-10);
        }
        if(chefs.get(currentChef).getX() >= 0 && chefs.get(currentChef).getX() < 1850 && chefs.get(currentChef).getY() < 230){  //top of lower counter
            chefs.get(currentChef).setY(10);
        }

        if (Gdx.input.isKeyPressed(right)) chefs.get(currentChef).setX(10);
        if (Gdx.input.isKeyPressed(left)) chefs.get(currentChef).setX(-10);
        if (Gdx.input.isKeyPressed(down)) chefs.get(currentChef).setY(-10);
        if (Gdx.input.isKeyPressed(up)) chefs.get(currentChef).setY(10);

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
            batch.draw(chefTextures.get(i), chefs.get(i).getX(), chefs.get(i).getY());
        }
    }
}
