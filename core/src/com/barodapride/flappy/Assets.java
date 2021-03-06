package com.barodapride.flappy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Mike on 3/20/2015.
 */
public class Assets {

    // Disposeables
    public static TextureAtlas atlas;
    public static SpriteBatch batch;


    // Non-Disposeables
    public static TextureRegion bird;
    public static TextureRegion background;
    public static TextureRegion ground;
    public static TextureRegion pipe;

    public static void load() {

        atlas = new TextureAtlas("pack.atlas");
        batch = new SpriteBatch();

        bird = atlas.findRegion("bird-16x16");
        background = atlas.findRegion("background-300x480");
        ground = atlas.findRegion("ground-300x24");
        pipe = atlas.findRegion("pipe-64x400");

    }

    // Dispose all disposeables here
    public static void dispose(){
        if (atlas != null) {
            atlas.dispose();
        }
        if (batch != null) {
            batch.dispose();
        }
    }

}
