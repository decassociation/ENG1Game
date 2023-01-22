package com.team10.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.team10.ingredients.BurgerPatty;

// Class for ScreenButtons which add ingredients to a chef's inventory
public class AddIngredientButton extends ScreenButton{
    String ingredient;
    ChefController chefController;

    /**
     * Constructor for AddIngredientButton
     * 
     * @param yPos the y coordinate
     * @param ingredient doubles as both the ingredient to add to the inventory and the text for the button
     * @param batch the SpriteBatch to draw the button to
     * @param camera the camera being used to render the game, needed to get correct
       projections of coordinates for clicking
       @param chefController the chefController being used so that the button can interact with it and
       add ingredients to chefs' inventories
     */
    public AddIngredientButton(int yPos, String ingredient, SpriteBatch batch, Camera camera, ChefController chefController){
        super(32, yPos, 3, 3, ingredient, batch, camera);

        this.ingredient = ingredient;
        this.chefController = chefController;
    }

    /**
     * Update procedure to set visibility, draw the button and check if it is clicked, call in render() instead of calling onClick() and draw()
     */
    @Override
    public void update(){
        if(chefController.chef.getArea().equals("ingredients_station")) visible = true;
        else visible = false;
        onClick();
        draw();
    }

    /**
     * Add ingredient to the active chef's inventory when clicked
     */
    @Override
    public void clickFunction(){
        System.out.println("AddIngredientButton.java clickFunction() add ingredient: " + ingredient);
        if(ingredient.equals("burger")){
            chefController.chef.addFood(new BurgerPatty(0f, 0f, 0));
        }
    }
}
