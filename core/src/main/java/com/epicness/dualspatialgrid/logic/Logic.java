package com.epicness.dualspatialgrid.logic;

import static com.badlogic.gdx.Input.Keys.R;
import static com.badlogic.gdx.graphics.Color.SALMON;
import static com.epicness.dualspatialgrid.Constants.BALL_SIZE;
import static com.epicness.dualspatialgrid.Constants.EFFECTIVE_HEIGHT;
import static com.epicness.dualspatialgrid.Constants.EFFECTIVE_WIDTH;
import static com.epicness.dualspatialgrid.Constants.GRID_X;
import static com.epicness.dualspatialgrid.Constants.GRID_Y;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.epicness.dualspatialgrid.Ball;
import com.epicness.dualspatialgrid.DualSpatialGridDemo;
import com.epicness.dualspatialgrid.dsg.HasDSGObject;

import java.util.ArrayList;
import java.util.List;

public class Logic {

    public final BallMover ballMover;
    public final CollisionResolver collisionResolver;
    public final DSGSolver dsgSolver;

    private final Sprite circle;

    private final Array<Ball> balls;
    private final List<HasDSGObject> ballList;

    private boolean useNewSolver;

    public Logic(DualSpatialGridDemo demo) {
        ballMover = new BallMover(demo.balls);
        collisionResolver = new CollisionResolver(demo.dualSpatialGrid, demo.balls);
        dsgSolver = new DSGSolver(demo.dualSpatialGrid);
        circle = demo.circle;
        balls = demo.balls;
        ballList = new ArrayList<>();
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

        if (Gdx.input.isKeyJustPressed(R)) {
            useNewSolver = !useNewSolver;
            System.out.println("SolveNewDSG " + useNewSolver);
        }
        if (useNewSolver) {
            solveNewDSG();
        } else {
            collisionResolver.resolveAllCollisions();
        }
    }

    private void solveNewDSG() {
        ballList.clear();
        for (int i = 0; i < balls.size; i++) {
            ballList.add(balls.get(i));
        }

        for (int a = 0; a < 3; a++) {
            dsgSolver.prepare(ballList);
            for (int i = 0; i < balls.size; i++) {
                dsgSolver.solveCollisions(ballList.get(i));
            }
        }
    }
}