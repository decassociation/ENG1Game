package com.team10.game;
/*this is only a loose outline of what the Salad class will need
 * it will require a rework at some point to properly implement
 * the composition features, but right now I don't have the time
 * or flexibility of mind.
 */


public class Salad{

    private Integer saladID;  //identifier for Salad for later
    private Integer timeLeft;  //Will be done in seconds or milliseconds - either way integer is good enough
    //turns out Salad will be more complicated that the others, since it requires ingredients and such, which also need to be prepared
    /*
     * Salad will need:
     *
     * lettuce
     * tomat
     * carrots
     * sauce
     *
     */
    private Boolean lettuce;
    private Boolean tomato;
    private Boolean carrots;
    private Boolean sauce;



    public Integer getID(){
        return(saladID);
    }

    public Salad(Integer salno){
        lettuce = false;
        tomato = false;
        carrots = false;
        sauce = false;
        saladID = salno;
    }



    public Integer checkTime(){
        return(timeLeft);
    }

    public void setID(Integer id){
        saladID = id;
    }





}