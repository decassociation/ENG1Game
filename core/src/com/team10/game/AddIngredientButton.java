package com.team10.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AddIngredientButton extends ScreenButton{
    String ingredient;
    ChefController chefController;

    public AddIngredientButton(int yPos, String ingredient, SpriteBatch batch, Camera camera, ChefController chefController){
        super(32, yPos, 3, 3, ingredient, batch, camera);

        this.ingredient = ingredient;
        this.chefController = chefController;
    }

    public void update(){
        if(chefController.chef.getArea().equals("ingredients_station")) visible = true;
        else visible = false;
        onClick();
        draw();
    }

    @Override
    public void clickFunction(){
        System.out.println("AddIngredientButton.java clickFunction() add ingredient: " + ingredient);
    }
}
