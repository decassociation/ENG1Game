package com.team10.game;
public class burger extends food{

    private Boolean isFlipped; //because burger must be flipped, need to know if that's been done
    private Boolean needsSalad;
    private Boolean hasSalad;

    public burger(Integer id) {
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
