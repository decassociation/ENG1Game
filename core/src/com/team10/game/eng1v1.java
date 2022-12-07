package com.team10.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import sun.jvm.hotspot.types.CIntegerField;
import sun.print.DialogOwnerAccessor;


public class eng1v1 extends ApplicationAdapter {

	private Boolean mainmenu;
	SpriteBatch batch;
	Texture img;
	private Texture chef1img;  //Declares chef1 to be used later as a texture
	private Texture chef2img;  //Declares chef2 to be used later as a texture
	private Boolean paused = false;   //Useful for when we want to implement a pause function
	private OrthographicCamera camera; //prepares camera for use later
	private chefs chef1;
	private chefs chef2;
	private chefs activeChef;
	private Texture pauseScreen;
	private Integer foodNumber = 0;
	private Texture mainmenupic;





	@Override
	public void create () {
		mainmenu = true;
		mainmenupic = new Texture("mainmenu.png");

		pauseScreen = new Texture("pause.png");

		img = new Texture("layout.png"); //img is the background image

		chef1img = new Texture(Gdx.files.internal("chef12.png")); //chef1 takes chef1(version 2) texture

		chef2img = new Texture(Gdx.files.internal("chef22.png")); //chef2 takes chef2(version 2) texture

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1850, 1040);

		batch = new SpriteBatch();



		chef1 = new chefs(500,500);
		//chef1.x = 500;
		//chef1.y = 500;
		//chef1.width = 120;
		//chef1.height = 60;
		activeChef = chef1;

		chef2 = new chefs(873,400);
		//chef2.x = 873;
		//chef2.y = 400;
		//chef2.width = 120;
		//chef2.height = 60;

	}

	public burger newBurger(Integer foodID){
		burger b = new burger(foodID);
		return b;
	}
	public pizza newPizza(Integer foodID){
		pizza p = new pizza(foodID);
		return p;
	}
	public salad newSalad(Integer foodID){
		salad s = new salad(foodID);
		return s;
	}
	public potato newPotato(Integer foodID){
		potato po = new potato(foodID);
		return po;
	}







	@Override
	public void render () { //The constantly looping function
		gameLogic(); //Extracts what is being done incase I need to do other none-game logic related things in the render function. This may be changed in future if it turns out nothing else needs doing.


	}




	public void gameLogic(){
		pause(); //constantly checks to see if someone wants to pause/ unpause the game
		if(paused) {

			renderPause(); //Draws the pause screen. For some reason having it elsewhere made the whole game flash like crazy so this was the solution I found.
		} else if(!mainmenu){
			swapChef(); //checks if the chef needs to be changed
			chefMovement();  //does the movement for the chefs
			drawStuff(); //Does the actual drawing of the game


		} else {
			mainmenudraw();
		}
	}

	public void renderPause(){
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(pauseScreen,0 ,0);
		batch.end();
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

	public void mainmenudraw(){
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(mainmenupic,0 ,0);
		batch.end();
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
			mainmenu = false;

		}




	}


	public void swapChef(){
		if (Gdx.input.isKeyJustPressed(Input.Keys.TAB)) {  //For us using two chefs, I have used tab as the key to switch, however with multiple chefs, numbers could be used in place of tab, using an elseif structure.
			if(activeChef == chef1){
				activeChef = chef2;
			} else {
				activeChef = chef1;
			}
			//try {Thread.sleep(100);} catch (Exception e) {}  //This is necessary in order to prevent them tabbing back and forth through the different chefs when pressing once, because of how fast it checks without the delay.
		}

	}


	public void chefMovement() {
		//activeChef = chefs.getActiveChef();
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
		if(activeChef.getX() >= -100 & activeChef.getX() < 873 & activeChef.getY() > 650){  //bottom of counter
			activeChef.setY(-10);
		}
		if(activeChef.getY() < 840 & activeChef.getY() > 560 & activeChef.getX() > 740){  //side of burger area
			activeChef.setX(-10);
		}
		if(activeChef.getX() >= 770 & activeChef.getX() < 1850 & activeChef.getY() > 540){  //bottom of burger area
			activeChef.setY(-10);
		}
		if(activeChef.getY() < 830 & activeChef.getY() > 0 & activeChef.getX() > 1500){  //left side of salad area
			activeChef.setX(-10);
		}
		if(activeChef.getX() >= 0 & activeChef.getX() < 1850 & activeChef.getY() < 230){  //top of lower counter
			activeChef.setY(10);
		}

		if (Gdx.input.isKeyPressed(right)) activeChef.setX(10);
		if (Gdx.input.isKeyPressed(left)) activeChef.setX(-10);
		if (Gdx.input.isKeyPressed(down)) activeChef.setY(-10);
		if (Gdx.input.isKeyPressed(up)) activeChef.setY(10);

		/*
		This whole section just checks which chef is activated and uses their section, and moves them by 250 units each loop.
		If one were to add more chefs, then they could use an elseif structure to check between chefs,
		but in this case, since there are only two option, an if/ else is sufficient
		*/
	}

	public void pause(){ //Flips the status of paused (which is boolean)
		if(Gdx.input.isKeyJustPressed(Input.Keys.P) || (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)))  {
			paused = !paused;
		}
	}



	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}