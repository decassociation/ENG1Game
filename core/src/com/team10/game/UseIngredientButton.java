package com.team10.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UseIngredientButton extends ScreenButton{
    ChefController chefController;
    CookingStation fryingStation;
    CookingStation bakingStation;
    CookingStation cuttingStation;
    
    public UseIngredientButton(int yPos, SpriteBatch batch, Camera camera, ChefController chefController, CookingStation fryingStation, CookingStation bakingStation, CookingStation cuttingStation){
        super(650, yPos, 140, 48, "", batch, camera);
        this.chefController = chefController;
        this.fryingStation = fryingStation;
        this.bakingStation = bakingStation;
        this.cuttingStation = cuttingStation;
    }

    /**
     * Update procedure to set visibility, draw the button and check if it is clicked, call in render() instead of calling onClick() and draw()
     */
    @Override
    public void update(){
        // if the chef has something in the stack and the top item is compatible with the current ingredient station, set to be visible
        if(chefController.chef.getInventory().isEmpty() == false){
            if(chefController.chef.getArea().equals("frying_station") && chefController.chef.getInventory().peek().frying_station) visible = true;
            else if(chefController.chef.getArea().equals("cutting_station") && chefController.chef.getInventory().peek().cutting_station) visible = true;
            else if(chefController.chef.getArea().equals("baking_station") && chefController.chef.getInventory().peek().baking_station) visible = true;
            else visible = false;

            // dynamically change the text based on what item is on the top of the stack
            setText();
        }
        else visible = false;
        onClick();
        draw();
    }

    protected void setText(){
        if(visible) text = "Use " + chefController.chef.getInventory().peek().name;
    }

    /**
     * Add ingredient to the active chef's inventory when clicked
     */
    @Override
    public void clickFunction(){
        System.out.println("UseIngredientButton.java clickFunction() use ingredient: " + chefController.chef.getInventory().peek());
        
        if(chefController.chef.getArea().equals("frying_station")) fryingStation.addIngredient(chefController.chef.getInventory().pop());
        if(chefController.chef.getArea().equals("baking_station")) bakingStation.addIngredient(chefController.chef.getInventory().pop());
        if(chefController.chef.getArea().equals("cutting_station")) cuttingStation.addIngredient(chefController.chef.getInventory().pop());
    }
}
