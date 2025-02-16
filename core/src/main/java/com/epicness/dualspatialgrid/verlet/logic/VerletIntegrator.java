package com.epicness.dualspatialgrid.verlet.logic;


import static com.epicness.dualspatialgrid.Constants.WINDOW_HEIGHT;
import static com.epicness.dualspatialgrid.Constants.WINDOW_WIDTH;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.epicness.dualspatialgrid.verlet.VerletCircle;

public class VerletIntegrator {

    @SuppressWarnings("FieldCanBeLocal")
    private Vector2 currentPos, oldPos, acceleration;
    private float radius;
    private final Vector2 velocity, gravity, dir;
    private final Circle boundsCircle;
    @SuppressWarnings("FieldCanBeLocal")
    private float dist;

    public VerletIntegrator(Circle boundsCircle) {
        velocity = new Vector2();
        gravity = new Vector2(0f, -1000f);
        dir = new Vector2();
        this.boundsCircle = boundsCircle;
    }

    public void integrate(VerletCircle circle, float delta) {
        attachCircle(circle);
        applyGravity();
        moveCircle(delta);
    }

    public void attachCircle(VerletCircle circle) {
        currentPos = circle.currentPos;
        oldPos = circle.oldPos;
        acceleration = circle.acceleration;
        radius = circle.radius;
    }

    private void applyGravity() {
        acceleration.add(gravity);
    }

    private void moveCircle(float delta) {
        // Calculate velocity
        velocity.set(currentPos).sub(oldPos);
        // Save current position
        oldPos.set(currentPos);
        // Perform Verlet integration
        currentPos.add(velocity).add(acceleration.scl(delta * delta));
        // Reset acceleration
        acceleration.setZero();
    }

    public void applyRectangleConstraint() {
        currentPos.y = Math.max(5f, Math.min(WINDOW_HEIGHT - 5f, currentPos.y));
        currentPos.x = Math.max(500f, Math.min(WINDOW_WIDTH - 500f, currentPos.x));
    }

    public void applyCircleConstraint() {
        dir.set(currentPos).sub(boundsCircle.x, boundsCircle.y);
        dist = dir.len();
        if (dist > boundsCircle.radius - radius) {
            currentPos.set(boundsCircle.x, boundsCircle.y).add(dir.nor().scl(boundsCircle.radius - radius));
        }
    }
}