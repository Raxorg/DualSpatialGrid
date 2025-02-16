package com.epicness.dualspatialgrid.dsg;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.OrderedSet;

import java.util.HashMap;
import java.util.Map;

public class SpatialGrid {

    public final float cellSize, offset;
    private final Map<GridPoint2, OrderedSet<DSGObject>> gridMap;
    public final Sizing sizing;
    private final GridPoint2 gridPoint2;
    private static final OrderedSet<DSGObject> EMPTY_SET = new OrderedSet<>();

    public SpatialGrid(Sizing sizing) {
        this.sizing = sizing;
        cellSize = sizing.cellSize;
        offset = sizing.offset;

        gridMap = new HashMap<>();
        gridPoint2 = new GridPoint2();
    }

    private OrderedSet<DSGObject> getSet(int col, int row) {
        GridPoint2 key = new GridPoint2(col, row);
        return gridMap.computeIfAbsent(key, k -> new OrderedSet<>());
    }

    public void clear() {
        gridMap.clear();
    }

    public void insert(DSGObject dsgObject) {
        int col = (int) ((dsgObject.getCenterX() - offset) / cellSize);
        int row = (int) ((dsgObject.getCenterY() - offset) / cellSize);

        getSet(col, row).add(dsgObject);
    }

    public OrderedSet<DSGObject> getDSGObjects(int col, int row) {
        return gridMap.getOrDefault(gridPoint2.set(col, row), EMPTY_SET);
    }
}