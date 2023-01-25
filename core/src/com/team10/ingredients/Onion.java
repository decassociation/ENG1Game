package com.team10.ingredients;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Onion extends Vegetable{

    public Onion(){
        uncutImg = new Texture(Gdx.files.internal("onion.png"));
        cutImg = new Texture(Gdx.files.internal("choppedOnion.png"));
        activeImg = uncutImg;
        name = "Onion";

        cutting_station = true;
    }
}
