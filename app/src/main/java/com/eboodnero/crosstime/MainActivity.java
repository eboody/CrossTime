package com.eboodnero.crosstime;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements AddRoundDialog.OnFragmentInteractionListener, TimeInput.OnFragmentInteractionListener {
    Toolbar toolbar;
    FragmentManager fragmentManager;
    static List<String> hoursList;
    static List<String> minutesList;
    static List<String> secondsList;
    static List<Boolean> isRoundList;
    static List<Integer> roundNumberList;
    static int roundBuffer;
    static boolean roundButtonPressedBuffer;
    static CustomArrayAdapter customArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        hoursList = new ArrayList<>();
        minutesList = new ArrayList<>();
        secondsList = new ArrayList<>();
        isRoundList = new ArrayList<>();
        roundNumberList = new ArrayList<>();

        WorkoutFragment workoutFragment = new WorkoutFragment();
        getFragmentManager().beginTransaction().add(R.id.fragment_view, workoutFragment).commit();


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
        roundButtonPressedBuffer = true;
    }

    public void addRestPressed() {
        fragmentManager = getFragmentManager();
        DialogFragment timeInput = new TimeInput();
        timeInput.show(fragmentManager, "timeInput");
        roundButtonPressedBuffer = false;
    }

    public void onSaveTimeInput(){
        fragmentManager = getFragmentManager();
        WorkoutFragment workOutFragment = (WorkoutFragment) fragmentManager.findFragmentById(R.id.fragment_view);
        workOutFragment.updateList();

    }
    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return dp;
    }
}
