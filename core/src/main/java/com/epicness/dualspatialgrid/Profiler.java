package com.epicness.dualspatialgrid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.profiling.GLProfiler;
import com.epicness.dualspatialgrid.utils.ProfilerInfo;

@SuppressWarnings("GDXJavaProfilingCode")
public class Profiler {

    private final GLProfiler profiler;
    private final ProfilerInfo profilerInfo;

    public Profiler() {
        profiler = new GLProfiler(Gdx.graphics);
        profiler.enable();
        BitmapFont font = new BitmapFont();
        font.setColor(Color.BLACK);
        profilerInfo = new ProfilerInfo(font);
    }

    public void update(float delta) {
        profilerInfo.drawCalls = profiler.getDrawCalls();
        profilerInfo.bindings = profiler.getTextureBindings();
        profiler.reset();
    }

    public void render(Batch batch) {
        profilerInfo.draw(batch);
    }
}