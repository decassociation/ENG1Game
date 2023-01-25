package com.team10.ingredients;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Cheese extends Ingredient {
    Texture cutImg;
    Texture uncutImg;

    public Cheese() {
        uncutImg = new Texture(Gdx.files.internal("cheese.png"));
        cutImg = new Texture(Gdx.files.internal("cutCheese.png"));
        activeImg = uncutImg;
        name = "Cheese";
    }

    @Override
    public void process() {
        cut = true;
        activeImg = cutImg;
        name = "Cut " + name;
        super.process();
        System.out.println(name);
    }
}
