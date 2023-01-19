package com.team10.game;


import java.time.Clock;
import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType.Bitmap;

public class CustomerController {
    
    Clock timer;
    long startTime;
    long timeOfLastCustomer;
    ArrayList<Customer> customers;
    int customerCount;
    Texture texture = new Texture(Gdx.files.internal("Customer.png"));
    BitmapFont font = new BitmapFont();
    BitmapFont font2 = new BitmapFont();

    // array of names of the different possible recipes
    String recipes[] = {"burger", "salad"};

    Random generator = new Random();


    public CustomerController(){
        timer = Clock.systemUTC();
        startTime = timer.millis();
        timeOfLastCustomer = 0;
        customers = new ArrayList<Customer>();

        font.setColor(Color.BLACK);
        font.getData().setScale(0.1f, 0.15f);

        font2.setColor(Color.BLACK);
        font2.getData().setScale(0.1f, 0.1f);

    }

    public long getCurrentTime(){
        return timer.millis() - startTime;
    }

    public void update(SpriteBatch batch, Camera camera){
        // draw timer
        
        font.draw(batch, Long.toString(getCurrentTime()/1000), 1, 30);

        // create new customers
        createCustomers_scenario(5);

        // draw customers
        drawCustomers(batch);
    }

    public void drawCustomers(SpriteBatch batch){
        for (int i = 0; i < customers.size(); i++) {
            batch.draw(texture, 5, customers.get(i).yPos, 2.0f, 2.0f);
            if(customers.get(i).yPos < 10 - (3*i)){
                customers.get(i).yPos += 0.2f;
            }
            if(i == 0 && customers.get(0).yPos >= 10 - (3*i)){
                font2.draw(batch, customers.get(i).recipe, 0, customers.get(i).yPos + 1.75f);
            }
        }
    }

    private void createCustomers_scenario(int totalCustomers){
        if (customers.size() == 0 || getCurrentTime()-timeOfLastCustomer >= 10000 && customerCount < totalCustomers){
            customers.add(new Customer(recipes[generator.nextInt(recipes.length)]));
            customerCount += 1;
            timeOfLastCustomer = getCurrentTime();
            System.out.println(customers.toString());
        }

        if (customers.size() >= 5 && getCurrentTime()-timeOfLastCustomer >= 10000){
            customers.remove(0);
        }
        
    }
}
