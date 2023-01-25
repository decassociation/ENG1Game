package com.team10.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.team10.ingredients.Ingredient;

import java.time.Clock;
import java.util.ArrayList;
import java.util.Random;

public class CustomerController {

    Clock timer;
    long startTime;
    long timeOfLastCustomer;
    ArrayList<Customer> customers;
    int customerCount;
    Texture[] texture = {new Texture(Gdx.files.internal("customer1.png")), new Texture(Gdx.files.internal("customer2.png")), new Texture(Gdx.files.internal("customer3.png"))};
    BitmapFont font = new BitmapFont();
    BitmapFont font2 = new BitmapFont();
    int queuePos;
    int currentFrame = 0;
    int frameDelay = 250; // delay between each frame change in milliseconds
    long lastFrameChange = System.currentTimeMillis(); // time of the last frame change
    ArrayList<Ingredient> servedIngredients;

    // array of names of the different possible recipes
    String[] recipes = {"burger", "salad"};

    Random generator = new Random();


    /**
     * Constructor for CustomerController
     */
    public CustomerController(){
        timer = Clock.systemUTC();
        startTime = timer.millis();
        timeOfLastCustomer = 0;
        customers = new ArrayList<Customer>();
        servedIngredients = new ArrayList<>();

        font.setColor(Color.BLACK);
        font.getData().setScale(2f, 2f);

        font2.setColor(Color.BLACK);
        font2.getData().setScale(2f, 2f);

    }

    /**
     * Function for getting the current game time in milliseconds
     */
    public long getCurrentTime(){
        return timer.millis() - startTime;
    }

    /**
     * Function for updating customers
     *
     * Draw the timer, draw the customers, manage the creation and deletion of customers. Should be called in render()
     *
     * @param batch the spriteBatch to draw all the customers and text to
     */
    public void update(SpriteBatch batch){
        

        // create new customers
        createCustomers();

        // draw customers
        drawCustomers(batch);

        // check for wanted ingredients
        serve();

        /* need some code along the lines of:
         * if (customers.get(0).isServed()) customers.remove(0);
         */
    }

    public void drawText(SpriteBatch batch){
        // draw timer
        font.draw(batch, Long.toString(getCurrentTime()/1000), 15, 470);

        for (int i = 0; i < customers.size(); i++) {
            if(i == 0 && customers.get(0).yPos >= queuePos){
                font2.draw(batch, customers.get(i).recipe, 160, customers.get(i).yPos * 16 + 55f);
            }
        }
    }

    /**
     * Draw the customers and text for their requests
     *
     * @param batch the spriteBatch to draw all images and text to
     */
    public void drawCustomers(SpriteBatch batch){
        for (int i = 0; i < customers.size(); i++) {
            batch.draw(texture[currentFrame], 4, customers.get(i).yPos, 4f, 4f);
            queuePos = 9 - (3*i);
            // move the customers up to their position in the queue from the bottom of the screen
            if(customers.get(i).yPos < queuePos){
                customers.get(i).yPos += 0.2f;
            }
            
            // increment the frame with delay
            if(System.currentTimeMillis() - lastFrameChange > frameDelay){
                currentFrame = (currentFrame + 1) % 3;
                lastFrameChange = System.currentTimeMillis();
            }
        }
    }

    protected void createCustomers() {
        // if the time since the last customer is greater than 5 seconds, create a new customer
        if(getCurrentTime() - timeOfLastCustomer > 5000){
            customers.add(new Customer(recipes[generator.nextInt(recipes.length)]));
            timeOfLastCustomer = getCurrentTime();
        }
    }

    // check if customer is served correct recipe
    protected void serve(){
        // burger recipe
        if(customers.get(0).recipe.equals("burger")){
            boolean burger = false;
            boolean cutBun = false;
            boolean sauce = false;
            boolean cheese = false;
            Ingredient current;

            // check if top items are correct items
            if(servedIngredients.size() >= 4){
                for (int i = servedIngredients.size() - 4; i < servedIngredients.size(); i++) {
                    current = servedIngredients.get(i);
                    if(current.name.equals("Cooked Burger")){
                        burger = true;
                    }
                    if(current.name.equals("Cut Bun")){
                        cutBun = true;
                    }
                    if(current.name.equals("Sauce")){
                        sauce = true;
                    }
                    if(current.name.equals("Cheese")){
                        cheese = true;
                    }
                }
            }

            // if all ingredients present, remove customer and the ingredients
            if (burger && cutBun && sauce && cheese){
                customers.remove(0);
                servedIngredients.clear();

            }
        }
    }
}
