package com.team10.game;

public class eng1v1Builder {
    private MainGame game;

    public eng1v1Builder setGame(MainGame game) {
        this.game = game;
        return this;
    }

    public eng1v1 createEng1v1() {
        return new eng1v1(game);
    }
}