package com.example.tommaso.suncity;

import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.HashMap;


public class GameMap {
    private Cell[][] cells;
    private HashMap<Class<? extends Building>, ArrayList<Building>> buildings;

    public GameMap(int width, int height) {
        this.cells = new Cell[width][height];
        buildings = new HashMap<>();
    }

    Cell getAt(Point where) {
        return cells[where.x][where.y];
    }

    public int getHeigth() {
        return this.cells[0].length;
    }

    public int getWidth() {
        return this.cells.length;
    }

    public void addBuilding(Class<? extends Building> buildingClass, Point where) throws CellAlreadyHasBuildingException {
        if (!buildings.containsKey(buildingClass)) {
            buildings.put(buildingClass, new ArrayList<Building>());
        }
        ArrayList<Building> values = buildings.get(buildingClass);
        Building building = null;
        try {
            building = buildingClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (building == null) {
            return;
        }
        values.add(building);

        Point where1;
        for (int x = where.x; x < where.x + building.getWidth(); x++) {
            for (int y = where.y; y < where.y + building.getHeight(); y++) {
                where1 = new Point(x, y);
                Cell cell = getAt(where1);
                if (cell != null && cell.hasBuilding()) {
                    throw new CellAlreadyHasBuildingException();
                }
            }
        }

        for (int x = where.x; x < where.x + building.getWidth(); x++) {
            for (int y = where.y; y < where.y + building.getHeight(); y++) {
                where1 = new Point(x, y);
                Cell cell = getAt(where1);
                if (cell == null) {
                    cells[where1.x][where1.y] = new Cell();
                    cell = getAt(where1);
                }
                cell.setBuilding(building);
            }
        }
    }
}
