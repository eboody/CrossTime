package com.eboodnero.crosstime;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements AddRoundDialog.OnFragmentInteractionListener, TimeInput.OnFragmentInteractionListener {
    Toolbar toolbar;
    static FragmentManager fragmentManager;
    static RoundInputFragment roundInputFragment;
    static TimerFragment timerFragment;
    static List<String> hoursList;
    static List<String> minutesList;
    static List<String> secondsList;
    static List<Boolean> isRoundList;
    static List<Integer> roundNumberList;
    static int roundBuffer;
    static boolean roundButtonPressedBuffer;

    static int[] roundsTimeToMilliSecondsArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        //set arraylists for the hours,minutes,seconds of the rounds. also whether the round is a rest or not, as well as the number of the rounds.
        hoursList = new ArrayList<>();
        minutesList = new ArrayList<>();
        secondsList = new ArrayList<>();
        isRoundList = new ArrayList<>();
        roundNumberList = new ArrayList<>();
        fragmentManager = getFragmentManager();

        timerFragment = new TimerFragment();

        roundInputFragment = new RoundInputFragment();
        getFragmentManager().beginTransaction().add(R.id.fragment_container, roundInputFragment).commit();


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
        if(id == R.id.save_menu_item){
            startTimer();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void addRoundPressed() {

        DialogFragment timeInput = new TimeInput();
        timeInput.show(fragmentManager, "timeInput");
        roundButtonPressedBuffer = true;
    }

    public void addRestPressed() {

        DialogFragment timeInput = new TimeInput();
        timeInput.show(fragmentManager, "timeInput");
        roundButtonPressedBuffer = false;
    }

    public void onSaveTimeInput(){

        RoundInputFragment workOutFragment = (RoundInputFragment) fragmentManager.findFragmentById(R.id.fragment_container);
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

    public void startTimer(){
        //create an array with a size equal to the amount of rounds.
        roundsTimeToMilliSecondsArray = new int[isRoundList.size()];

        //convert items in the hours,minutes,seconds arraylists to milliseconds, add them up, and put it in a roundsTimeToMilliSecondsArray index
        for (int i = 0; i < isRoundList.size(); i++){
            roundsTimeToMilliSecondsArray[i] = (Integer.valueOf(hoursList.get(i)) * 3600000) + (Integer.valueOf(minutesList.get(i)) * 60000) + (Integer.valueOf(secondsList.get(i)) * 1000);
        }
        fragmentManager.beginTransaction().replace(R.id.fragment_container, timerFragment, "fragmentContainer").addToBackStack(null).commit();
    }
}
