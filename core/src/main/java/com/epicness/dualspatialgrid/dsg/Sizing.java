package com.epicness.dualspatialgrid.dsg;

/**
 * Stores sizing information about the grid like its offset and cell size.
 */
public class Sizing {

    public float offset;
    public float cellSize;

    private Sizing(float offset, float cellSize) {
        this.offset = offset;
        this.cellSize = cellSize;
    }

    public Sizing(float cellSize) {
        this(0, cellSize);
    }

    public Sizing offsetCopy() {
        return new Sizing(offset - cellSize * 0.5f, cellSize);
    }
}