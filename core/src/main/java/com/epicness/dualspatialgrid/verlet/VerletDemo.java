package com.epicness.dualspatialgrid.verlet;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.epicness.dualspatialgrid.verlet.logic.Logic;

public class VerletDemo extends Game {

    private Logic logic;
    private Renderer renderer;

    @Override
    public void create() {
        Stuff stuff = new Stuff();
        logic = new Logic(stuff);
        new Input(logic);
        renderer = new Renderer(stuff);
    }

    @Override
    public void render() {
        logic.update(Gdx.graphics.getDeltaTime());
        renderer.render();
    }
}