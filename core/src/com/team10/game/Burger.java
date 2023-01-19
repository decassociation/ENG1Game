package com.team10.game;

import java.lang.annotation.Inherited;

public class Burger extends Food {

    //    private Boolean isFlipped; //This is an extra step that could be added later to add spice to the game.
//    private Boolean needsSalad;
//    private Boolean hasSalad;
    private Boolean isCooked;

    public Burger(Integer id) {
        super(id);
        isCooked = false;
    }







    public Boolean getIsCooked(){
        return isCooked;
    }

    public void setIsCooked(){
        isCooked = true;
    }


//    public Boolean getIsFlipped(){
//        return isFlipped;
//    }
//
//    public Boolean getNeedsSalad(){
//        return needsSalad;
//    }
//
//    public Boolean getHasSalad(){
//        return hasSalad;
//    }




}
