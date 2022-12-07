package com.team10.game;
public class Burger extends Food {

    private Boolean isFlipped; //because Burger must be flipped, need to know if that's been done
    private Boolean needsSalad;
    private Boolean hasSalad;

    public Burger(Integer id) {
        super(id);
    }


    public Boolean getIsFlipped(){
        return isFlipped;
    }

    public Boolean getNeedsSalad(){
        return needsSalad;
    }

    public Boolean getHasSalad(){
        return hasSalad;
    }


}
