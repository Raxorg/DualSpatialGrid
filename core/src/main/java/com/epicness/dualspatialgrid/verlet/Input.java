package com.epicness.dualspatialgrid.verlet;

import static com.badlogic.gdx.Input.Buttons.LEFT;
import static com.badlogic.gdx.Input.Keys.D;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.epicness.dualspatialgrid.verlet.logic.Logic;

public class Input extends InputAdapter {

    private final Logic logic;

    public Input(Logic logic) {
        this.logic = logic;
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenY = Gdx.graphics.getHeight() - screenY;
        if (button == LEFT) {
            logic.soldierSpawner.spawnSoldier(screenX, screenY);
        } else {
            logic.soldierSpawner.spawnSoldiers(screenX, screenY);
        }
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode != D) return false;
        System.out.println(Gdx.graphics.getFramesPerSecond());
        return false;
    }
}