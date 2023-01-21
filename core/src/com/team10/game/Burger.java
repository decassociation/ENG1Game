package com.team10.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.team10.ingredients.Ingredient;

import java.lang.annotation.Inherited;

public class Burger extends Ingredient {

    //    private Boolean isFlipped; //This is an extra step that could be added later to add spice to the game.
//    private Boolean needsSalad;
//    private Boolean hasSalad;
    private Boolean isCooked;

    public Burger(Integer id) {
        ingID =  id;
        isCooked = false;
        activeImg = new Texture(Gdx.files.internal("temp_food.png"));
    }

    public Boolean getIsCooked(){
        return isCooked;
    }

    public void setIsCooked(){
        isCooked = true;
    }


//    public Boolean getIsFlipped(){
//        return isFlipped;
//    }
//
//    public Boolean getNeedsSalad(){
//        return needsSalad;
//    }
//
//    public Boolean getHasSalad(){
//        return hasSalad;
//    }




}
