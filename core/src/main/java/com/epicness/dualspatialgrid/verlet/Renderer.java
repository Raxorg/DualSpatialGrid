package com.epicness.dualspatialgrid.verlet;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;
import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.dualspatialgrid.dsg.DualSpatialGrid;

import java.util.List;

public class Renderer {

    private final SpriteBatch spriteBatch;
    private final ShapeRenderer shapeRenderer;

    private final List<Soldier> soldiers;
    private final Circle circle;
    private final DualSpatialGrid dualSpatialGrid;

    public Renderer(Stuff stuff) {
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        Gdx.gl.glLineWidth(2f);

        soldiers = stuff.soldiers;
        circle = stuff.circle;
        dualSpatialGrid = stuff.dualSpatialGrid;
    }

    public void render() {
        ScreenUtils.clear(BLACK);

        spriteBatch.begin();
        //dualSpatialGrid.draw(spriteBatch);
        spriteBatch.end();

        shapeRenderer.begin();
        drawSoldiers();
        shapeRenderer.setColor(WHITE);
        shapeRenderer.circle(circle.x, circle.y, circle.radius);
        shapeRenderer.end();
    }

    private void drawSoldiers() {
        shapeRenderer.set(Filled);
        for (int i = 0; i < soldiers.size(); i++) {
            Soldier soldier = soldiers.get(i);
            shapeRenderer.setColor(soldier.color);
            shapeRenderer.circle(soldier.verletCircle.currentPos.x, soldier.verletCircle.currentPos.y, soldier.verletCircle.radius);
        }
        shapeRenderer.set(Line);
        for (int i = 0; i < soldiers.size(); i++) {
            Soldier soldier = soldiers.get(i);
            shapeRenderer.setColor(WHITE);
            shapeRenderer.circle(soldier.dsgCircle.getCenterX(), soldier.dsgCircle.getCenterY(), soldier.verletCircle.radius);
        }
    }
}