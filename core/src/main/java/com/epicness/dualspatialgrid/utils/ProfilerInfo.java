package com.epicness.dualspatialgrid.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class ProfilerInfo {

    private final BitmapFont font;
    public float x, y;
    public int drawCalls, bindings;

    public ProfilerInfo(BitmapFont font) {
        this.font = font;
        x = 8;
        y = 55;
    }

    public void draw(Batch spriteBatch) {
        font.draw(spriteBatch,
            "FPS: " + Gdx.graphics.getFramesPerSecond() + "\n" +
                "Draw calls: " + drawCalls + "\n" +
                "Texture bindings: " + bindings,
            x, y);
    }
}