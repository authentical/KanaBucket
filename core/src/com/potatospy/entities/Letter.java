package com.potatospy.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.potatospy.KanaBucket;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class Letter {

    // == Fields ==
    private int difficulty;     /* Used in determining the range of characters to choose from when
                                   this class generates it's character */
    private String character;   // UTF-16 character
    private boolean isKana;     // Points will be awarded for catching Kana and deducted for not Kana.
    private boolean isInPlay;     // Determines whether or not the letter is currently being used
    private boolean caught;     // If a letter is caught, then true
    private boolean missed;     // If a letter is missed, then true

    private Sprite characterSprite;
    private BitmapFont characterFont;
    private float speed;        // How fast the character falls
    private final float characterX;   // X position never changes
    private float characterY;   // Character will fall and Y distance is subtracted as time passes
    Random rand;

    // == Constructors ==

    public Letter(int difficulty) {

        this.difficulty = difficulty;
        this.character = newCharacter(difficulty);

        this.characterSprite = new Sprite();
        this.rand = new Random();

        this.characterX = newX();   // Randomly select X position
        this.characterY = KanaBucket.APP_HEIGHT;     // Character is dropped from the top of the screen

        characterSprite.setOrigin(50f/2,50f/2);
        characterSprite.setSize(50f, 50f);

        characterFont = new BitmapFont( // Todo select different font
                Gdx.files.internal("UDDigi.fnt"),
                false);
        characterFont.setColor(1.0f, 1.0f, 1.0f, 1.0f); // Todo randomize
    }


    // GET SET
    public String getCharacter() { return character; }
    public void setCharacter(String character) { this.character = character; }

    public int getDifficulty() { return difficulty; }
    public void setDifficulty(int difficulty) { this.difficulty = difficulty; }

    public boolean isKana() { return isKana; }
    public void setKana(boolean kana) { isKana = kana; }

    public Sprite getCharacterSprite() { return characterSprite; }
    public void setCharacterSprite(Sprite characterSprite) { this.characterSprite = characterSprite; }

    public float getSpeed() { return speed; }
    public void setSpeed(float speed) { this.speed = speed; }

    public float getCharacterX() { return characterX; }

    public float getCharacterY() { return characterY; }
    public void setCharacterY(float characterY) { this.characterY = characterY; }

    public BitmapFont getCharacterFont() { return characterFont; }
    public void setCharacterFont(BitmapFont characterFont) { this.characterFont = characterFont; }

    public boolean isInPlay() { return isInPlay; }
    public void setInPlay(boolean inPlay) { isInPlay = inPlay; }

    public boolean isCaught() { return caught; }
    public void setCaught(boolean caught) { this.caught = caught; }

    public boolean isMissed() { return missed; }
    public void setMissed(boolean missed) { this.missed = missed; }



    // == private methods ==

    private float newX(){
        return 1100f - (float)rand.nextInt(1000);
    }


    private String newCharacter(int difficulty){

        // Get character
        return "a";
    }


}
