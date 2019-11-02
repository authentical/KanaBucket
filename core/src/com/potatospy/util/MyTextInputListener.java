package com.potatospy.util;

import com.badlogic.gdx.Input;
import com.potatospy.managers.ScoreManager;

public class MyTextInputListener implements Input.TextInputListener {

    private ScoreManager scoreManager = ScoreManager.getInstance();
    public int score;


    @Override
    public void input(String text) {

        scoreManager.addScore(text, score);
    }

    @Override
    public void canceled() {

    }

}
