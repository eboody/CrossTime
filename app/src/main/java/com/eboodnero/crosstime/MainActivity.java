package com.eboodnero.crosstime;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements AddRoundDialog.OnFragmentInteractionListener, TimeInput.OnFragmentInteractionListener{
    Toolbar toolbar;
    FragmentManager fragmentManager;
    Button addButton;
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
    public void addRoundPressed(){
        fragmentManager = getFragmentManager();
        DialogFragment timeInput = new TimeInput();
        timeInput.show(fragmentManager, "timeInput");
    }
    public void addRestPressed(){
        fragmentManager = getFragmentManager();
        DialogFragment timeInput = new TimeInput();
        timeInput.show(fragmentManager, "timeInput");
    }
    public void saveTimeInput(String[] input){
        Toast.makeText(this, input[0] + " " + input[1] + " " + input[2], Toast.LENGTH_SHORT).show();
    }

}
