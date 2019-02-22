package com.potatospy.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteCharacter extends DroppableCharacter{

    private Sprite characterSprite;

    public SpriteCharacter(int difficulty) {
        super(difficulty);
        this.characterSprite = new Sprite();
    }

}
