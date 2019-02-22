package com.potatospy.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.potatospy.KanaBucket;
import com.potatospy.entities.Bucket;
import com.potatospy.managers.CharacterManager;

/*
Characters are generated at the top of the screen and fall down
A Bucket attached to the user's mouse can collide with the characters and
that character is destroyed and points are added to the score.

If a certain number of characters are destroyed at the bottom of the screen,
(you missed them), you lose the game. Todo: Implement 3 Rounds
 */


public class GameScreen implements Screen {


    protected final KanaBucket app;
    protected final CharacterManager characterManager;
    protected final Bucket bucket;
    OrthographicCamera camera;


    public static Sound drop1;
    public static Sound drop2;
    public static Sound catch1;
    public static Sound catch2;
    public static Sound missed;

    // Fonts
    BitmapFont characterFont; // Font used for the Cat name Tags!
    BitmapFont scoreFont = new BitmapFont(Gdx.files.internal("arial.fnt"), false);

    // Game logic fields
    private int timePassed;   // Every game starts at 0ms Todo ms?
    private int difficulty;   // Default difficulty is 1

    public GameScreen(final KanaBucket app) {

        // Init game logic
        timePassed = 0;
        difficulty = 1;

        this.app = app;
        characterManager = new CharacterManager(difficulty);
        bucket = new Bucket(0f, 0f, 1);


        // Initialize camera
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, KanaBucket.APP_WIDTH, KanaBucket.APP_HEIGHT);

        // Initialize fonts
        characterFont = new BitmapFont(Gdx.files.internal("arial.fnt"), false);
        scoreFont = new BitmapFont(Gdx.files.internal("arial.fnt"), false);

        // Initialize sounds
        drop1 = Gdx.audio.newSound(Gdx.files.internal("drop1.wav"));
        drop2 = Gdx.audio.newSound(Gdx.files.internal("drop2.wav"));
        catch1 = Gdx.audio.newSound(Gdx.files.internal("catch1.wav"));
        catch2 = Gdx.audio.newSound(Gdx.files.internal("catch2.wav"));
        missed = Gdx.audio.newSound(Gdx.files.internal("missed.wav"));



    }

    // On screen load...
    @Override
    public void show() {

        // Get size of bucket, Differs depending on difficulty
        bucket.setDifficulty(difficulty);
        characterManager.setDifficulty(difficulty);

        // Set volumes for audio


        // Font and Text init
        characterFont.setColor(Color.WHITE);
        characterFont.getData().setScale(0.6f);
        scoreFont.setColor(Color.CYAN);
        scoreFont.getData().setScale(0.5f);

        // Hide the mouse cursor
        Gdx.input.setCursorCatched(true);

        BitmapFont info = new BitmapFont(Gdx.files.internal("arial.fnt"), false);

    }

    public void update(float delta) {

        // Every delta=500, Add a new ball to the balls List
        timePassed += delta;

        // Tell each DroppableCharacter to update
        characterManager.update(timePassed);

        bucket.setBucketX((Gdx.input.getX()));
        System.out.println("X:"+ Gdx.input.getX() + " Y:"+ Gdx.input.getY());


        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }
    }

    @Override
    public void render(float delta) {

        update(delta); // Call update() every frame to update logic before rendering

        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);  // Black background
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);   // I forget Todo: unforget

        app.batch.begin();  // Start rendering


        ///////////////////////////////
        // Render Text
        //

        // For PLAYER: number of balls on screen Todo Use game texts, dont harcode
        scoreFont.draw(app.batch,("Current DroppableCharacter: " + String.valueOf("0")), 0, 550);
        // For PLAYER: score (number of balls "caught"/intersected)
        scoreFont.draw(app.batch, ("Score: " + "0"), 0, 600);
        // For PLAYER: balls not caught. If a player misses 10 balls, it's game over!
        scoreFont.draw(app.batch, ("DroppableCharacter not caught: " + String.valueOf("0")), 0,650);


        ///////////////////////////////
        // Render the bucket
        //
        app.batch.draw(bucket.getBucketSprite(), bucket.getBucketX(), bucket.getBucketY(), bucket.getBucketSprite().getScaleX(),bucket.getBucketSprite().getScaleY());

        ///////////////////////////////
        // Render the DroppableCharacter
        //



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


        drop1.dispose();
        drop2.dispose();
        missed.dispose();
        catch1.dispose();
        catch2.dispose();
    }
}















































