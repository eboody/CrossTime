package com.eboodnero.crosstime;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TimeInput.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class TimeInput extends DialogFragment {
    View parentView;

    EditText hourView;
    EditText minuteView;
    EditText secondView;

    Button upHourIncrement;
    Button downHourIncrement;

    Button upMinuteIncrement;
    Button downMinuteIncrement;

    Button upSecondIncrement;
    Button downSecondIncrement;

    Button saveButton;


    private OnFragmentInteractionListener mListener;

    public TimeInput() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();

        //if adding a new round, set the text for the edittext views to be the previous entered round time
        //otherwise do the same for the last entered rest time
        if(MainActivity.roundButtonPressedBuffer){
            for(int i = MainActivity.isRoundList.size() - 1; i >= 0; i--){
                if(MainActivity.isRoundList.get(i) == true){
                    hourView.setText(MainActivity.hoursList.get(i));
                    minuteView.setText(MainActivity.minutesList.get(i));
                    secondView.setText(MainActivity.secondsList.get(i));
                    break;
                }
            }
        }
        else {
            for(int i = MainActivity.isRoundList.size() - 1; i >= 0; i--){
                if(MainActivity.isRoundList.get(i) == false){
                    hourView.setText(MainActivity.hoursList.get(i));
                    minuteView.setText(MainActivity.minutesList.get(i));
                    secondView.setText(MainActivity.secondsList.get(i));
                    break;
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parentView = inflater.inflate(R.layout.fragment_time_input, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        saveButton = (Button) parentView.findViewById(R.id.save_button);

        upHourIncrement = (Button) parentView.findViewById(R.id.up_hour_increment);
        downHourIncrement = (Button) parentView.findViewById(R.id.down_hour_increment);
        hourView = (EditText) parentView.findViewById(R.id.hour_edit_view);

        upMinuteIncrement = (Button) parentView.findViewById(R.id.up_minute_increment);
        downMinuteIncrement = (Button) parentView.findViewById(R.id.down_minute_increment);
        minuteView = (EditText) parentView.findViewById(R.id.minute_edit_view);

        upSecondIncrement = (Button) parentView.findViewById(R.id.up_second_increment);
        downSecondIncrement = (Button) parentView.findViewById(R.id.down_second_increment);
        secondView = (EditText) parentView.findViewById(R.id.second_edit_view);


        setSaveButtonListener(saveButton);
        setHourListeners(upHourIncrement, hourView, downHourIncrement);
        setSecondListeners(upSecondIncrement, secondView, downSecondIncrement);
        setMinuteListeners(upMinuteIncrement, minuteView, downMinuteIncrement);


        return parentView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }



    private void setSaveButtonListener(Button saveButton){
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.hoursList.add(hourView.getText().toString());
                MainActivity.minutesList.add(minuteView.getText().toString());
                MainActivity.secondsList.add(secondView.getText().toString());
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.onSaveTimeInput();

                //update arrays according to which button was pressed
                if(MainActivity.roundButtonPressedBuffer == false){
                    MainActivity.isRoundList.add(false);
                    MainActivity.roundNumberList.add(0);
                }
                else {
                    MainActivity.isRoundList.add(true);
                    if(MainActivity.roundNumberList.size() > 0){
                        MainActivity.roundBuffer = MainActivity.roundNumberList.get(0);
                        for(int i = 0; i < MainActivity.roundNumberList.size(); ++i){
                            if (MainActivity.roundBuffer < MainActivity.roundNumberList.get(i)){
                                MainActivity.roundBuffer = MainActivity.roundNumberList.get(i);
                            }
                        }
                    }
                    MainActivity.roundNumberList.add(++MainActivity.roundBuffer);
                }
                dismiss();
            }
        });
    }


    private void setHourListeners(Button upHourIncrement, final EditText hourView, Button downHourIncrement) {
        upHourIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = Integer.valueOf(hourView.getText().toString()) + 1;
                hourView.setText("" + hour);
            }
        });
        downHourIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = Integer.valueOf(hourView.getText().toString()) - 1;
                if (hour + 1 > 0) hourView.setText("" + hour);
            }
        });
    }

    private void setSecondListeners(Button upSecondIncrement, final EditText secondView, Button downSecondIncrement) {
        upSecondIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int second = Integer.valueOf(secondView.getText().toString()) + 1;
                secondView.setText("" + second);
            }
        });
        downSecondIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int second = Integer.valueOf(secondView.getText().toString()) - 1;
                if (second + 1 > 0) secondView.setText("" + second);
            }
        });
    }

    private void setMinuteListeners(Button upMinuteIncrement, final EditText minuteView, Button downMinuteIncrement) {
        upMinuteIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int minute = Integer.valueOf(minuteView.getText().toString()) + 1;
                minuteView.setText("" + minute);
            }
        });
        downMinuteIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int minute = Integer.valueOf(minuteView.getText().toString()) - 1;
                if (minute + 1 > 0) minuteView.setText("" + minute);
            }
        });
    }

}
