package com.team10.game;
public class burger {

    private Boolean flipped = false; //because burger must be flipped, need to know if that's been done
    private final Integer burgerID;  //identifier for burger for later
    private final Integer timeLeft;  //Will be done in seconds or milliseconds - either way integer is good enough
    //private Integer totalBurgers = 0;



    public Integer getID(){
        return(burgerID);
    }

    public burger(Integer noburg){
        burgerID = noburg;
        flipped = false;
        timeLeft = 20;
    }

    public Boolean checkFlipped(){
        return(flipped);
    }

    public Integer checkTime(){
        return(timeLeft);
    }




}
