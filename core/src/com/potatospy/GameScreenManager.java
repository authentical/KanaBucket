package com.potatospy;

import com.badlogic.gdx.Screen;
import com.potatospy.screens.GameScreen;

import java.util.HashMap;

public class GameScreenManager {



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
        this.gameScreenMap.put(STATE.PLAY, new GameScreen(app));
        //this.gameScreenMap.put(STATE.SETTINGS, new Settings(app));
        //this.gameScreenMap.put(STATE.HIGHSCORE, new Highscore(app));
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
