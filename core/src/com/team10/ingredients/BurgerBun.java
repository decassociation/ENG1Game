package com.team10.ingredients;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BurgerBun extends Ingredient{
    Texture cutImg;
    Texture uncutImg;

    public BurgerBun(){
        cutImg = new Texture(Gdx.files.internal("cutBun.png"));
        uncutImg = new Texture(Gdx.files.internal("bun.png"));
        activeImg = uncutImg;
        name = "Bun";

        cutting_station = true;
    }

    @Override
    public void process() { //Method for cutting any vegetable
        cut = true;
        activeImg = cutImg;
        name = "Cut " + name;
        super.process();
        System.out.println(name);
    }
}
