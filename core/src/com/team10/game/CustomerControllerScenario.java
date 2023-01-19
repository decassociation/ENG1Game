package com.team10.game;

public class CustomerControllerScenario extends CustomerController{
    int totalCustomers;
    int customerDelay;

    /**
     * Constructor for the subclass CustomerControllerScenario
     * 
     * @param totalCustomers the total number of customers which will need to be served in the scenario
     */
    public CustomerControllerScenario(int totalCustomers){
        super();
        this.totalCustomers = totalCustomers;
        customerDelay = 10000;
    }

    /**
     * Define behaviour of creating customers
     * 
     * Start immediately with one customer, add a new one approximately every 10 seconds
     */
    @Override
    protected void createCustomers(){
        if (customers.size() == 0 || getCurrentTime()-timeOfLastCustomer >= customerDelay && customerCount < totalCustomers){
            customers.add(new Customer(recipes[generator.nextInt(recipes.length)]));
            customerCount += 1;
            timeOfLastCustomer = getCurrentTime();
            customerDelay = (int) (Math.random() * (12000 - 8000 + 1) + 8500);
            System.out.println("customerDelay: " + customerDelay);
        }
    }
    
}
