package com.example.tommaso.suncity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, View.OnClickListener {
    private static final String TAG = MainActivity.class.getName();
    final int WIDTH = 5;
    final int HEIGHT = 5;
    private GridView gridView;
    private GameMap map;
    private int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridView);

        gridView.setNumColumns(WIDTH);
        gridView.setOnItemClickListener(this);
        gridView.setOnItemLongClickListener(this);
        map = new GameMap(WIDTH, HEIGHT);

        gridView.setAdapter(new CellAdapter(this, map));

        findViewById(R.id.main_bottom_menu_add_house).setOnClickListener(this);
        findViewById(R.id.main_bottom_menu_close).setOnClickListener(this);
    }

    private void addHouseAt(int position) throws LoggableException {
        try {
            map.addBuilding(House.class, ((CellAdapter) gridView.getAdapter()).getPointFromPosition(position));
        } catch (CellAlreadyHasBuildingException e) {
            throw new LoggableException("Cannot create an house here");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(TAG, "Item clicked:" + position);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(TAG, "Long Clicked " + position + " element");

        currentPosition = position;
        view.setBackgroundColor(R.color.cell_selected);

        return true;
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.main_bottom_menu_add_house:
                    addHouseAt(currentPosition);
                    break;
            }
        } catch (LoggableException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        ((CellAdapter) gridView.getAdapter()).notifyDataSetChanged();
    }
}
