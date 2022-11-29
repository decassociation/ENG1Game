package com.team10.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;


public class eng1v1 implements Screen {
	SpriteBatch batch;
	Texture img;
	private final float chefSwitchCooldownTime = 0.5f;
	private float timeSinceLastChefSwitch = 0f;
	private boolean chefSwitchCooldown = false;
	private Texture chef1img;  //Declares chef1 to be used later as a texture
	private Texture chef2img;  //Declares chef2 to be used later as a texture
	private Boolean paused = false;   //Useful for when we want to implement a pause function
	private OrthographicCamera camera; //prepares camera for use later
	private Rectangle chef1;
	private Rectangle chef2;
	private Integer activeChef = 1;

	public eng1v1(MainGame game) {
	}

	public eng1v1() {

	}


	@Override
	public void show () {



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
		chef2.x = 500;
		chef2.y = 400;
		chef2.width = 120;
		chef2.height = 60;



	}


	public void render (float delta) {
		gameLogic(delta);
		ScreenUtils.clear(1, 0, 0, 1);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
		batch.begin();
		batch.draw(chef1img, chef1.x, chef1.y);
		batch.draw(chef2img, chef2.x, chef2.y);
		batch.end();


	}

	@Override
	public void resize(int width, int height) {
		// TODO: Implement this method
	}


	public void gameLogic(float delta){


		swapChef(delta);

		chefMovement();




	}

	public void swapChef(float delta){

		if (chefSwitchCooldown){
			System.out.println(timeSinceLastChefSwitch);
			timeSinceLastChefSwitch += delta;
			if (timeSinceLastChefSwitch >= chefSwitchCooldownTime){
				chefSwitchCooldown = false;
				timeSinceLastChefSwitch = 0f;
			}
			return;
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.TAB))  {
			if(activeChef == 1){
				activeChef = 2;
			} else {
				activeChef = 1;
			}
			chefSwitchCooldown = true;
			timeSinceLastChefSwitch = 0f;
		}

	}







	public void chefMovement() {
		//activeChef = chefs.getActiveChef();
		if(activeChef == 1){
			if (Gdx.input.isKeyPressed(Input.Keys.D)) chef1.x += 200 *Gdx.graphics.getDeltaTime();
			if (Gdx.input.isKeyPressed(Input.Keys.A)) chef1.x -= 200 *Gdx.graphics.getDeltaTime();
			if (Gdx.input.isKeyPressed(Input.Keys.S)) chef1.y -= 200 *Gdx.graphics.getDeltaTime();
			if (Gdx.input.isKeyPressed(Input.Keys.W)) chef1.y += 200 *Gdx.graphics.getDeltaTime();
		}else{

			if (Gdx.input.isKeyPressed(Input.Keys.D)) chef2.x += 200 *Gdx.graphics.getDeltaTime();
			if (Gdx.input.isKeyPressed(Input.Keys.A)) chef2.x -= 200 *Gdx.graphics.getDeltaTime();
			if (Gdx.input.isKeyPressed(Input.Keys.S)) chef2.y -= 200 *Gdx.graphics.getDeltaTime();
			if (Gdx.input.isKeyPressed(Input.Keys.W)) chef2.y += 200 *Gdx.graphics.getDeltaTime();

		}


	}

	public void pause(){
		paused = !paused;
		try{Thread.sleep(69);} catch(Exception e){}
	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}


	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}



