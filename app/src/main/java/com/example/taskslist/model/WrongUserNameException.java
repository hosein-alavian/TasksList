package com.example.taskslist.model;

import androidx.annotation.Nullable;

public class WrongUserNameException extends Exception{

    @Nullable
    @Override
    public String getMessage() {
        return "username is already registered!!";
    }
}

