/*
package com.example.taskslist.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.UUID;

public class UserOpenHelper extends SQLiteOpenHelper {
    public static final int VERSION = 1;

    public UserOpenHelper(@Nullable Context context) {
        super(context, UserDBSchema.NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + UserDBSchema.User.NAME + "(" +
                UserDBSchema.User.Cols._ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + "," +
                UserDBSchema.User.Cols.UUID + "," +
                UserDBSchema.User.Cols.USERNAME + "," +
                UserDBSchema.User.Cols.PASSWORD + ")"
        );

        sqLiteDatabase.execSQL("insert INTO " +
                UserDBSchema.User.NAME +
                "(" +
                UserDBSchema.User.Cols.UUID + ","
                + UserDBSchema.User.Cols.USERNAME + ","
                + UserDBSchema.User.Cols.PASSWORD +
                ")" +
                " VALUES " +
                "(" +
                UUID.randomUUID().toString()
                + "admin" + ","
                + "admin" +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
*/
