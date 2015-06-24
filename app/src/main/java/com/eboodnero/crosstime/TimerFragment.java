package com.eboodnero.crosstime;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimerFragment extends Fragment {
    TextView roundView;
    TextView timerView;
    MenuItem ready;
    Animation alphaIn;

    public TimerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_timer, container, false);
        roundView = (TextView) v.findViewById(R.id.timer_total_round_view);
        roundView.setText(String.valueOf(MainActivity.isRoundList.size()));
        timerView = (TextView) v.findViewById(R.id.timer_text_view);

        alphaIn = AnimationUtils.loadAnimation(getActivity(),R.anim.alpha_in);
        timerView.startAnimation(alphaIn);

        // Inflate the layout for this fragment
        return v;
    }


}
