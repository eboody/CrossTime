package com.eboodnero.crosstime;


import android.os.Bundle;
import android.app.Fragment;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimerFragment extends Fragment {
    TextView totalRoundsView;
    TextView currentRoundView;
    TextView timerView;
    Button stopButton;
    Button startButton;
    CountDownTimer countDownTimer;
    Animation rotateAnimation;

    final String FORMAT = "%02d:%02d:%02d";

    public TimerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_timer, container, false);

        rotateAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_left);

        totalRoundsView = (TextView) v.findViewById(R.id.timer_total_round_view);
        totalRoundsView.setText(String.valueOf(MainActivity.isRoundList.size()));

        currentRoundView = (TextView) v.findViewById(R.id.timer_current_round_view);

        timerView = (TextView) v.findViewById(R.id.timer_text_view);

        stopButton = (Button) v.findViewById(R.id.timer_stop_button);
        stopButton.setVisibility(View.GONE);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, MainActivity.roundInputFragment, "fragmentContainer").disallowAddToBackStack().commit();
                countDownTimer.cancel();
            }
        });

        startButton = (Button) v.findViewById(R.id.timer_start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopButton.setVisibility(View.VISIBLE);
                startButton.setText("Pause");
                CountDownTimer[] countDownTimers = initializeTimers();

                startTimers(countDownTimers);
            }
        });

        // Inflate the layout for this fragment
        return v;
    }

    public void startTimers(CountDownTimer[] timers) {

    }

    public CountDownTimer[] initializeTimers() {
        final int roundNumer = 1;
        final CountDownTimer[] timers = new CountDownTimer[MainActivity.isRoundList.size()];
        for (int i = 0; i < MainActivity.isRoundList.size(); i++) {
            timers[i] = new CountDownTimer(Integer.valueOf(MainActivity.hoursList.get(i)) * 3600000 + Integer.valueOf(MainActivity.minutesList.get(i)) * 60000 + Integer.valueOf(MainActivity.secondsList.get(i)) * 1000, 1000) { // adjust the milli seconds here
                public void onTick(long millisUntilFinished) {
                    timerView.setText("" + String.format(FORMAT,
                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));

                }

                public void onFinish() {
                    if (roundNumer < MainActivity.isRoundList.size()) {
                        
                    }

                }
            };
        }
        return timers;
    }
}