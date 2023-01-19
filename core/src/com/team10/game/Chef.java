package com.team10.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class Chef {
    private Rectangle chef;
    private float xPos;
    private float yPos;
    private Boolean isWorking;
    private Boolean isInFryingArea; //for frying burgers
    private Boolean isInSaladArea; //Where they can chop salad
    private Boolean isInIngredientsArea; //Where they can get ingredients
    private Boolean isInCounterArea; //Where they can interact with customers




    public Chef(Integer x, Integer y) {
        xPos = x;
        yPos = y;
        isWorking = false;

        //Chef instantiation
        chef = new Rectangle();
        chef.x = x;
        chef.y = y;
        chef.width = 20;
        chef.height = 36;

        //This should be changed later ===============================================================================================================
        isInFryingArea = true;
        isInSaladArea = true;
        isInCounterArea = true;
        isInIngredientsArea = true;

    }

    /*
     * This is the gets and sets for locating the chefs.
     * This can be expanded if further areas are required.
     * At the time of writing, 18/01/2023, all are defaulted to true
     * but this needs to be changed for the final game.
     */

    public void setIsInFryingArea(Boolean thing){isInFryingArea = thing;}
    public Boolean getIsInFryingArea(){return isInFryingArea;}

    public void setIsInSaladArea(Boolean thing){isInSaladArea = thing;}
    public Boolean getIsInSaladArea(){return isInSaladArea;}

    public void setIsInIngredientsArea(Boolean thing){isInIngredientsArea = thing;}
    public Boolean getIsInIngredientsArea(){return isInIngredientsArea;}

    public void setIsInCounterArea(Boolean thing){isInCounterArea = thing;}
    public Boolean getIsInCounterArea (){return isInCounterArea ;}



    public float getWidth(){return chef.width;}
    public float getHeight(){return chef.height;}

    public float getX() {
        return xPos;
    }
    public float getY() {
        return yPos;
    }
    public void setX(float xValue) {
        //chef will only move if the chef is not currently working at a station
        if (!isWorking) {
            chef.x += xValue * Gdx.graphics.getDeltaTime();
            xPos += xValue;
        }
    }
    public void setY(float yValue) {
        if (!isWorking) {
            chef.y += yValue * Gdx.graphics.getDeltaTime();
            yPos += yValue;
        }
    }

    public boolean isWorking() {
        return isWorking;
    }
    public void updateWorking() {
        isWorking = !isWorking;
    }
}
