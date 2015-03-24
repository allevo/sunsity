package com.example.tommaso.suncity;

/**
 * Created by tommaso on 24/03/15.
 */
public class Cell {
    private Building building;

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public boolean hasBuilding() {
        return building != null;
    }
}
