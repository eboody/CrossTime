package com.eboodnero.crosstime;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements AddRoundDialog.OnFragmentInteractionListener, TimeInput.OnFragmentInteractionListener {
    Toolbar toolbar;
    FragmentManager fragmentManager;
    Button addButton;
    CursorAdapter cursorAdapter;
    Cursor cursor;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        WorkoutFragment workoutFragment = new WorkoutFragment();
        getFragmentManager().beginTransaction().add(R.id.fragment_view, workoutFragment).commit();


    }

    @Override
    protected void onResume() {
        super.onResume();
        RoundsDbHelper dbHelper = new RoundsDbHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();

        String[] fromColumns = {RoundsDbHelper.WorkoutEntry._ID, RoundsDbHelper.WorkoutEntry.HOURS, RoundsDbHelper.WorkoutEntry.MINUTES, RoundsDbHelper.WorkoutEntry.SECONDS};
        int[] toViews = {R.id.round_number, R.id.hour_text_view, R.id.minute_text_view, R.id.second_text_view};

        //cursor = db.query(RoundsDbHelper.WorkoutEntry.TABLE_NAME, fromColumns, null, null, null, null, null);
        cursor = db.query(RoundsDbHelper.WorkoutEntry.TABLE_NAME, fromColumns, RoundsDbHelper.WorkoutEntry.INACTIVE + "=?",  new String[]{"0"}, null, null, null);

        cursorAdapter = new CursorAdapter(this, R.layout.rounds_item_layout, cursor, fromColumns, toViews, 0);
        final ListView listView = (ListView) findViewById(R.id.rounds_list_view);
        listView.setAdapter(cursorAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void addRoundPressed() {
        fragmentManager = getFragmentManager();
        DialogFragment timeInput = new TimeInput();
        timeInput.show(fragmentManager, "timeInput");
    }

    public void addRestPressed() {
        fragmentManager = getFragmentManager();
        DialogFragment timeInput = new TimeInput();
        timeInput.show(fragmentManager, "timeInput");
    }

    public void saveTimeInput(String[] input) {
        RoundsDbHelper dbHelper = new RoundsDbHelper(getApplicationContext());
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RoundsDbHelper.WorkoutEntry.TYPE, "round");
        values.put(RoundsDbHelper.WorkoutEntry.HOURS, input[0]);
        values.put(RoundsDbHelper.WorkoutEntry.MINUTES, input[1]);
        values.put(RoundsDbHelper.WorkoutEntry.SECONDS, input[2]);
        values.put(RoundsDbHelper.WorkoutEntry.INACTIVE, input[3]);
        long newRowId;
        newRowId = database.insert(RoundsDbHelper.WorkoutEntry.TABLE_NAME, null, values);

        onResume();
    }
    public void delete(int id){
        db.delete(RoundsDbHelper.WorkoutEntry.TABLE_NAME, "_ID='" + id + "'", null);
    }

}
