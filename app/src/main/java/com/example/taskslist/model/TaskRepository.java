package com.example.taskslist.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.taskslist.R;
import com.example.taskslist.model.database.TaskCursorWrapper;
import com.example.taskslist.model.database.TaskOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.taskslist.model.database.TaskDBSchema.Task.Cols;
import static com.example.taskslist.model.database.TaskDBSchema.Task.NAME;

public class TaskRepository {
    private static TaskRepository instance;
    private static List<Task> tasksList;
    private final SQLiteDatabase mDataBase;

    public static TaskRepository getInstance(Context context) {
        if (instance == null)
            instance = new TaskRepository(context);
        return instance;
    }

    private TaskRepository(Context context) {
        mDataBase = new TaskOpenHelper(context).getWritableDatabase();
//        tasksList = new ArrayList<>();
//        taskInit();
    }


    private TaskCursorWrapper queryTask(String where, String[] whereArgs) {
        Cursor cursor = mDataBase.query(NAME,
                null,
                where,
                whereArgs,
                null,
                null,
                null);

        return new TaskCursorWrapper(cursor);
    }

    private ContentValues getContentValues(Task task) {
        ContentValues values = new ContentValues();
        values.put(Cols.UUID, task.getId().toString());
        values.put(Cols.TITLE, task.getmTitle());
        values.put(Cols.DESCRIPTION, task.getmDescription());
        values.put(Cols.DATE, task.getmDate().getTime());
        values.put(Cols.HOUR, task.getHour().getTime());
        values.put(Cols.STATE, task.getmState().toString());
        values.put(Cols.USERID, task.getUserId().toString());

        return values;
    }

/*    private static void taskInit() {
        tasksList=new ArrayList<>();
        SecureRandom r=new SecureRandom();
        String alphabet="abcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < 5; i++) {
            Task task =new Task();
            task.setmTitle(String.valueOf(alphabet.charAt(r.nextInt(alphabet.length()))));
            task.setmDescription(String.valueOf(alphabet.charAt(r.nextInt(alphabet.length()))));
            task.setState();
            tasksList.add(task);
        }
    }*/

    public void insertTask(Task task) {
        ContentValues values = getContentValues(task);
        mDataBase.insertOrThrow(NAME, null, values);
    }


    public List<Task> getToDoList(UUID userId) {
       /* List<Task> toDoList = new ArrayList<>();
        for (Task objects : tasksList) {
            if (objects.getmState() == States.TODO &&
                    objects.getUserId().equals(id))
                toDoList.add(objects);
        }
        return toDoList;*/
        List<Task> taskList = new ArrayList<>();
        String where = Cols.STATE + " = ? AND " + Cols.USERID + " = ?";
        String[] whereArgs = new String[]{States.TODO.toString(), userId.toString()};
        TaskCursorWrapper cursor = queryTask(where, whereArgs);

        try {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                taskList.add(cursor.getTask());

                cursor.moveToNext();
            }
            return taskList;

        } finally {
            cursor.close();
        }
    }

    public List<Task> getDoingList(UUID userId) {
       /* List<Task> doingList = new ArrayList<>();
        for (Task objects : tasksList) {
            if (objects.getmState() == States.DOING &&
                    objects.getUserId().equals(id))
                doingList.add(objects);
        }
        return doingList;*/
        List<Task> taskList = new ArrayList<>();
        String where = Cols.STATE + " = ? AND " + Cols.USERID + " = ?";
        String[] whereArgs = new String[]{States.DOING.toString(), userId.toString()};
        TaskCursorWrapper cursor = queryTask(where, whereArgs);

        try {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                taskList.add(cursor.getTask());

                cursor.moveToNext();
            }
            return taskList;

        } finally {
            cursor.close();
        }
    }

    public List<Task> getDoneList(UUID userId) {
        /*List<Task> doneList = new ArrayList<>();
        for (Task objects : tasksList) {
            if (objects.getmState() == States.DONE &&
                    objects.getUserId().equals(id))
                doneList.add(objects);
        }
        return doneList;*/
        List<Task> taskList = new ArrayList<>();
        String where = Cols.STATE + " = ? AND " + Cols.USERID + " = ?";
        String[] whereArgs = new String[]{States.DONE.toString(), userId.toString()};
        TaskCursorWrapper cursor = queryTask(where, whereArgs);

        try {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                taskList.add(cursor.getTask());

                cursor.moveToNext();
            }
            return taskList;

        } finally {
            cursor.close();
        }
    }

    public void updateTask(Task task) throws Exception {
       /* for (int i = 0; i < tasksList.size(); i++) {
            if (task.getId() == tasksList.get(i).getId()) {
                tasksList.set(i, task);
                break;}
            }*/
        if (task == null)
            throw new Exception(String.valueOf(R.string.task_not_exist));

        ContentValues values = getContentValues(task);
        String where = Cols.UUID + " = ?";
        String[] whereArgs = new String[]{task.getId().toString()};
        mDataBase.update(NAME, values, where, whereArgs);
    }

    public void deleteTask(Task task) throws Exception {
/*        for (int i = 0; i < tasksList.size(); i++) {
            if (task.getId() == tasksList.get(i).getId()) {
                tasksList.remove(i);
                break;
            }
        }*/
        if (task == null)
            throw new Exception(String.valueOf(R.string.task_not_exist));

        String where = Cols.UUID + " = ?";
        String[] whereArgs = new String[]{task.getId().toString()};
        mDataBase.delete(NAME, where, whereArgs);
    }

    public Task getTask(UUID id) {

        /*for (int i = 0; i < tasksList.size(); i++) {
            if (id == tasksList.get(i).getId())
                return tasksList.get(i);
        }
        return null;
    }*/
        String where = Cols.UUID + " = ?";
        String[] whereArgs = new String[]{id.toString()};
        TaskCursorWrapper cursor = queryTask(where, whereArgs);

        try {
            if (cursor == null || cursor.getCount() == 0)
                return null;

            cursor.moveToFirst();
            return cursor.getTask();

        } finally {
            cursor.close();
        }
    }
}
