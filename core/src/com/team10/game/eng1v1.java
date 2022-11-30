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


public class eng1v1 extends ApplicationAdapter {

	private Boolean mainmenu;
	SpriteBatch batch;
	Texture img;
	private Texture chef1img;  //Declares chef1 to be used later as a texture
	private Texture chef2img;  //Declares chef2 to be used later as a texture
	private Boolean paused = false;   //Useful for when we want to implement a pause function
	private OrthographicCamera camera; //prepares camera for use later
	private Rectangle chef1;
	private Rectangle chef2;
	private Integer activeChef = 1;
	private Texture pauseScreen;
	private Integer foodNumber = 0;
	private Texture mainmenupic;





	@Override
	public void create () {
		mainmenu = true;
		mainmenupic = new Texture("mainmeni.png");

		pauseScreen = new Texture("pause.png");

		img = new Texture("layout.png"); //img is the background image

		chef1img = new Texture(Gdx.files.internal("chef12.png")); //chef1 takes chef1(version 2) texture

		chef2img = new Texture(Gdx.files.internal("chef22.png")); //chef2 takes chef2(version 2) texture

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1850, 1040);

		batch = new SpriteBatch();



		chef1 = new Rectangle();
		chef1.x = 500;
		chef1.y = 500;
		chef1.width = 120;
		chef1.height = 60;

		chef2 = new Rectangle();
		chef2.x = 873;
		chef2.y = 400;
		chef2.width = 120;
		chef2.height = 60;

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
		batch.draw(chef1img, chef1.x, chef1.y);
		batch.draw(chef2img, chef2.x, chef2.y);
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
			if(activeChef == 1){
				activeChef = 2;
			} else {
				activeChef = 1;
			}
			//try {Thread.sleep(100);} catch (Exception e) {}  //This is necessary in order to prevent them tabbing back and forth through the different chefs when pressing once, because of how fast it checks without the delay.
		}

	}


	public void chefMovement() {
		//activeChef = chefs.getActiveChef();
		Integer right = Input.Keys.D;   //This way we can have mappable keys for the controls.
		if(activeChef == 1){


			/*
			This section checks if the chef is in a "good" place, i.e. not in a wall.
			If the chef is in a wall, denoted by them being in the given boundaries shown below,
			they are moved to be not in that wall.

			This is an initial and somewhat brutal method, but for now it will stick

			If later a better option is found, then we can ammend this.

			 */
			if(chef1.x >= -100 & chef1.x < 873 & chef1.y > 1040 - 390){
				chef1.y = 1040 - 390;
			}
			if(chef1.y < 1040-200 & chef1.y > 1040-500 & chef1.x > 740){
				chef1.x = 740;
			}






			if (Gdx.input.isKeyPressed(right)) chef1.x += 250 *Gdx.graphics.getDeltaTime();
			if (Gdx.input.isKeyPressed(Input.Keys.A)) chef1.x -= 250 *Gdx.graphics.getDeltaTime();
			if (Gdx.input.isKeyPressed(Input.Keys.S)) chef1.y -= 250 *Gdx.graphics.getDeltaTime();
			if (Gdx.input.isKeyPressed(Input.Keys.W)) chef1.y += 250 *Gdx.graphics.getDeltaTime();
		}else{
			if (Gdx.input.isKeyPressed(Input.Keys.D)) chef2.x += 250 *Gdx.graphics.getDeltaTime();
			if (Gdx.input.isKeyPressed(Input.Keys.A)) chef2.x -= 250 *Gdx.graphics.getDeltaTime();
			if (Gdx.input.isKeyPressed(Input.Keys.S)) chef2.y -= 250 *Gdx.graphics.getDeltaTime();
			if (Gdx.input.isKeyPressed(Input.Keys.W)) chef2.y += 250 *Gdx.graphics.getDeltaTime();
		}

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