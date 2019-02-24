package com.potatospy.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class Bucket {


    private static Texture bucketTexture = new Texture(Gdx.files.internal("bucket.png"));
    private Sprite bucketSprite;
    private float bucketX;
    private float bucketY;
    private int difficulty;   // Determines size of the bucket

    public Bucket(float bucketX, float bucketY, int difficulty) {

        this.bucketSprite=new Sprite(bucketTexture);
        this.bucketX = bucketX;
        this.bucketY = bucketY;
        this.difficulty = difficulty;
        bucketSprite.scale(125/difficulty);
    }

    public float getBucketX() {
        return bucketX;
    }

    public void setBucketX(float bucketX) {
        this.bucketX = bucketX;
    }

    public float getBucketY() {
        return bucketY;
    }

    public void setBucketY(float bucketY) {
        this.bucketY = bucketY;
    }

    public static Texture getBucketTexture() {
        return bucketTexture;
    }

    public static void setBucketTexture(Texture bucketTexture) {
        Bucket.bucketTexture = bucketTexture;
    }

    public Sprite getBucketSprite() {
        return bucketSprite;
    }

    public void setBucketSprite(Sprite bucketSprite) {
        this.bucketSprite = bucketSprite;
    }

    public float getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
