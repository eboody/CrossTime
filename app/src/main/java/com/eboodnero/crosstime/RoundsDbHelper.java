package com.eboodnero.crosstime;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by eran on 6/20/15.
 */
public class RoundsDbHelper extends SQLiteOpenHelper {

    public static abstract class WorkoutEntry implements BaseColumns {
        public static final String TABLE_NAME = "workout";
        public static final String HOURS = "hours";
        public static final String MINUTES = "minutes";
        public static final String SECONDS = "seconds";
        public static final String TYPE = "type";
    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ", ";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + WorkoutEntry.TABLE_NAME + " (" +
                    WorkoutEntry._ID + " INTEGER PRIMARY KEY, " +
                    WorkoutEntry.TYPE + TEXT_TYPE + COMMA_SEP +
                    WorkoutEntry.HOURS + TEXT_TYPE + COMMA_SEP +
                    WorkoutEntry.MINUTES + TEXT_TYPE + COMMA_SEP +
                    WorkoutEntry.SECONDS + TEXT_TYPE +
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + WorkoutEntry.TABLE_NAME;


    public static final int DATABASE_VERSION = 1;
    public static final String DEFAULT_DATABASE_NAME = "workout.db";

    public RoundsDbHelper(Context context) {
        super(context, DEFAULT_DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}

