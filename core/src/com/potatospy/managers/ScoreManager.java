package com.potatospy.managers;

import com.sun.javafx.collections.UnmodifiableObservableMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ScoreManager {

    // == Fields ==
   public static ScoreManager scoreManager_instance = null;
   private Map<String, Integer> highscoreMap;

    // == Constructor ==

    private ScoreManager() {
        this.highscoreMap = new HashMap<>();

    }
    public static ScoreManager getInstance(){
        if(scoreManager_instance ==null){
            scoreManager_instance = new ScoreManager();
        }

        return scoreManager_instance;
    }


    // == Public methods ==
    public void addScore(String name, Integer score){

        highscoreMap.put(name, score);
    }
    public Map<String, Integer> getHighscoreMap() {
        return Collections.unmodifiableMap(highscoreMap);
    }
}
