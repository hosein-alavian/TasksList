package com.example.taskslist.model.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.taskslist.model.User;

import java.util.UUID;

public class UserCursorWrapper extends CursorWrapper {
    public UserCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public User getUser(){
        UUID id = UUID.fromString(getString(getColumnIndex(UserDBSchema.User.Cols.UUID)));
        String username = getString(getColumnIndex(UserDBSchema.User.Cols.USERNAME));
        String password = getString(getColumnIndex(UserDBSchema.User.Cols.PASSWORD));
        User user=new User(username,password,id);
        return user;
    }
}
