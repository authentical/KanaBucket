package com.potatospy.managers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.potatospy.entities.GameCharacter;
import com.potatospy.util.FontLoader;

import java.util.ArrayList;
import java.util.List;

/*
For each game, some number of GameCharacter are added to the gameCharacters list.
As timePassed increases, For each game, some number of GameCharacters are added to the gameCharacters list.
 are put into play at some rate dependent upon difficulty.
The GameCharacters become available to GameScreen to render and update.

If a GameCharacter reaches the bottom or is caught then it is marked as caught or missed.
GameScreen takes care of the score.

When gameCharacters list is empty, it is the end of the round.
 */




public class CharacterManager {


    // == Fields ==
    List<GameCharacter> gameCharacters;  // List of gameCharacters
    private int difficulty; /* Passed to GameCharacter to determine speed and range of
                               characters used. */
    private float timePassed;  // For determining if a new spriteCharacter should be generated
    private float generateNewCharacterTimeThreshold; // For determining if a new character should be added to the screen
    private float timeTemp;                       // For determining if a new character should be added to the screen
    private int nextIndexToPutInPlay;   // For determining which character to drop next
    private boolean characterListEmpty;
    private boolean charactersInPlay;

    private BitmapFont characterFont;


    // == Constructor ==
    public CharacterManager(int difficulty) {

        this.gameCharacters = new ArrayList<>();
        this.difficulty = difficulty;
        this.timePassed = 0f;
        this.generateNewCharacterTimeThreshold = 3f/difficulty;
        this.timeTemp = 3f;     // Setting this to 3 allows the initial character to drop immediately when game starts
        this.nextIndexToPutInPlay = 0;  // TOdo there must be 10 indices in gameCharacters or this'll blow up
        this.characterListEmpty = false;
        this.charactersInPlay =false;

        // Font
        characterFont = FontLoader.prepareFont();
        characterFont.setColor(Color.WHITE);
        characterFont.getData().setScale(0.5f);
    }


    // == Get Set ==

    public List<GameCharacter> getGameCharacters() { return gameCharacters; }
    public void setGameCharacters(List<GameCharacter> gameCharacters) { this.gameCharacters = gameCharacters; }

    public int getDifficulty() { return difficulty; }
    public void setDifficulty(int difficulty) { this.difficulty = difficulty; }

    public BitmapFont getCharacterFont() { return characterFont; }
    public void setCharacterFont(BitmapFont characterFont) { this.characterFont = characterFont; }

    public boolean isCharacterListEmpty() { return characterListEmpty; }


// == Manager methods ==

    // Create a list filled with gameCharacters appropriate
    // for the difficulty (Higher difficulty, wider range of characters)
    public void generateListOfCharacters(){

        // Add 10 gameCharacters
        for(int i=0; i < 10; i++){
            gameCharacters.add(new GameCharacter(difficulty));
        }
    }

    // On every Game update(), Character positions are updated.
    public void update(float timePassedSinceLastUpdateUpdate){

        // How much time has passed since the last time update was called
        timeTemp += timePassedSinceLastUpdateUpdate;

        charactersInPlay = false;
        // Each character that is in play, update its position
        gameCharacters.forEach((gameCharacter)->{
            if (gameCharacter.isInPlay()) {

                gameCharacter.setCharacterY(
                        gameCharacter.getCharacterY() - gameCharacter.getSpeed());
                charactersInPlay = true;
            }
        });

        // If character list is not empty,
        // If 5 seconds has passed,
        // then set one !inPlay character to inPlay.
        // If no characters are left to set inPlay, characterListEmpty=true
        if(!characterListEmpty) {
            if (timeTemp > generateNewCharacterTimeThreshold) {
                System.out.println(nextIndexToPutInPlay);
                gameCharacters.get(nextIndexToPutInPlay).setInPlay(true);
                if (nextIndexToPutInPlay >= gameCharacters.size() - 1) {
                    characterListEmpty = true;
                } else {
                    nextIndexToPutInPlay++;
                }
                timeTemp = 0;
            }
        }
    }
}
