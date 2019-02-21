package com.potatospy.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.potatospy.KanaBucket;
import com.potatospy.entities.Bucket;
import com.potatospy.managers.KanaManager;

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
    protected final KanaManager kanaManager;
    protected final Bucket bucket;
    OrthographicCamera camera;

    // Entities
    List<Character> characters; // Characters currently on screen

    // Textures
    Texture bucketImage;

    public static Sound drop1;
    public static Sound drop2;
    public static Sound catch1;
    public static Sound catch2;
    public static Sound missed;


    BitmapFont characterFont; // Font used for the Cat name Tags!
    BitmapFont scoreFont = new BitmapFont(Gdx.files.internal("arial.fnt"), false);


    public GameScreen(final KanaBucket app) {
        this.app = app;
        kanaManager = new KanaManager();
        bucket = new Bucket(0f, 0f);

        // Initialize camera
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, KanaBucket.APP_WIDTH, KanaBucket.APP_HEIGHT);

        // Initialize fonts and character list
        characters = new ArrayList<>();
        characterFont = new BitmapFont(Gdx.files.internal("arial.fnt"), false);
        scoreFont = new BitmapFont(Gdx.files.internal("arial.fnt"), false);

        // Initialize sounds
        drop1 = Gdx.audio.newSound(Gdx.files.internal("drop1.wav"));
        drop2 = Gdx.audio.newSound(Gdx.files.internal("drop2.wav"));
        catch1 = Gdx.audio.newSound(Gdx.files.internal("catch1.wav"));
        catch2 = Gdx.audio.newSound(Gdx.files.internal("catch2.wav"));
        missed = Gdx.audio.newSound(Gdx.files.internal("missed.wav"));


        // Init images
        bucketImage = new Texture("bucket.png");
    }

    // On screen load...
    @Override
    public void show() {

        // Set volume for audio


        // Font and Text init
        characterFont.setColor(Color.WHITE);
        characterFont.getData().setScale(0.6f);
        scoreFont.setColor(Color.WHITE);
        scoreFont.getData().setScale(0.5f);
    }

    public void update(float delta) {

        // Tell each Kana to update
        //kanaManager.update();

        bucket.setBucketX((Gdx.input.getX()));
        System.out.println("X:"+ Gdx.input.getX() + " Y:"+ Gdx.input.getY());



    }

    @Override
    public void render(float delta) {

        update(delta); // Call update() every frame to update logic before rendering

        Gdx.gl.glClearColor(1f, 1, 1f, 0);  // Black background
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);   // I forget Todo: unforget

        app.batch.begin();  // Start rendering

        // Render the bucket
        app.batch.draw(bucketImage, bucket.getBucketX(), bucket.getBucketY());

        // Render the Kana



        app.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        scoreFont.dispose();
        characterFont.dispose();

        bucketImage.dispose();

        drop1.dispose();
        drop2.dispose();
        missed.dispose();
        catch1.dispose();
        catch2.dispose();
    }
}















































