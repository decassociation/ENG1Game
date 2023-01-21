package com.team10.ingredients;

import com.badlogic.gdx.graphics.Texture;

public class Meat extends Ingredient{
    protected Texture uncookedImg;
    protected Texture cookedImg;
    private boolean isCooked = false;
    private boolean isFlipped = false;

    public void flip() {
        isFlipped = !isFlipped;
    }

}
