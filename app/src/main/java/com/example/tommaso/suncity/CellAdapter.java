package com.example.tommaso.suncity;

import android.content.Context;
import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;


public class CellAdapter extends BaseAdapter {
    private final Context context;
    private final GameMap map;

    public CellAdapter(Context context, GameMap map) {
        this.context = context;
        this.map = map;
    }

    @Override
    public int getCount() {
        return map.getWidth() * map.getHeigth();
    }

    @Override
    public Object getItem(int position) {
        return map.getAt(getPointFromPosition(position));
    }

    Point getPointFromPosition(int position) {
        return new Point(position % map.getWidth(), position / map.getHeigth());
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Cell cell = map.getAt(getPointFromPosition(position));
        // Point p = getPointFromPosition(position);

        Button b = new Button(this.context);
        b.setFocusable(false);
        // b.setFocusableInTouchMode(false);
        b.setClickable(false);
        b.setText(String.valueOf(position));

        if (cell != null ) {
            Building building = cell.getBuilding();
            if (building != null) {
                b.setBackgroundColor(context.getResources().getColor( R.color.house));
            }
        }

        return b;
    }
}
