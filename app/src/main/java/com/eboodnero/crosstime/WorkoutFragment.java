package com.eboodnero.crosstime;


import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutFragment extends Fragment {
    View parentView;
    FragmentManager fragmentManager;
    Button addButton;
    Context context;
    ListView listView;
    static CustomArrayAdapter arrayAdapter;
    String[] dummy;

    public WorkoutFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = getActivity();

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = (ListView) getView().findViewById(R.id.rounds_list_view);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parentView = inflater.inflate(R.layout.fragment_workout, container, false);

        addButton = (Button) parentView.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddRoundDialog();
            }
        });

        return parentView;
    }

    public void showAddRoundDialog() {
        fragmentManager = getFragmentManager();
        DialogFragment addRound = new AddRoundDialog();
        addRound.show(fragmentManager, null);

    }



    @Override
    public void onResume() {
        super.onResume();
        updateList();
    }
    public void updateList(){
        arrayAdapter = new CustomArrayAdapter(context, R.layout.rounds_item_layout, MainActivity.hoursList, MainActivity.minutesList, MainActivity.secondsList);
        listView.setAdapter(arrayAdapter);
    }
}
