package com.potatospy.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.potatospy.KanaBucket;

import java.util.ArrayList;
import java.util.List;

/*
Characters are generated at the top of the screen and fall down
A Bucket attached to the user's mouse can collide with the characters and
that character is destroyed and points are added to the score.

If a certain number of characters are destroyed at the bottom of the screen,
(you missed them), you lose the game. Todo: Implement 3 Rounds
 */


public class GameScreen implements Screen {


    protected final KanaBucket app;
    OrthographicCamera camera;

    // Entities
    List<Character> characters; // Characters currently on screen

    // Textures
    Texture bucketImage;

    public static Sound dropCharacter = Gdx.audio.newSound(Gdx.files.internal("catmeow.wav"));
    public static Sound catchCharacter = Gdx.audio.newSound(Gdx.files.internal("catmeow.wav"));
    public static Sound missedCharacter = Gdx.audio.newSound(Gdx.files.internal("catmeow.wav"));


    BitmapFont characterFont; // Font used for the Cat name Tags!
    BitmapFont scoreFont = new BitmapFont(Gdx.files.internal("arial.fnt"), false);


    public GameScreen(final KanaBucket app){
        this.app=app;


        // Initialize camera
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, KanaBucket.APP_WIDTH, KanaBucket.APP_HEIGHT);

        // Initialize fonts and character list
        characters = new ArrayList<>();
        characterFont = new BitmapFont(Gdx.files.internal("arial.fnt"), false);
        scoreFont = new BitmapFont(Gdx.files.internal("arial.fnt"), false);

        // Initialize sounds

        // Init images
        bucketImage = new Texture("bucket.png");
    }


}
