package com.epicness.dualspatialgrid.verlet;

import static com.epicness.dualspatialgrid.Constants.CELL_SIZE;
import static com.epicness.dualspatialgrid.Constants.WINDOW_HEIGHT;
import static com.epicness.dualspatialgrid.Constants.WINDOW_WIDTH;

import com.badlogic.gdx.math.Circle;
import com.epicness.dualspatialgrid.dsg.DualSpatialGrid;
import com.epicness.dualspatialgrid.dsg.Sizing;

import java.util.ArrayList;
import java.util.List;

public class Stuff {

    public final List<Soldier> soldiers;
    public final Circle circle;
    public final DualSpatialGrid dualSpatialGrid;

    public Stuff() {
        soldiers = new ArrayList<>();
        circle = new Circle(WINDOW_WIDTH * 0.5f, WINDOW_HEIGHT * 0.5f, WINDOW_HEIGHT * 0.5f);
        Sizing sizing = new Sizing(CELL_SIZE);
        dualSpatialGrid = new DualSpatialGrid(sizing);
    }
}