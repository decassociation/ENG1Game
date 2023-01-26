package com.team10.ingredients;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Lettuce extends Vegetable{

    public Lettuce(){
        uncutImg = new Texture(Gdx.files.internal("lettuce.png"));
        cutImg = new Texture(Gdx.files.internal("cutLettuce.png"));
        activeImg = uncutImg;
        name = "Lettuce";

        cutting_station = true;
    }
}
