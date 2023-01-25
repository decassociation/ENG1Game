package com.team10.game;
/*this is only a loose outline of what the Salad class will need
 * it will require a rework at some point to properly implement
 * the composition features, but right now I don't have the time
 * or flexibility of mind.
 */


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.team10.ingredients.Ingredient;

public class Salad extends Ingredient {
    private Integer timeLeft;  //Will be done in seconds or milliseconds - either way integer is good enough
    //turns out Salad will be more complicated that the others, since it requires ingredients and such, which also need to be prepared
    /*
     * Salad will need:
     *
     * lettuce
     * tomato
     * carrots
     * sauce
     *
     */
    private Boolean lettuce;
    private Boolean tomato;
    private Boolean carrots;
    private Boolean sauce;



    public String getName(){
        return(name);
    }

    public Salad(){
        lettuce = false;
        tomato = false;
        carrots = false;
        sauce = false;
        activeImg = new Texture(Gdx.files.internal("temp_food.png"));
    }



    public Integer checkTime(){
        return(timeLeft);
    }





}