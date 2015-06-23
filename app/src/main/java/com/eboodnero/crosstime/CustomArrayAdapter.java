package com.eboodnero.crosstime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by eran on 6/22/15.
 */
public class CustomArrayAdapter extends android.widget.ArrayAdapter<String> {
    Context context;
    List<String> hoursList, minutesList, secondsList;
    String[] hoursArray, minutesArray, secondsArray;
    LayoutInflater inflater;

    static class ViewHolder {
        public TextView roundView;
        public TextView hourView;
        public TextView minuteView;
        public TextView secondView;
    }

    public CustomArrayAdapter(Context context, int resource, List<String> hoursList, List<String> minutesList, List<String> secondsList) {
        super(context, resource, hoursList.toArray(new String[hoursList.size()]));
        this.context = context;
        this.hoursList = hoursList;
        this.minutesList = minutesList;
        this.secondsList = secondsList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null){
            rowView = inflater.inflate(R.layout.rounds_item_layout, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.roundView = (TextView) rowView.findViewById(R.id.round_number);
            viewHolder.hourView = (TextView) rowView.findViewById(R.id.hour_text_view);
            viewHolder.minuteView = (TextView) rowView.findViewById(R.id.minute_text_view);
            viewHolder.secondView = (TextView) rowView.findViewById(R.id.second_text_view);
            rowView.setTag(viewHolder);
        }
        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.roundView.setText(String.valueOf(position + 1));

        hoursArray = new String[hoursList.size()];
        hoursList.toArray(hoursArray);

        minutesArray = new String[minutesList.size()];
        minutesList.toArray(minutesArray);

        secondsArray = new String[secondsList.size()];
        secondsList.toArray(secondsArray);

        holder.hourView.setText(hoursArray[position]);
        holder.minuteView.setText(minutesArray[position]);
        holder.secondView.setText(secondsArray[position]);


        return rowView;
    }

}
