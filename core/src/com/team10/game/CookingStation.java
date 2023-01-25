package com.team10.game;

import java.time.Clock;
import java.util.ArrayList;
import java.util.Queue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.team10.ingredients.Ingredient;

public class CookingStation {
    Clock timer;
    ArrayList<Ingredient> ingredients;
    ArrayList<Long> times;
    long timeToCook;
    String type;
    boolean done;
    Texture readyTexture;
    float xPos;
    float yPos;

    /**
     * Constructor for CookingStation
     * 
     * @param type what kind of cooking station it is, frying_station, baking_station or cutting_station
     * @param timeToCook how long it takes the cooking station to process an item in ms
     * @param xPos the x coordinate of the cooking station, used to display the ready marker
     * @param yPos the y coordinate of the cooking station, used to display the ready marker
     */
    public CookingStation(String type, long timeToCook, float xPos, float yPos){
        timer = Clock.systemUTC();
        this.timeToCook = timeToCook;
        this.type = type;
        this.xPos = xPos;
        this.yPos = yPos;
        done = false;
        readyTexture = new Texture(Gdx.files.internal("Ready.png"));
        ingredients = new ArrayList<>();
        times = new ArrayList<>();
    }

    /**
     * Add an ingredient to the cooking station
     */
    public void addIngredient(Ingredient ingredient){
        ingredients.add(ingredient);
        times.add(timer.millis());
    }

    /**
     * Update each frame. Check if items are done and draw ready marker
     * 
     * @param batch the SpriteBatch to draw the ready marker to
     */
    public void update(SpriteBatch batch){
        if(times.size() > 0){
            for (int i = 0; i < times.size(); i++) {
                if((timer.millis() - times.get(i)) >= timeToCook){
                    if(!ingredients.get(i).processed) ingredients.get(i).process();
                    done = true;
                }
            }
            

            if((timer.millis() - times.get(0)) < timeToCook){
                done = false;
            }
        }
        else done = false;

        if(done){
            batch.draw(readyTexture, xPos, yPos, 3.0f, 3.0f);
        }
    }

    /**
     * Remove and return the first ingredient
     * 
     * @return ingredient
     */
    public Ingredient removeIngredient(){
        times.remove(0);
        return ingredients.remove(0);
    }


}
