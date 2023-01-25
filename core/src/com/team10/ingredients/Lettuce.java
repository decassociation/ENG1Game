package com.team10.ingredients;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Lettuce extends Vegetable{

    public Lettuce(){
        uncutImg = new Texture(Gdx.files.internal("temp_food.png"));
        cutImg = new Texture(Gdx.files.internal("temp_food.png"));
        activeImg = uncutImg;
        name = "Lettuce";

        cutting_station = true;
    }
}
