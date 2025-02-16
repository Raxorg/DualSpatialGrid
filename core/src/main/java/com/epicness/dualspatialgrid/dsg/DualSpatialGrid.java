package com.epicness.dualspatialgrid.dsg;

import com.badlogic.gdx.utils.OrderedSet;

public class DualSpatialGrid {

    private final SpatialGrid gridA, gridB;
    public final Sizing sizing;
    private final float cellSize, halfCellSize;
    private final OrderedSet<DSGObject> nearby;

    public DualSpatialGrid(Sizing sizing) {
        this.sizing = sizing;
        this.cellSize = sizing.cellSize;
        this.halfCellSize = cellSize * 0.5f;
        gridA = new SpatialGrid(sizing);
        gridB = new SpatialGrid(sizing.expandedCopy());
        nearby = new OrderedSet<>();
    }

    public void clear() {
        gridA.clear();
        gridB.clear();
    }

    public void insert(DSGObject dsgObject) {
        float xA = dsgObject.getCenterX();
        float yA = dsgObject.getCenterY();
        float xB = xA - gridB.offset;
        float yB = yA - gridB.offset;
        int colA = (int) (xA / cellSize);
        int rowA = (int) (yA / cellSize);
        int colB = (int) (xB / cellSize);
        int rowB = (int) (yB / cellSize);
        float centerAx = colA * cellSize + halfCellSize;
        float centerAy = rowA * cellSize + halfCellSize;
        float centerBx = colB * cellSize + halfCellSize;
        float centerBy = rowB * cellSize + halfCellSize;

        float distA = Math.abs(xA - centerAx) + Math.abs(yA - centerAy);
        float distB = Math.abs(xB - centerBx) + Math.abs(yB - centerBy);

        if (distA < distB) {
            dsgObject.col = colA;
            dsgObject.row = rowA;
            dsgObject.setGridA(true);
        } else {
            dsgObject.col = colB;
            dsgObject.row = rowB;
            dsgObject.setGridA(false);
        }
        gridA.insert(dsgObject);
        gridB.insert(dsgObject);
    }

    public OrderedSet<DSGObject> getNearby(DSGObject dsgObject) {
        nearby.clear();
        SpatialGrid mainGrid = dsgObject.isGridA() ? gridA : gridB;
        SpatialGrid otherGrid = (mainGrid == gridA) ? gridB : gridA;

        int col = (int) ((dsgObject.getCenterX() - mainGrid.offset) / cellSize);
        int row = (int) ((dsgObject.getCenterY() - mainGrid.offset) / cellSize);

        if (mainGrid == gridA) {
            for (int c = col; c < col + 2; c++) {
                for (int r = row; r < row + 2; r++) {
                    nearby.addAll(otherGrid.getDSGObjects(c, r));
                }
            }
        } else {
            for (int c = col; c > col - 2; c--) {
                for (int r = row; r > row - 2; r--) {
                    nearby.addAll(otherGrid.getDSGObjects(c, r));
                }
            }
        }

        return nearby;
    }
}