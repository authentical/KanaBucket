package com.potatospy.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.potatospy.KanaBucket;
import com.potatospy.entities.Bucket;
import com.potatospy.managers.CharacterManager;
import com.potatospy.managers.CollisionManager;
import com.potatospy.managers.GameScreenManager;
import com.potatospy.managers.ScoreManager;
import com.potatospy.util.MyTextInputListener;

import java.util.Random;

/*
Characters are generated at the top of the screen and fall down
A Bucket attached to the user's mouse can collide with the characters and
that character is destroyed and points are added to the score.

If a certain number of characters are destroyed at the bottom of the screen,
(you missed them), you lose the game. Todo: Implement 3 Rounds
 */


public class GameScreen implements Screen {


    protected final KanaBucket app;
    protected final GameScreenManager gameScreenManager;
    protected final CharacterManager characterManager;
    protected final Bucket bucket;
    OrthographicCamera camera;


    public static Sound drop1;
    public static Sound drop2;
    public static Sound catch1;
    public static Sound catch2;
    public static Sound missed;

    // Fonts
    BitmapFont scoreFont = new BitmapFont(Gdx.files.internal("arial.fnt"), false);

    // Game logic fields
    private int difficulty;   // Default difficulty is 1
    private int score;
    private int charactersMissed;
    private int cursorX;
    private MyTextInputListener listener;
    private ScoreManager scoreManager;
    private Random rand = new Random();


    // == Constructor ==
    public GameScreen(final KanaBucket app, final GameScreenManager gameScreenManager) {

        // Init game logic
        difficulty = 2;
        score =0;
        cursorX=app.APP_WIDTH/2;
        scoreManager = ScoreManager.getInstance();
        listener = new MyTextInputListener();

        this.app = app;
        this.gameScreenManager = gameScreenManager;
        bucket = new Bucket(0f, 0f, 1);
        characterManager = new CharacterManager(difficulty);



        // Initialize camera
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, KanaBucket.APP_WIDTH, KanaBucket.APP_HEIGHT);

        // Initialize fonts
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

        // Initialize bucket and characterManager
        bucket.setDifficulty(difficulty);
        characterManager.setDifficulty(difficulty);
        characterManager.generateListOfCharacters();

        // Set volumes for audio


        // Font and Text init
        scoreFont.setColor(Color.CYAN);
        scoreFont.getData().setScale(0.5f);

        // Hide the mouse cursor
        Gdx.input.setCursorCatched(true);

        //BitmapFont info = new BitmapFont(Gdx.files.internal("arial.fnt"), false);

    }

    public void update(float delta) {


        //System.out.println(delta);
        // Tell each SpriteCharacter to update via characterManager
        characterManager.update(delta);


        // Attach bucket to mouse X
        cursorX = Gdx.input.getX();
        if(cursorX < 0){cursorX=0;}
        if(cursorX > app.APP_WIDTH-75){ cursorX=app.APP_WIDTH-115;}
        bucket.setBucketX(cursorX);


        // Is character list empty and all characters no longer in play? If so, go to high scores.
        if(characterManager.isCharacterListEmpty()==true){

            listener.score = score;
            Gdx.input.getTextInput(listener, "Enter your name", "", "");

            gameScreenManager.setScreen(GameScreenManager.STATE.HIGHSCORE);
        }

        // Quit with Escape button
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
        // Render Text Info
        //

        // For PLAYER: score (number of characters "caught"/intersected)
        scoreFont.draw(app.batch, ("Score: " + score), 0, 600);
        // For PLAYER: characters not caught. If a player misses 10 characters, it's game over!
        scoreFont.draw(app.batch, ("Characters missed: " + charactersMissed), 0,650);


        ///////////////////////////////
        // Render the bucket
        //
        app.batch.draw(bucket.getBucketSprite(),
                bucket.getBucketX(), bucket.getBucketY(),
                bucket.getBucketSprite().getScaleX(),bucket.getBucketSprite().getScaleY());


        ///////////////////////////////
        // Render the GameCharacters
        //
        // Some of this logic belongs in update() but I couldn't justify looping twice,
        // Once to render, another time to update catch and miss.
        characterManager.getGameCharacters().forEach((gameCharacter) -> {
            // Only draw the character if its in play
            if(gameCharacter.isInPlay()) {
                characterManager.getCharacterFont().draw(app.batch, gameCharacter.getCharacter(),
                        gameCharacter.getCharacterX(), gameCharacter.getCharacterY());

                if(gameCharacter.getCharacterY()==750){
                    if(rand.nextInt(2)==1){
                        drop2.play();
                    } else{
                        drop1.play();
                    }
                }
                                    // Todo If the characters are dropping fast enough, they aren't being caught
                if((gameCharacter.getCharacterY()<158&&gameCharacter.getCharacterY()>110)&&CollisionManager.isCollision(bucket, gameCharacter, difficulty)){

                    gameCharacter.setInPlay(false);
                    gameCharacter.setCaught(true);
                    score++;
                    if(rand.nextInt(2)==1){
                        catch1.play();
                    } else{
                        catch2.play();
                    }

                } else if(gameCharacter.getCharacterY()<0){
                    gameCharacter.setInPlay(false);
                    gameCharacter.setMissed(true);
                    charactersMissed++;
                    missed.play();
                }

            }
        });


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


        drop1.dispose();
        drop2.dispose();
        missed.dispose();
        catch1.dispose();
        catch2.dispose();
    }
}















































