package com.team10.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class Chef {
    private Rectangle chef;
    private Integer xPos;
    private Integer yPos;
    private Boolean isWorking;


    public Chef(Integer x, Integer y) {
        xPos = x;
        yPos = y;
        isWorking = false;

        //Chef instantiation
        chef = new Rectangle();
        chef.x = x;
        chef.y = y;
        chef.width = 120;
        chef.height = 60;
    }

    public Integer getX() {
        return xPos;
    }
    public Integer getY() {
        return yPos;
    }
    public void setX(Integer xValue) {
        //chef will only move if the chef is not currently working at a station
        if (!isWorking) {
            chef.x += xValue * Gdx.graphics.getDeltaTime();
            xPos += xValue;
        }
    }
    public void setY(Integer yValue) {
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
