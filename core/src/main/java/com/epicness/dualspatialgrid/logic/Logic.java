package com.epicness.dualspatialgrid.logic;

import static com.badlogic.gdx.graphics.Color.SALMON;
import static com.epicness.dualspatialgrid.Constants.BALL_SIZE;
import static com.epicness.dualspatialgrid.Constants.EFFECTIVE_HEIGHT;
import static com.epicness.dualspatialgrid.Constants.EFFECTIVE_WIDTH;
import static com.epicness.dualspatialgrid.Constants.GRID_X;
import static com.epicness.dualspatialgrid.Constants.GRID_Y;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.epicness.dualspatialgrid.Ball;
import com.epicness.dualspatialgrid.DualSpatialGridDemo;

public class Logic {

    public final BallMover ballMover;
    public final CollisionResolver collisionResolver;

    private final Sprite circle;

    private final Array<Ball> balls;


    public Logic(DualSpatialGridDemo demo) {
        ballMover = new BallMover(demo.balls);
        collisionResolver = new CollisionResolver(demo.dualSpatialGrid, demo.balls);
        circle = demo.circle;
        balls = demo.balls;
    }

    public void spawnBalls() {
        for (int i = 0; i < 100; i++) {
            spawnBall();
        }
        System.out.println("#Balls: " + balls.size);
    }

    private void spawnBall() {
        Ball ball = new Ball(circle, BALL_SIZE, SALMON);
        ball.getDSGObject().setX(MathUtils.random(GRID_X + BALL_SIZE, GRID_X + EFFECTIVE_WIDTH - BALL_SIZE));
        ball.getDSGObject().setY(MathUtils.random(GRID_Y + BALL_SIZE, GRID_Y + EFFECTIVE_HEIGHT - BALL_SIZE));
        balls.add(ball);
    }

    public void update(float delta) {
        ballMover.moveBalls(delta);
        collisionResolver.resolveAllCollisions();
    }
}