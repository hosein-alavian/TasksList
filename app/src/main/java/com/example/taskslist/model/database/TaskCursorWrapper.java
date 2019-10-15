package com.example.taskslist.model.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.taskslist.model.States;
import com.example.taskslist.model.Task;

import java.util.Date;
import java.util.UUID;

public class TaskCursorWrapper extends CursorWrapper {
    public TaskCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Task getTask() {
        UUID id = UUID.fromString(getString(getColumnIndex(TaskDBSchema.Task.Cols.UUID)));
        String title = getString(getColumnIndex(TaskDBSchema.Task.Cols.TITLE));
        String description = getString(getColumnIndex(TaskDBSchema.Task.Cols.DESCRIPTION));
        long date = getLong(getColumnIndex(TaskDBSchema.Task.Cols.DATE));
        long hour = getLong(getColumnIndex(TaskDBSchema.Task.Cols.HOUR));
        States state = States.valueOf(getString(getColumnIndex(TaskDBSchema.Task.Cols.STATE)));
        UUID userid = UUID.fromString(getString(getColumnIndex(TaskDBSchema.Task.Cols.USERID)));

        Task task = new Task(id,userid);
        task.setmTitle(title);
        task.setmDescription(description);
        task.setmDate(new Date(date));
        task.setHour(new Date(hour));
        task.setState(state);

        return task;
    }
}
