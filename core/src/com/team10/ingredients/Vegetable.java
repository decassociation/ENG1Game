package com.team10.ingredients;

import com.badlogic.gdx.graphics.Texture;

public class Vegetable extends Ingredient{
    protected Texture uncutImg;
    protected Texture cutImg;
    private Boolean isCut = false;

    public void cut() {
        isCut = true;
        activeImg = cutImg;
    }
}
