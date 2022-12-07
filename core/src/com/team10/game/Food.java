package com.team10.game;

public class Food {

    private Boolean isReady;
    private Integer timeLeft; //will be done in seconds or milliseconds - either way integer is good enough
    private Integer foodID; //will be useful for identifying foods later I think




    public Food(Integer id){
        foodID = id;
    }
    public Boolean getIsReady(){
        return isReady;
    }
    public Integer getTimeLeft(){
        return timeLeft;
    }
    public Integer getFoodID(){
        return foodID;
    }


}
