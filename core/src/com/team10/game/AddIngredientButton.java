package com.team10.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.team10.ingredients.*;

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
        super(720, yPos, 70, 48, ingredient, batch, camera);

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
            chefController.chef.addFood(new BurgerPatty());
        }
        else if(ingredient.equals("lettuce")){
            chefController.chef.addFood(new Lettuce());
        }
        else if(ingredient.equals("onion")){
            chefController.chef.addFood(new Onion());
        }
        else if(ingredient.equals("tomato")){
            chefController.chef.addFood(new Tomato());
        }
        else if(ingredient.equals("carrot")){
            chefController.chef.addFood(new Carrot());
        }
        else if(ingredient.equals("bun")){
            chefController.chef.addFood(new BurgerBun());
        }
        else if(ingredient.equals("sauce")){
            chefController.chef.addFood(new Sauce());
        }
        else if(ingredient.equals("cheese")){
            chefController.chef.addFood(new Cheese());
        }
    }
}
