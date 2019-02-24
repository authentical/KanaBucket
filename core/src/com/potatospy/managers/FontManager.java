package com.potatospy.managers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.potatospy.util.CharacterLoader;


// A fair amount goes into preparing the unicode japanese font for LibGDX so
// this class helps keep it clean
public class FontManager {

    private static CharacterLoader characterLoader = CharacterLoader.getInstance();
    public static BitmapFont characterFont;     // This font is returned for use by CharacterManager


    public static BitmapFont prepareFont(){

        // Get the font from ttf file and configure
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("sazanami-gothic.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 128;    // Import font at this font size

        // Load letters to be used in game  Todo what if this fails

        parameter.characters = characterLoader.getLetterString();

        System.out.println(parameter.characters);

        // Finally give the prepared font to characterFont and dispose
        characterFont = generator.generateFont(parameter);
        generator.dispose();


        return characterFont;
    }
}
