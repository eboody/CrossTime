package com.eboodnero.crosstime;

import android.provider.BaseColumns;

/**
 * Created by eran on 6/20/15.
 */
public final class RoundDbContract {
    public RoundDbContract() {
    }

    public static abstract class WorkoutEntry implements BaseColumns {
        public static final String TABLE_NAME = "workout";
        public static final String HOURS = "hours";
        public static final String MINUTES = "minutes";
        public static final String SECONDS = "seconds";
        public static final String TYPE = "type";
    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + WorkoutEntry.TABLE_NAME + " (" +
                    WorkoutEntry._ID + " INTEGER PRIMARY KEY," +
                    WorkoutEntry.TYPE + TEXT_TYPE + COMMA_SEP +
                    WorkoutEntry.HOURS + TEXT_TYPE + COMMA_SEP +
                    WorkoutEntry.MINUTES + TEXT_TYPE + COMMA_SEP +
                    WorkoutEntry.SECONDS + TEXT_TYPE + COMMA_SEP +
            " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + WorkoutEntry.TABLE_NAME;
}
