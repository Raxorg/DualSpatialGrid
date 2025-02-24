package com.epicness.dualspatialgrid.verlet.logic;

import com.epicness.dualspatialgrid.logic.DSGSolver;
import com.epicness.dualspatialgrid.verlet.Soldier;

import java.util.List;

public class Solver {

    private final VerletIntegrator verletIntegrator;
    private final DSGSolver dsgSolver;
    private final BruteForceSolver bruteForceSolver;

    private final List<Soldier> soldiers;
    private Soldier soldier;

    public Solver(VerletIntegrator verletIntegrator, DSGSolver dsgSolver, BruteForceSolver bruteForceSolver, List<Soldier> soldiers) {
        this.verletIntegrator = verletIntegrator;
        this.dsgSolver = dsgSolver;
        this.bruteForceSolver = bruteForceSolver;
        this.soldiers = soldiers;
        soldier = null;
    }

    public void solve(float delta) {
        solveDSG(delta);
    }

    private void solveDSG(float delta) {
        dsgSolver.prepare(soldiers);
        for (int i = 0; i < soldiers.size(); i++) {
            soldier = soldiers.get(i);
            verletIntegrator.integrate(soldier.verletCircle, delta);
            syncDSG(soldier);
            dsgSolver.solveCollisions(soldier);
            syncVerlet(soldier);
            verletIntegrator.applyCircleConstraint();
            syncDSG(soldier);
        }
    }

    private void solveBruteForce(float delta) {
        for (int i = 0; i < soldiers.size(); i++) {
            soldier = soldiers.get(i);
            verletIntegrator.integrate(soldier.verletCircle, delta);
            bruteForceSolver.solveCollisions(soldiers, i);
            verletIntegrator.applyCircleConstraint();
        }
    }

    private void syncDSG(Soldier soldier) {
        soldier.dsgCircle.setPositionCentered(soldier.verletCircle.currentPos.x, soldier.verletCircle.currentPos.y);
    }

    private void syncVerlet(Soldier soldier) {
        soldier.verletCircle.currentPos.set(soldier.dsgCircle.getCenter());
    }
}