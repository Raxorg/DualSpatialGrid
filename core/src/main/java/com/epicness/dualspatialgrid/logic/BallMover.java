package com.epicness.dualspatialgrid.logic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.epicness.dualspatialgrid.Ball;
import com.epicness.dualspatialgrid.dsg.DSGObject;

public class BallMover {

    private final Array<Ball> balls;
    public final Vector2 target, dir;

    public BallMover(Array<Ball> balls) {
        this.balls = balls;
        target = new Vector2();
        dir = new Vector2();
    }

    public void moveBalls(float delta) {
        for (int i = 0; i < balls.size; i++) {
            moveBall(balls.get(i).getDSGObject(), delta);
        }
    }

    private void moveBall(DSGObject dsgObject, float delta) {
        dir.set(target).sub(dsgObject.getCenter());
        if (dir.len() < 5f) {
            dsgObject.setPositionCentered(target.x, target.y);
            return;
        }
        dir.nor().scl(100f * delta);
        dsgObject.translate(dir);
    }
}