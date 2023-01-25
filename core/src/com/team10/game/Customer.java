package com.team10.game;

public class Customer {
    
    boolean served;
    String recipe;
    float yPos;

    /**
     * Constructor for Customer
     * 
     * no need for xPos since they just queue up vertically, customer controller will deal with textures, rendering etc.
     * 
     * @param recipe the meal they are requesting, selected from the array of options in CustomerController
     */
    public Customer(String recipe){
        served = false;
        this.recipe = recipe;
        yPos = -5;
        
    }
}
