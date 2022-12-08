package com.team10.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    private Boolean paused = false;   //Useful for when we want to implement a pause function
    private Eng1Game game;

    private Chef chef1;
    private Chef chef2;
    private Chef activeChef;

    private Texture chef1img;  //Declares chef1 to be used later as a texture
    private Texture chef2img;  //Declares chef2 to be used later as a texture
    private Texture img;

    private Boolean mainmenu;

    private SpriteBatch batch;

    private OrthographicCamera camera;
    public GameScreen(Eng1Game game) {
        this.game = game;

        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1850, 1040);
        img = new Texture("layout.png"); //img is the background image

        chef1img = new Texture(Gdx.files.internal("chef12.png")); //chef1 takes chef1(version 2) texture

        chef2img = new Texture(Gdx.files.internal("chef22.png")); //chef2 takes chef2(version 2) texture

        mainmenu = false;
        chef1 = new Chef(500,500);
        //chef1.x = 500;
        //chef1.y = 500;
        //chef1.width = 120;
        //chef1.height = 60;
        activeChef = chef1;

        chef2 = new Chef(873,400);
        //chef2.x = 873;
        //chef2.y = 400;
        //chef2.width = 120;
        //chef2.height = 60;

    }

    @Override
    public void show() {

    }




    public void swapChef(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.TAB)) {  //For us using two Chef, I have used tab as the key to switch, however with multiple Chef, numbers could be used in place of tab, using an elseif structure.
            if(activeChef == chef1){
                activeChef = chef2;
            } else {
                activeChef = chef1;
            }
        }

    }

    public void chefMovement() {
        //activeChef = Chef.getActiveChef();
        Integer up = Input.Keys.W; //This way we can have mappable keys for the controls.
        Integer left = Input.Keys.A;
        Integer down = Input.Keys.S;
        Integer right = Input.Keys.D;

		/*
		This section checks if the chef is in a "good" place, i.e. not in a wall.
		If the chef is in a wall, denoted by them being in the given boundaries shown below,
		they are moved to be not in that wall.

		This is an initial and somewhat brutal method, but for now it will stick

		If later a better option is found, then we can ammend this.

		 */
        if(activeChef.getX() <= 0){  //left side of map
            activeChef.setX(10);
        }
        if(activeChef.getY() <= 0){  //bottom of the map
            activeChef.setY(10);
        }
        if(activeChef.getX() >= -100 && activeChef.getX() < 873 && activeChef.getY() > 650){  //bottom of counter
            activeChef.setY(-10);
        }
        if(activeChef.getY() < 840 && activeChef.getY() > 560 && activeChef.getX() > 740){  //side of Burger area
            activeChef.setX(-10);
        }
        if(activeChef.getX() >= 770 && activeChef.getX() < 1850 && activeChef.getY() > 540){  //bottom of Burger area
            activeChef.setY(-10);
        }
        if(activeChef.getY() < 830 && activeChef.getY() > 0 && activeChef.getX() > 1500){  //left side of Salad area
            activeChef.setX(-10);
        }
        if(activeChef.getX() >= 0 && activeChef.getX() < 1850 && activeChef.getY() < 230){  //top of lower counter
            activeChef.setY(10);
        }

        if (Gdx.input.isKeyPressed(right)) activeChef.setX(10);
        if (Gdx.input.isKeyPressed(left)) activeChef.setX(-10);
        if (Gdx.input.isKeyPressed(down)) activeChef.setY(-10);
        if (Gdx.input.isKeyPressed(up)) activeChef.setY(10);

		/*
		This whole section just checks which chef is activated and uses their section, and moves them by 250 units each loop.
		If one were to add more Chef, then they could use an elseif structure to check between Chef,
		but in this case, since there are only two option, an if/ else is sufficient
		*/
    }

    public void drawStuff(){
        ScreenUtils.clear(1, 0, 0, 1);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(img, 0, 0);
        batch.draw(chef1img, chef1.getX(), chef1.getY());
        batch.draw(chef2img, chef2.getX(), chef2.getY());
        batch.end();
    }

    private void gameLogic(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            try {
                game.changeScreen("pause");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(!mainmenu){
            swapChef(); //checks if the chef needs to be changed
            chefMovement();  //does the movement for the Chef
            drawStuff(); //Does the actual drawing of the game


        } else {
            mainmenudraw();
        }
    }



    public void mainmenudraw(){
        try {
            game.changeScreen("mainMenu");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render (float delta) { //The constantly looping function
        gameLogic(); //Extracts what is being done incase I need to do other none-game logic related things in the render function. This may be changed in future if it turns out nothing else needs doing.


    }

    @Override
    public void resize(int width, int height) {

    }

    public void pause() {

    }

    public void resume() {

    }

    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
