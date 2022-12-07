package com.team10.game;

public class Pizza {


    private Integer pizzaID;  //identifier for Pizza for later
    private final Integer timeLeft;  //Will be done in seconds or milliseconds - either way integer is good enough
    //private Integer totalPizzas = 0; //will be used to generate sensible ids



    public Integer getID(){
        return(pizzaID);
    }

    public Pizza(Integer pizno){

        pizzaID = pizno;
        timeLeft = 20;
    }



    public Integer checkTime(){
        return(timeLeft);
    }

    public void setID(Integer id){
        pizzaID = id;
    }


}