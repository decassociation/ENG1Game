package com.team10.game;

public class Customer {
    
    boolean served;
    String recipe;
    float yPos;

    public Customer(String recipe){
        served = false;
        this.recipe = recipe;
        yPos = -5;
        
    }
}
