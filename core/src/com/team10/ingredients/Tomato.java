package com.team10.ingredients;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Tomato extends Vegetable{

    public Tomato(){
        uncutImg = new Texture(Gdx.files.internal("tomato_uncut.png"));
        cutImg = new Texture(Gdx.files.internal("tomato_cut.png")); //This sprite will need updating
        activeImg = uncutImg;
        name = "Tomato";

        cutting_station = true;
    }
}
