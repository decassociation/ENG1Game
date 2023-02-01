package com.team10.ingredients;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Carrot extends Vegetable{
    public Carrot(){
        uncutImg = new Texture(Gdx.files.internal("carrot.png"));
        cutImg = new Texture(Gdx.files.internal("CutCarrot.png"));
        activeImg = uncutImg;
        name = "Carrot";
        
        cutting_station = true;
    }
}
