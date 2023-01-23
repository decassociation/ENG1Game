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

    public void addIngredient(Ingredient ingredient){
        ingredients.add(ingredient);
        times.add(timer.millis());
    }

    public void update(SpriteBatch batch){
        if(times.size() > 0){
            for (int i = 0; i < times.size(); i++) {
                if((timer.millis() - times.get(i)) >= timeToCook){
                    if(type.equals("frying_station")){
                        ingredients.get(i).fried = true;
                    }
                    else if(type.equals("baking_station")){
                        ingredients.get(i).baked = true;
                    }
                    else if(type.equals("cutting_station")){
                        ingredients.get(i).cut = true;
                    }
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

    public Ingredient removeIngredient(){
        times.remove(0);
        return ingredients.remove(0);
    }


}
