package com.epicness.dualspatialgrid.verlet.logic;

import com.badlogic.gdx.math.MathUtils;
import com.epicness.dualspatialgrid.verlet.Soldier;

import java.util.List;

public class SoldierSpawner {

    private final List<Soldier> soldiers;
    private static final float SPAWN_VARIANCE = 50f;

    public SoldierSpawner(List<Soldier> soldiers) {
        this.soldiers = soldiers;
    }

    public void spawnSoldiers(float x, float y) {
        for (int i = 0; i < 100; i++) {
            spawnSoldier(
                x + MathUtils.random(-SPAWN_VARIANCE, SPAWN_VARIANCE),
                y + MathUtils.random(-SPAWN_VARIANCE, SPAWN_VARIANCE),
                MathUtils.random(5f, 5f));
        }
        System.out.println(soldiers.size());
    }

    public void spawnSoldier(float x, float y) {
        spawnSoldier(x, y, MathUtils.random(5f, 5f));
    }

    private void spawnSoldier(float x, float y, float radius) {
        Soldier soldier = new Soldier(x, y, radius);
        soldiers.add(soldier);
    }
}