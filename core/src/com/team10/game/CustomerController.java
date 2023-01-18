package com.team10.game;


import java.time.Clock;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CustomerController {
    
    Clock timer;
    long startTime;
    long timeOfLastCustomer;
    ArrayList<Customer> customers;
    int customerCount;
    Texture texture = new Texture(Gdx.files.internal("Customer.png"));


    public CustomerController(){
        timer = Clock.systemUTC();
        startTime = timer.millis();
        timeOfLastCustomer = 0;
        customers = new ArrayList<Customer>();

    }

    public long getCurrentTime(){
        return timer.millis() - startTime;
    }

    public void update(SpriteBatch batch, Camera camera){
        // draw timer
        BitmapFont font = new BitmapFont();
        batch.setProjectionMatrix(camera.combined);
        font.setColor(Color.BLACK);
        font.getData().setScale(5);
        font.draw(batch, Long.toString(getCurrentTime()/1000), 10, 1030);

        // create new customers
        createCustomers_scenario(5);
    }

    public void drawCustomers(SpriteBatch batch){
        for (int i = 0; i < customers.size(); i++) {
            batch.draw(texture, 50, i + 50, 100.0f, 100.0f);
        }
    }

    private void createCustomers_scenario(int totalCustomers){
        if (getCurrentTime()-timeOfLastCustomer >= 10000 && customerCount < totalCustomers){
            customers.add(new Customer());
            customerCount += 1;
            timeOfLastCustomer = getCurrentTime();
            System.out.println(customers.toString());
        }
    }
}
