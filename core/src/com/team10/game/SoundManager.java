package com.team10.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/* Class for global management of sound in the game
 * Allows for simplification of code elsewhere in the project
 * Allows sounds to be modified by other parts of the code, such as volume sliders, allowing changes to be made in real-time
 * so that the game does not need to be restarted to apply changes
 * 
 * To add new sounds to the game, they should be created in the create() procedure and have their volume set in setVolume()
 * To use new sounds, a procedure should be made for playing the sound, which should then be called in the section of code where you want it to start playing 
 */
public class SoundManager {
    private static Music KitchenMusic;
    private static Music stepSound;
    private static FileManager fileManager;
    private static float volume;

    /**
     * Initialise the class, called in Eng1Game
     * 
     * Different to a constructor because this class is static and we are not creating an instance of it
     */
    public static void create(){
        // read volume from settings file
        fileManager = new FileManager();
        volume = Float.valueOf(fileManager.read("volume"));

        // create the kitchen music
        KitchenMusic = Gdx.audio.newMusic(Gdx.files.internal("KitchenMusic.mp3"));
        KitchenMusic.setLooping(true);

        // create the step sound
        stepSound = Gdx.audio.newMusic(Gdx.files.internal("step.mp3"));
        stepSound.setOnCompletionListener(new Music.OnCompletionListener() {
            @Override
            public void onCompletion(Music music) {
                stepSound.dispose();
            }
        });
        stepSound.setLooping(false);

        // set the volume of all sounds
        setVolume(volume);
    }

    /**
     * Procedure for setting the volume of all sounds in the game
     * 
     * @param newVolume the volume coefficient for changing the volume of sounds
     */
    public static void setVolume(float newVolume){
        volume = newVolume;
        KitchenMusic.setVolume(0.0025f * volume);
        stepSound.setVolume(0.0025f * volume);
    }

    /**
     * Procedures for playing and pausing all the sounds
     */
    public static void playKitchenMusic(){
        KitchenMusic.play();
    }

    public static void pauseKitchenMusic(){
        KitchenMusic.pause();
    }

    public static void playStepSound(){
        stepSound.play();
    }
}
