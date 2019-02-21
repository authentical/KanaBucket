package com.potatospy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;




public class KanaBucket extends Game {

    public final static String APP_NAME = "KanaBucket";
    public final static double APP_VERSION = 0.01;

    /* Note about APP_WIDTH vs V_WIDTH etc.
        Although people are recommending defining two sets of width/height constants,
        I so far have no seen one implementation that requires an "APP" size to
        have different values than that of a "Virtual"    */
    public final static int APP_WIDTH = 1100;
    public final static int APP_HEIGHT = 700;


    // Managers
    public GameScreenManager gameScreenManager;

    // Batches
    public SpriteBatch batch;

	
	@Override
	public void create () {
        // Setup Batches
        batch = new SpriteBatch();

        // Setup Managers
        gameScreenManager = new GameScreenManager(this);
	}

	@Override           // Dont want to render anything here
	public void render () {
//		Gdx.gl.glClearColor(1, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//		batch.begin();
//
//		batch.end();
        super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
