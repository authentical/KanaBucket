package com.potatospy.managers;

import com.potatospy.entities.Letter;

import java.util.ArrayList;
import java.util.List;

/*
For each game, some number of Letters are added to the letters list
(dependent on difficulty).
As timePassed increases, Letters are put into play.
The Letters are now available to GameScreen to render and update.
The characters each have:
A speed
An X position (which currently will remain static)
An isKana that will determine whether points are added and subtracted for catching it
in the bucket.

If a Letter reaches the bottom or is caught then it is marked as caught or missed.
GameScreen takes care of the score.

When letters list is empty, it is the end of the round.
 */




public class CharacterManager {


    // == Fields ==
    List<Letter> letters;  // List of letters
    private int difficulty; // Passed to Letter to determine speed and range of
                            // characters used.
    private float timePassedSinceLastUpdate;  // For determining if a new spriteCharacter should be generated
    private float lastTimePassed;             // For determining if a new spriteCharacter should be generated

    // == Constructor ==
    public CharacterManager(int difficulty) {

        this.letters = new ArrayList<>();
        this.difficulty = difficulty;
        this.timePassedSinceLastUpdate = 0;
        this.lastTimePassed=0;
    }


    // == Get Set ==
    public List<Letter> getLetters() { return letters; }
    public void setLetters(List<Letter> letters) { this.letters = letters; }

    public int getDifficulty() { return difficulty; }
    public void setDifficulty(int difficulty) { this.difficulty = difficulty; }


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
    public void update(float timePassed){

        // How much time has passed since the last time update was called
        timePassedSinceLastUpdate = timePassed-lastTimePassed;
        lastTimePassed = timePassed;

        // Each letter that is in play, update its position
        // If 5 seconds has passed,
        // then set one !inPlay character to inPlay
        //
        letters.forEach((letter)->{

            if(letter.isInPlay()) {
                letter.setCharacterY(
                        letter.getCharacterY() + 10 * letter.getSpeed());
            }
            else if (timePassedSinceLastUpdate > 500 && !letter.isInPlay()) {

                letter.setInPlay(true);
                return;
            }
        });
    }

//    public List<Letter> getInPlayLetters(){
//
//
//
//
//    }
}
