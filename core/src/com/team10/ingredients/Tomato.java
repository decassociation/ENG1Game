package com.team10.ingredients;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Tomato extends Vegetable{

    public Tomato(){
        uncutImg = new Texture(Gdx.files.internal("tomato_uncut.png"));
        cutImg = new Texture(Gdx.files.internal("tomato_cut.png"));
        activeImg = uncutImg;
        name = "Tomato";

        cutting_station = true;
    }
}
