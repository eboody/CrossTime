package com.eboodnero.crosstime;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLClientInfoException;
import java.util.zip.Inflater;


/**
 * Created by eran on 6/20/15.
 */
public class CursorAdapter extends SimpleCursorAdapter {
    Button trashButton;
    TextView id;
    Context context;
    private int layout;
    Cursor cursor;
    private final LayoutInflater layoutInflater;


    public CursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        this.context = context;
        this.layout = layout;
        cursor = c;
        layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.rounds_item_layout, null);
        }
        View row = convertView;
        cursor.moveToPosition(position);


        id = (TextView) convertView.findViewById(R.id.round_number);
        id.setText(cursor.getString(cursor.getColumnIndex(RoundsDbHelper.WorkoutEntry._ID)));

        trashButton = (Button) convertView.findViewById(R.id.trash_button);
        trashButton.setClickable(true);
        trashButton.setFocusable(true);

        trashButton.setTag(id.getText().toString());
        trashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, trashButton.getTag().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return row;
    }

    @Override
    public void bindView(final View view, final Context context, final Cursor cursor) {
        super.bindView(view, context, cursor);
        trashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*                RoundsDbHelper dbHelper = new RoundsDbHelper(context);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete(RoundsDbHelper.WorkoutEntry.TABLE_NAME, "_ID='" + id.getText().toString() + "'", null);*/
                Toast.makeText(context, id.getTag().toString(), Toast.LENGTH_SHORT).show();


            }
        });


    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = layoutInflater.inflate(layout, parent, false);
        return view;
    }

}
