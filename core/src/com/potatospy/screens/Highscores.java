package com.potatospy.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.potatospy.KanaBucket;
import com.potatospy.entities.Bucket;
import com.potatospy.managers.CharacterManager;
import com.potatospy.managers.CollisionManager;
import com.potatospy.managers.GameScreenManager;
import com.potatospy.managers.ScoreManager;

import java.util.Map;

public class Highscores implements Screen {


    protected final KanaBucket app;
    protected final GameScreenManager gameScreenManager;
    private ScoreManager scoreManager = ScoreManager.getInstance();
    OrthographicCamera camera;

    // Fonts
    BitmapFont scoreFont = new BitmapFont(Gdx.files.internal("arial.fnt"), false);


    // == Constructor ==

    public Highscores(final KanaBucket app, final GameScreenManager gameScreenManager) {

        // Init
        this.app = app;
        this.gameScreenManager = gameScreenManager;
    }


    @Override
    public void show() {
        // Font and Text init
        scoreFont.setColor(Color.CYAN);
        scoreFont.getData().setScale(2f);

        // Hide the mouse cursor
        Gdx.input.setCursorCatched(false);
    }



    // Clear screen, Draw scores and exit on Esc key
    @Override
    public void render(float delta) {


        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);  // Set Black background for glClear
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);   //  Clear the frame buffer

        app.batch.begin();  // Start rendering



        ///////////////////////////////
        // Render Text Info
        //

        // For PLAYER: score (number of characters "caught"/intersected)

        for(String name :scoreManager.getHighscoreMap().keySet()) { // Todo This aint gonna work man!
            // Todo where are you getting the saved keySet from? Nowhere


            scoreFont.draw(app.batch, ("You did good " + name), /*Todo get screen width and height from app*/400, 500);
            scoreFont.draw(app.batch, ("Your score: " + scoreManager.getHighscoreMap().get(name)), 400, 400);

        }



        // Go to Main menu button


        app.batch.end();



        // Quit with Escape button
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }
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

    }
}
