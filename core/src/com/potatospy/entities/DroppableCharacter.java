package com.potatospy.entities;

import com.potatospy.KanaBucket;

public class DroppableCharacter {

    // == Fields ==
    private int difficulty;     /* Used in determining the range of characters to choose from when
                                   this class generates it's character */
    private String character;   // UTF-16 character
    private final float characterX;   // X position never changes
    private float characterY;   // Character will fall and Y distance is subtracted as time passes
    private float speed;        // How fast the character falls
    private boolean isKana;     // Points will be awarded for catching Kana and deducted for not Kana.


    // == Constructor ==
    public DroppableCharacter(int difficulty) {

        this.difficulty = difficulty;
        this.character = newCharacter(difficulty);
        this.characterX = newX();   // Randomly select X position
        this.characterY = KanaBucket.APP_HEIGHT;    // Character is dropped from the top of the screen
    }


    // GET SET
    public String getCharacter() { return character; }
    public void setCharacter(String character) { this.character = character; }

    public int getDifficulty() { return difficulty; }
    public void setDifficulty(int difficulty) { this.difficulty = difficulty; }

    public float getCharacterX() { return characterX; }

    public float getCharacterY() { return characterY; }
    public void setCharacterY(float characterY) { this.characterY = characterY; }

    public boolean isKana() { return isKana; }
    public void setKana(boolean kana) { isKana = kana; }

    public float getSpeed() { return speed; }
    public void setSpeed(float speed) { this.speed = speed; }


    // == private methods ==
    private String newCharacter(int difficulty){
        return "\\u308E";
    }

    private float newX(){
        return 400f;
    }
}
