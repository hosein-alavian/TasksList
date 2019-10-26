package com.example.taskslist.model.greendaoDB;

import android.content.Context;

import com.example.taskslist.model.DaoMaster;

public class GreenDaoOpenHelper extends DaoMaster.OpenHelper {
    public static final String NAME="task.db";

    public GreenDaoOpenHelper(Context context) {
        super(context, NAME);


    }
}
