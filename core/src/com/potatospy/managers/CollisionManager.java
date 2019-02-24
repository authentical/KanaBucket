package com.potatospy.managers;

import com.potatospy.entities.Bucket;
import com.potatospy.entities.GameCharacter;

public class CollisionManager {


    public static boolean isCollision(Bucket bucket, GameCharacter gameCharacter, int difficulty){

        float bucketX = bucket.getBucketX();
        float letterX = gameCharacter.getCharacterX();


        if(bucketX > letterX){
            if(bucketX - letterX < 70/difficulty){
                return true;
            }
        } else if(bucketX < letterX){
            if(letterX - bucketX < 70/difficulty){
                return true;
            }
        } else{
            return true;
        }

        return false;
    }
}
