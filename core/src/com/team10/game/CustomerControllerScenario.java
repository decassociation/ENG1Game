package com.team10.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CustomerControllerScenario extends CustomerController{
    int totalCustomers;
    int customerDelay;
    Camera camera;

    /**
     * Constructor for the subclass CustomerControllerScenario
     * 
     * @param totalCustomers the total number of customers which will need to be served in the scenario
     */
    public CustomerControllerScenario(int totalCustomers, Eng1Game game, Camera camera){
        super(game);
        this.totalCustomers = totalCustomers;
        customerDelay = 10000;
        this.camera = camera;
    }

    /**
     * Define behaviour of creating customers
     * 
     * Start immediately with one customer, add a new one approximately every 10 seconds
     */
    @Override
    protected void createCustomers(){
        if (customerCount == 0 || getCurrentTime()-timeOfLastCustomer >= customerDelay && customerCount < totalCustomers){
            customers.add(new Customer(recipes[generator.nextInt(recipes.length)]));
            customerCount += 1;
            timeOfLastCustomer = getCurrentTime();
            customerDelay = (int) (Math.random() * (12000 - 8000 + 1) + 8500);
            System.out.println("customerDelay: " + customerDelay);
        }
    }

    @Override
    public void update(SpriteBatch batch){
        if(customerCount == totalCustomers && customers.isEmpty())
            try {
                game.changeScreen("scenarioVictory", camera);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        super.update(batch);
    }
    
}
