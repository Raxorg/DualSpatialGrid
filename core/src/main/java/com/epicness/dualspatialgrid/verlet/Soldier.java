package com.epicness.dualspatialgrid.verlet;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.epicness.dualspatialgrid.dsg.DSGCircle;
import com.epicness.dualspatialgrid.dsg.HasDSGObject;

public class Soldier implements HasDSGObject, HasVerletCircle {

    public final VerletCircle verletCircle;
    public final Color color;
    public final DSGCircle dsgCircle;

    public Soldier(float x, float y, float radius) {
        verletCircle = new VerletCircle(x, y, radius);
        color = new Color().fromHsv(MathUtils.random(360f), 1f, 1f);
        dsgCircle = new DSGCircle(radius);
        dsgCircle.setPositionCentered(x, y);
    }

    @Override
    public DSGCircle getDSGObject() {
        return dsgCircle;
    }

    @Override
    public VerletCircle getVerletCircle() {
        return verletCircle;
    }
}