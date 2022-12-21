package com.team10.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.team10.game.Eng1Game;

public class ResumeButton extends MenuButton {
    private Eng1Game game;

    /**
     * Constructor for Resume button, text is removed as is hardcoded
     *
     * @param game the Eng1Game object
     */
    public ResumeButton(int xPos, int yPos, int width, int height, SpriteBatch batch, Camera camera, Eng1Game game) {
        super(xPos, yPos, width, height, "Resume", batch, camera);
        this.game = game;
    }

    /**
     * Constructor for MenuButton
     *
     * @param xPos   the x coordinate
     * @param yPos   the y coordinate
     * @param width  the width of the button
     * @param height the height of the button
     * @param text   the text to display on the button
     * @param batch  the SpriteBatch to draw the button to
     * @param camera the camera being used to render the game, needed to get correct
     *               projections of coordinates for rendering and clicking
     */
    public ResumeButton(int xPos, int yPos, int width, int height, String text, SpriteBatch batch, Camera camera) {
        super(xPos, yPos, width, height, text, batch, camera);
    }

    /**
     * Change the screen back to the GameScreen when clicked
     */
    @Override
    public void clickFunction() {
        try {
            game.changeScreen("game");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    }
