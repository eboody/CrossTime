package com.eboodnero.crosstime;

import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by eran on 6/22/15.
 */
public class CustomArrayAdapter extends android.widget.ArrayAdapter<String> {
    Context context;
    List<String> hoursList, minutesList, secondsList;
    List<Boolean> isRoundList;
    List<Integer> roundNumberList;
    String[] hoursArray, minutesArray, secondsArray;
    Boolean[] isRoundArray;
    Integer[] roundNumberArray;
    LayoutInflater inflater;


    static class ViewHolder {
        public TextView roundView;
        public TextView hourView;
        public TextView minuteView;
        public TextView secondView;
        public ImageView border;
        public ImageView restImage;
    }

    public CustomArrayAdapter(Context context,
                              int resource,
                              List<String> hoursList,
                              List<String> minutesList,
                              List<String> secondsList,
                              List<Boolean> isRoundList,
                              List<Integer> roundNumberList) {
        super(context, resource, hoursList.toArray(new String[hoursList.size()]));
        this.context = context;
        this.hoursList = hoursList;
        this.minutesList = minutesList;
        this.secondsList = secondsList;
        this.isRoundList = isRoundList;
        this.roundNumberList = roundNumberList;

        MainActivity mainActivity = (MainActivity) context;

        inflater = LayoutInflater.from(context);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            rowView = inflater.inflate(R.layout.rounds_item_layout, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.roundView = (TextView) rowView.findViewById(R.id.round_number);
            viewHolder.hourView = (TextView) rowView.findViewById(R.id.hour_text_view);
            viewHolder.minuteView = (TextView) rowView.findViewById(R.id.minute_text_view);
            viewHolder.secondView = (TextView) rowView.findViewById(R.id.second_text_view);
            viewHolder.border = (ImageView) rowView.findViewById(R.id.left_border);
            viewHolder.restImage = (ImageView) rowView.findViewById(R.id.rest_image);
            rowView.setTag(viewHolder);
        }
        ViewHolder holder = (ViewHolder) rowView.getTag();

        hoursArray = new String[hoursList.size()];
        hoursList.toArray(hoursArray);

        minutesArray = new String[minutesList.size()];
        minutesList.toArray(minutesArray);

        secondsArray = new String[secondsList.size()];
        secondsList.toArray(secondsArray);

        isRoundArray = new Boolean[isRoundList.size()];
        isRoundList.toArray(isRoundArray);

        roundNumberArray = new Integer[roundNumberList.size()];
        roundNumberList.toArray(roundNumberArray);

        holder.hourView.setText(String.format("%02d", Integer.valueOf(hoursArray[position])));
        holder.minuteView.setText(String.format("%02d", Integer.valueOf(minutesArray[position])));
        holder.secondView.setText(String.format("%02d", Integer.valueOf(secondsArray[position])));

        if (isRoundArray[position]) {
            holder.border.setBackgroundResource(R.color.roundRed);
            if(roundNumberArray[position] != 0)
            holder.roundView.setText(String.valueOf(roundNumberArray[position]));
        }
        else {
            holder.border.setBackgroundResource(R.color.restBlue);
            holder.roundView.setVisibility(View.INVISIBLE);
            holder.restImage.setVisibility(View.VISIBLE);

            rowView.findViewById(R.id.round_label).setVisibility(View.INVISIBLE);
        }
        return rowView;
    }

}
