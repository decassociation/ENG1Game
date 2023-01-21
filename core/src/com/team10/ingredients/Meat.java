package com.team10.ingredients;

import com.badlogic.gdx.graphics.Texture;

public class Meat extends Ingredient{
    protected Texture uncookedImg;
    protected Texture cookedImg;
    private boolean isCooked = false;
    private boolean isFlipped = false;

    public void flip() {
        isFlipped = !isFlipped;
    } //Changes if the burger is flipped or not

    //isCooked has not been implemented yet, other meat methods can be implemented here.
}
