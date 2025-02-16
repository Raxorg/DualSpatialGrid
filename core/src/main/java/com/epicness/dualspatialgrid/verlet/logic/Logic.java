package com.epicness.dualspatialgrid.verlet.logic;

import com.epicness.dualspatialgrid.verlet.Stuff;

public class Logic {

    public final SoldierSpawner soldierSpawner;
    private final Solver solver;

    private static final float MAX_FRAME_TIME = 1f / 30f;
    private static final float TIME_STEP = 1f / 60f;
    private float accumulator;

    public Logic(Stuff stuff) {
        VerletIntegrator verletIntegrator = new VerletIntegrator(stuff.circle);
        soldierSpawner = new SoldierSpawner(stuff.soldiers);
        solver = new Solver(verletIntegrator, new DSGSolver(stuff.dualSpatialGrid), new BruteForceSolver(), stuff.soldiers);
    }

    public void update(float delta) {
        if (delta > MAX_FRAME_TIME) delta = MAX_FRAME_TIME;
        accumulator += delta;

        while (accumulator >= TIME_STEP) {
            step();
            accumulator -= TIME_STEP;
        }
    }

    private void step() {
        int subSteps = 3;
        float subDelta = TIME_STEP / subSteps;
        for (int i = 0; i < subSteps; i++) {
            solver.solve(subDelta);
        }
    }
}