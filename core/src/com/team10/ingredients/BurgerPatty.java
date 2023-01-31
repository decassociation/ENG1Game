package com.team10.ingredients;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BurgerPatty extends Ingredient {
    protected Texture uncookedImg;
    protected Texture cookedImg;
    private boolean isCooked = false;
    private boolean isFlipped = false;

    public BurgerPatty() {
        cookedImg = new Texture(Gdx.files.internal("cookedBurger.png"));
        uncookedImg = new Texture(Gdx.files.internal("rawBurger.png"));
        activeImg = uncookedImg;
        name = "Burger";

        frying_station = true;
    }


    public void flip() {
        isFlipped = !isFlipped;
    } //Changes if the burger is flipped or not

    @Override
    public void process() {
        fried = true;
        name = "Cooked " + name;
        super.process();
    }
}
