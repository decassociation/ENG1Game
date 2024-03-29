package com.team10.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.team10.ingredients.Ingredient;

import java.util.Stack;

public class Chef {
    private Rectangle chef;
    private float xPos;
    private float yPos;
    private Boolean isWorking;
    private Stack<Ingredient> inventory; //Where the items the chef is carrying can be stored
    private String area;



    /**
     * Constructor for the chef class
     * 
     * @param x the initial x coordinate
     * @param y the initial y coordinate
     */
    public Chef(Integer x, Integer y) {
        xPos = x;
        yPos = y;
        isWorking = false;
        inventory = new Stack<Ingredient>();
        area = "";

        //Chef instantiation
        chef = new Rectangle();
        chef.x = x;
        chef.y = y;
        chef.width = 20;
        chef.height = 36;




    }
    /*
     * This is the gets and sets for locating the chefs.
     * This can be expanded if further areas are required.
     * At the time of writing, 18/01/2023, all are defaulted to true
     * but this needs to be changed for the final game.
     */

    public String getArea(){
        return area;
    }
    public void setArea(String location){
        area = location;
    }

    //Inventory thing. Kinda temporary cos I think lucy is working on a better one,
    //but I needed one to test the burger and zone stuff
    public Stack<Ingredient> getInventory(){
        return(inventory);
    }

    //Adds food to the inventory
    public void addFood(Ingredient ing) {
        if (inventory.size() < 6) {
            inventory.push(ing);
        }
    }

    //Removes top food from inventory and returns it
    public Ingredient popFood() {
        if (inventory.size() > 0) {
            return inventory.pop();
        }
        return null;
    }



    public float getWidth(){return chef.width;}
    public float getHeight(){return chef.height;}

    public float getX() {
        return xPos;
    }
    public float getY() {
        return yPos;
    }
    public void setX(float xValue) {
        //chef will only move if the chef is not currently working at a station
        if (!isWorking) {
            chef.x += xValue * Gdx.graphics.getDeltaTime();
            xPos += xValue;
        }
    }
    public void setY(float yValue) {
        if (!isWorking) {
            chef.y += yValue * Gdx.graphics.getDeltaTime();
            yPos += yValue;
        }
    }

    public boolean isWorking() {
        return isWorking;
    }
    public void updateWorking() {
        isWorking = !isWorking;
    }
}
