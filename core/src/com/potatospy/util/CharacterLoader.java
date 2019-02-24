package com.potatospy.util;

import java.io.*;
import java.util.List;


// This file gets the characters out of the prepared characterList.data
public class CharacterLoader {

    private static CharacterLoader characterLoaderInstance = null;

    private List<String> letterList = null; // For loading from file. File is stored as a serialized List<String>
    private StringBuilder letterStringBuilder = new StringBuilder();// For generating a concatenated string of
                                                                    // characters for FontLoader
    private String letterString;

    // == Init ==
    public CharacterLoader(){

        this.letterList = getCharactersFromFile();
        this.letterString = convertLetterListToStringBuilder();
    }
    public static CharacterLoader getInstance()
    {
        if (characterLoaderInstance == null)
            characterLoaderInstance = new CharacterLoader();

        return characterLoaderInstance;
    }


    // Get Characters from file and return concatenated string of characters
    private List<String> getCharactersFromFile(){

        FileInputStream in;             // Todo I forget if these streams close themselves...!!o
        ObjectInputStream objectIn;
        try {
            in = new FileInputStream("characterList.dat");
            objectIn = new ObjectInputStream(in);
            letterList = (List<String>) (objectIn.readObject());
        }catch (FileNotFoundException e) { System.out.println("File not found: " + e);
        }catch(IOException e){ System.out.println("Problem serializing: " + e);
        }catch (ClassNotFoundException e){ System.out.println("Class not found: " + e); }

        System.out.println("CHARACTERS LOADED SUCCESSFULLY to List<String> letterList\n"+letterList+"\n\n\n");
        return letterList;
    }


    // == Conversion Utility methods ==    // Todo need error handling
    private String convertLetterListToStringBuilder(){
        letterList.forEach((letter)->{letterStringBuilder.append(letter);});

        return letterStringBuilder.toString();
    }


    // == Getters ==

    // return concatenated string of letters
    public String getLetterString(){
        return letterStringBuilder.toString();  // Todo throw error if this is empty
    }


    // Todo What if its empty?
    public List<String> getLetterList(){

        return letterList;
    }


}
