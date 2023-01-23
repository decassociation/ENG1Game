package com.team10.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
        customers = new ArrayList<>();

        font.setColor(Color.BLACK);
        font.getData().setScale(0.1f, 0.15f);

        font2.setColor(Color.BLACK);
        font2.getData().setScale(0.1f, 0.1f);

    }

    /**
     * Function for getting the current game time in milliseconds
     */
    public long getCurrentTime(){
        return timer.millis() - startTime;
    }

    /**
     * Function for updating customers
     * <p>
     * Draw the timer, draw the customers, manage the creation and deletion of customers. Should be called in render()
     *
     * @param batch the spriteBatch to draw all the customers and text to
     */
    public void update(SpriteBatch batch){
        // draw timer
        font.draw(batch, Long.toString(getCurrentTime()/1000), 1, 30);

        // create new customers
        createCustomers();

        // draw customers
        drawCustomers(batch);

        /* need some code along the lines of:
         * if (customers.get(0).isServed()) customers.remove(0);
         */
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
            if(i == 0 && customers.get(0).yPos >= queuePos){
                font2.draw(batch, customers.get(i).recipe, 0, customers.get(i).yPos + 1.75f);
            }
            // increment the frame
            currentFrame = (currentFrame + 1) % 3;
        }
    }

    /**
     * Procedure for managing the behaviour of creating customers, such as when to add a new one
     * Should be overwritten in a new subclass to allow for different styles of game
     */
    protected void createCustomers(){

    }
}