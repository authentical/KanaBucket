package com.potatospy.managers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.potatospy.KanaBucket;
import com.potatospy.screens.GameScreen;
import com.potatospy.screens.Highscores;
import com.potatospy.util.CharacterLoader;

import java.util.HashMap;

public class GameScreenManager {

    private static GameScreenManager gameScreenManager = null;

    public final KanaBucket app;    // For use with Screens. This is passed around so all screens can access the shared SpriteBatch
    private HashMap<STATE, Screen> gameScreenMap;
    public enum STATE { PLAY, MAIN_MENU, SETTINGS, HIGHSCORE }


    //== CONSTRUCTOR ==

    public GameScreenManager(final KanaBucket app){

        this.app = app;

        initGameScreens();
        setScreen(STATE.PLAY);
    }


    // == INIT methods ==

    private void initGameScreens() {

        this.gameScreenMap = new HashMap<STATE, Screen>();
        //this.gameScreenMap.put(STATE.MAIN_MENU, new MainMenu(app));
        this.gameScreenMap.put(STATE.PLAY, new GameScreen(app, this));
        //this.gameScreenMap.put(STATE.SETTINGS, new Settings(app));
        this.gameScreenMap.put(STATE.HIGHSCORE, new Highscores(app, this));
    }


    // == PUBLIC methods ==

    public void setScreen(STATE state){
        app.setScreen(gameScreenMap.get(state));
    }


    // CLEANUP

    public void dispose(){
        for(Screen screen : gameScreenMap.values()){
            if(screen !=null){
                screen.dispose();
            }
        }
    }
}
