package com.potatospy.managers;

import com.potatospy.entities.DroppableCharacter;
import com.potatospy.entities.SpriteCharacter;

import java.util.List;

/*
For each game, some number of DroppableCharacters are added to the characterPool
(dependent on difficulty).
As timePassed increases, DroppableCharacters are removed from characterPool,
they are converted into SpriteCharacters and added to spriteCharacters.
The SpriteCharacters are now available to GameScreen to render and update.
The characters each have:
A speed
An X position (which currently will remain static)
An isKana that will determine whether points are added and subtracted for catching it
in the bucket.

If a SpriteCharacter reaches the bottom or is caught then it is removed from the list.
GameScreen takes care of the score.

When spriteCharacters is empty, it is the end of the round.
 */




public class CharacterManager {


    // == Fields ==
    List<DroppableCharacter> characterPool;  // List of characters before they enter play
    List<SpriteCharacter> spriteCharacters; // List of characters in play
    private int difficulty; // Passed to DroppableCharacter to determine speed and range of
                            // characters used.


    // == Constructor ==
    public CharacterManager(int difficulty) {
        this.characterPool = characterPool;
        this.difficulty = difficulty;
    }


    // == Get Set ==
    public List<DroppableCharacter> getCharacterPool() { return characterPool; }
    public void setCharacterPool(List<DroppableCharacter> characterPool) { this.characterPool = characterPool; }

    public int getDifficulty() { return difficulty; }
    public void setDifficulty(int difficulty) { this.difficulty = difficulty; }


    // == Manager methods ==

    public void generateKana(){

        // Create a list filled with kana appropriate for the difficulty (Higher difficulty, wider range of characters)
        characterPool.add(new DroppableCharacter(difficulty));

    }

    public void update(float timePassed){

        characterPool.forEach((droppableCharacter) -> {
            droppableCharacter.setCharacterY(timePassed*10f);
        });
    }


    public boolean spriteCharactersIsEmpty(){
        if(spriteCharacters.isEmpty()){
            return true;
        }

        return false;
    }
}
