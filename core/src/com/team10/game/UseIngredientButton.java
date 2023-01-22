package com.team10.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UseIngredientButton extends ScreenButton{
    ChefController chefController;
    
    public UseIngredientButton(int xPos, int yPos, int width, int height, SpriteBatch batch, Camera camera, ChefController chefController){
        super(xPos, yPos, width, height, "", batch, camera);
        this.chefController = chefController;
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
            if(visible) text = "Use " + chefController.chef.getInventory().peek().ingID;
        }
        else visible = false;
        onClick();
        draw();
    }

    /**
     * Add ingredient to the active chef's inventory when clicked
     */
    @Override
    public void clickFunction(){
        System.out.println("UseIngredientButton.java clickFunction() use ingredient: " + chefController.chef.getInventory().peek());
        chefController.chef.getInventory().pop();
    }
}