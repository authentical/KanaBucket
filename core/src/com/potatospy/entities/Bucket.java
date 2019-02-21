package com.potatospy.entities;

public class Bucket {

    private float bucketX;
    private float bucketY;

    public Bucket(float bucketX, float bucketY) {
        this.bucketX = bucketX;
        this.bucketY = bucketY;
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
}
