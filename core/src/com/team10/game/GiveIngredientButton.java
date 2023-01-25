package com.team10.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.team10.ingredients.Ingredient;

public class GiveIngredientButton extends ScreenButton{
    ChefController chefController;
    CustomerController customerController;

    /**
     * Constructor for GiveIngredienButton
     * 
     * @param batch the SpriteBatch to draw the button to
     * @param camera the camera to project the batch to
     * @param chefController the chefController to get ingredients from
     * @param customerController the customerController to give the ingredients to
     */
    public GiveIngredientButton(SpriteBatch batch, Camera camera, ChefController chefController, CustomerController customerController){
        super(650, 0, 140, 48, "", batch, camera);
        this.chefController = chefController;
        this.customerController = customerController;
    }

    /**
     * Update procedure to set visibility, draw the button and check if it is clicked, call in render() instead of calling onClick() and draw()
     */
    @Override
    public void update(){
        // if the chef has something in the stack and the top item is compatible with the current ingredient station, set to be visible
        if(chefController.chef.getInventory().isEmpty() == false){
            if(chefController.chef.getArea().equals("serving_station")) visible = true;
            else visible = false;

            // dynamically change the text based on what item is on the top of the stack
            setText();
        }
        else visible = false;
        onClick();
        draw();
    }

    protected void setText(){
        if(visible) text = "Give " + chefController.chef.getInventory().peek().name;
    }

    /**
     * Add ingredient to the customerController's servedIngredients list
     */
    @Override
    public void clickFunction(){
        System.out.println("UseIngredientButton.java clickFunction() give ingredient: " + chefController.chef.getInventory().peek());
        
        customerController.servedIngredients.add(chefController.chef.popFood());
    }
    
}
