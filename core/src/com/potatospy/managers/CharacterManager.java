package com.potatospy.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.potatospy.entities.Letter;

import java.util.ArrayList;
import java.util.List;

/*
For each game, some number of Letters are added to the letters list
(dependent on difficulty).
As timePassed increases, Letters are put into play.
The Letters become available to GameScreen to render and update.

If a Letter reaches the bottom or is caught then it is marked as caught or missed.
GameScreen takes care of the score.

When letters list is empty, it is the end of the round.
 */




public class CharacterManager {


    // == Fields ==
    List<Letter> letters;  // List of letters
    private int difficulty; /* Passed to Letter to determine speed and range of
                               characters used. */
    private float timePassed;  // For determining if a new spriteCharacter should be generated
    private float generateNewLetterTimeThreshold; // For determining if a new letter should be added to the screen
    private float timeTemp;                       // For determining if a new letter should be added to the screen

    private BitmapFont characterFont;



    // == Constructor ==
    public CharacterManager(int difficulty) {

        this.letters = new ArrayList<>();
        this.difficulty = difficulty;
        this.timePassed = 0f;
        this.generateNewLetterTimeThreshold = 60f/difficulty;
        this.timeTemp = 0f;

        // Font
        characterFont = FontManager.prepareFont();
        characterFont.setColor(Color.WHITE);
        characterFont.getData().setScale(0.5f);

    }


    // == Get Set ==
    public List<Letter> getLetters() { return letters; }
    public void setLetters(List<Letter> letters) { this.letters = letters; }

    public int getDifficulty() { return difficulty; }
    public void setDifficulty(int difficulty) { this.difficulty = difficulty; }

    public BitmapFont getCharacterFont() { return characterFont; }
    public void setCharacterFont(BitmapFont characterFont) { this.characterFont = characterFont; }


    // == Manager methods ==

    // Create a list filled with letters appropriate
    // for the difficulty (Higher difficulty, wider range of characters)
    public void generateKana(){

        // Add 10 letters
        for(int i=0; i < 10; i++){
            letters.add(new Letter(difficulty));
        }
    }

    // On every Game update(), Character positions and existence are updated.
    public void update(float timePassedSinceLastUpdateUpdate){

        // How much time has passed since the last time update was called
        timeTemp += timePassedSinceLastUpdateUpdate;
        //System.out.println("TIME: " + timeTemp + "\n");
        // Each letter that is in play, update its position
        // If 5 seconds has passed,
        // then set one !inPlay character to inPlay
        //
        //letters.forEach((letter)->{System.out.println(letter.getCharacter());});
        letters.forEach((letter)->{

            if(letter.isInPlay()) {
                letter.setCharacterY(
                        letter.getCharacterY() + letter.getSpeed());
            }
            else if (generateNewLetterTimeThreshold > timeTemp && !letter.isInPlay()) {

                letter.setInPlay(true);
                timeTemp=0f;

                return;
            }
        });
    }
}
