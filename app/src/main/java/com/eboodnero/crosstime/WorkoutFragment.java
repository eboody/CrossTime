package com.eboodnero.crosstime;


import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutFragment extends Fragment {
    View parentView;
    Toolbar toolbar;
    FragmentManager fragmentManager;
    Button addButton;
    MainActivity mainActivity;

    public WorkoutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parentView = inflater.inflate(R.layout.fragment_workout_entry, container, false);

        addButton = (Button) parentView.findViewById(R.id.add_button);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddRoundDialog();
            }
        });

        return parentView;
    }

    public void showAddRoundDialog(){
        fragmentManager = getFragmentManager();
        DialogFragment addRound = new AddRoundDialog();
        addRound.show(fragmentManager, "addRound");
    }
}
