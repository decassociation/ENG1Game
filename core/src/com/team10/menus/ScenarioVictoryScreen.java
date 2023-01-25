package com.team10.menus;

import com.badlogic.gdx.graphics.Camera;
import com.team10.game.Eng1Game;
import com.team10.game.Eng1Screen;

public class ScenarioVictoryScreen extends Eng1Screen{
    
    Camera camera;

    public ScenarioVictoryScreen(Eng1Game game, Camera camera){
        super(game);
        this.camera = camera;
    }
}
