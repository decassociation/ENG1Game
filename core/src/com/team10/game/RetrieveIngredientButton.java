package com.team10.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RetrieveIngredientButton extends UseIngredientButton{

    public RetrieveIngredientButton(int yPos, SpriteBatch batch, Camera camera, ChefController chefController, CookingStation fryingStation, CookingStation bakingStation, CookingStation cuttingStation){
        super(yPos, batch, camera, chefController, fryingStation, bakingStation, cuttingStation);
    }

    /**
     * Update procedure to set visibility, draw the button and check if it is clicked, call in render() instead of calling onClick() and draw()
     */
    @Override
    public void update(){
        // make visible if the chef is in the right area and the corresponding station is done
        if(chefController.chef.getArea().equals("frying_station") && fryingStation.done) visible = true;
        else if(chefController.chef.getArea().equals("cutting_station") && cuttingStation.done) visible = true;
        else if(chefController.chef.getArea().equals("baking_station") && bakingStation.done) visible = true;
        else visible = false;

        // dynamically change the text based on what item is on the top of the stack
        setText();

        onClick();
        draw();
    }

    @Override
    protected void setText(){
        if(visible){
            text = "Get ";
            if (chefController.chef.getArea().equals("frying_station")) text += fryingStation.ingredients.get(0).ingID;
            if (chefController.chef.getArea().equals("baking_station")) text += bakingStation.ingredients.get(0).ingID;
            if (chefController.chef.getArea().equals("cutting_station")) text += cuttingStation.ingredients.get(0).ingID;
        }
    }

    /**
     * Add ingredient to the active chef's inventory when clicked
     */
    @Override
    public void clickFunction(){
        
        if(chefController.chef.getArea().equals("frying_station")) chefController.chef.addFood(fryingStation.removeIngredient());
        if(chefController.chef.getArea().equals("baking_station")) chefController.chef.addFood(bakingStation.removeIngredient());
        if(chefController.chef.getArea().equals("cutting_station")) chefController.chef.addFood(cuttingStation.removeIngredient());
    }
    
}
