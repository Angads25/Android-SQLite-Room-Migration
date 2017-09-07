package com.hellohasan.sqlite_project.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hellohasan.sqlite_project.Config;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "student-db";

    // Constructor
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create tables SQL execution
        String CREATE_STUDENT_TABLE = "CREATE TABLE " + Config.TABLE_STUDENT + "("
                + Config.COLUMN_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Config.COLUMN_STUDENT_NAME + " TEXT NOT NULL, "
                + Config.COLUMN_STUDENT_REGISTRATION + " INTEGER NOT NULL UNIQUE, "
                + Config.COLUMN_STUDENT_PHONE + " TEXT, " //nullable
                + Config.COLUMN_STUDENT_EMAIL + " TEXT, " //nullable
                + ")";

        String CREATE_SUBJECT_TABLE = "CREATE TABLE " + Config.TABLE_SUBJECT + "("
                + Config.COLUMN_SUBJECT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Config.COLUMN_REGISTRATION_NUMBER + " INTEGER NOT NULL, "
                + Config.COLUMN_SUBJECT_NAME + " INTEGER NOT NULL, "
                + Config.COLUMN_SUBJECT_CODE + " INTEGER NOT NULL, "
                + Config.COLUMN_SUBJECT_CREDIT + " INTEGER, " //nullable
                + "FOREIGN KEY (" + Config.COLUMN_REGISTRATION_NUMBER + ") REFERENCES " + Config.TABLE_STUDENT + "(" + Config.COLUMN_STUDENT_REGISTRATION + "), "
                + "CONSTRAINT " + Config.STUDENT_SUB_CONSTRAINT + " UNIQUE (" + Config.COLUMN_REGISTRATION_NUMBER + "," + Config.COLUMN_SUBJECT_CODE + ")"
                + ")";

        db.execSQL(CREATE_STUDENT_TABLE);
        db.execSQL(CREATE_SUBJECT_TABLE);

        Logger.d("DB created!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_SUBJECT);

        // Create tables again
        onCreate(db);
    }

}
