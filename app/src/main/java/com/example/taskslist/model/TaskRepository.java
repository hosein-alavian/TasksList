package com.example.taskslist.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.taskslist.R;
import com.example.taskslist.model.greendaoDB.GreenDaoOpenHelper;

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

public class TaskRepository {
    private static TaskRepository instance;
    private final TaskDao mTaskDao;
    private final SQLiteDatabase mDb;
    private final Context mContext;

/*    public SQLiteDatabase getDb() {
        return mDb;
    }*/

    public static TaskRepository getInstance(Context context) {
        if (instance == null)
            instance = new TaskRepository(context);
        return instance;
    }

    private TaskRepository(Context context) {
//        tasksList = new ArrayList<>();
//        taskInit();
//        mDataBase = new TaskOpenHelper(context).getWritableDatabase();
        mContext = context;
        Context appContext = mContext.getApplicationContext();
        mDb = new GreenDaoOpenHelper(appContext).getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(mDb);
        DaoSession daoSession = daoMaster.newSession();

//        mTaskDao = UserRepository.getInstance(context).getTaskDao();
        mTaskDao = daoSession.getTaskDao();
    }


/*    private TaskCursorWrapper queryTask(String where, String[] whereArgs) {
        Cursor cursor = mDataBase.query(NAME,
                null,
                where,
                whereArgs,
                null,
                null,
                null);

        return new TaskCursorWrapper(cursor);
    }*/

/*    private ContentValues getContentValues(Task task) {
        ContentValues values = new ContentValues();
        values.put(Cols.UUID, task.getId().toString());
        values.put(Cols.TITLE, task.getmTitle());
        values.put(Cols.DESCRIPTION, task.getmDescription());
        values.put(Cols.DATE, task.getmDate().getTime());
        values.put(Cols.HOUR, task.getHour().getTime());
        values.put(Cols.STATE, task.getmState()==null?null:task.getmState().toString());
        values.put(Cols.USERID, task.getUserid());

        return values;
    }*/

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
/*        ContentValues values = getContentValues(task);
        mDataBase.insertOrThrow(NAME, null, values);*/
        mTaskDao.insert(task);

    }


    public List<Task> getToDoList(long userId) {
       /* List<Task> toDoList = new ArrayList<>();
        for (Task objects : tasksList) {
            if (objects.getmState() == States.TODO &&
                    objects.getUserid().equals(id))
                toDoList.add(objects);
        }
        return toDoList;*/

/*        List<Task> taskList = new ArrayList<>();
        String where = Cols.STATE + " = ? AND " + Cols.USERID + " = ?";
        String[] whereArgs = new String[]{States.TODO.toString(), String.valueOf(userId)};
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
        }*/

        Log.d("user id in repository", "rep login user id " + userId);


        Log.d("user id in repository", "rep list size " + mTaskDao
                .queryBuilder()
                .where(TaskDao.Properties.MState.eq(States.TODO))
                .list().size());


/*        if(mTaskDao.queryBuilder()
                .where(TaskDao.Properties.MState.eq(States.TODO.toString()),
                        TaskDao.Properties.Userid.eq(userId)).unique()!=null)
        Log.d("user id in repository", "rep task title " + mTaskDao.queryBuilder()
                .where(TaskDao.Properties.MState.eq(States.TODO.toString()),
                        TaskDao.Properties.Userid.eq(userId)).unique().getmTitle());

        return mTaskDao.queryBuilder()
                .where(TaskDao.Properties.MState.eq(States.TODO.toString()),
                        TaskDao.Properties.Userid.eq(userId))
                .list();*/
        User user = UserRepository.getInstance(mContext).getUser(userId);
        if(user.getRole()==Role.ADMIN) {
            List<Task> doneTask = new ArrayList<>();
            for (Task objects : mTaskDao.loadAll()) {
                if (objects.getmState() == States.TODO)
                    doneTask.add(objects);
            }
            return doneTask;
        }

        List<Task> todoList = new ArrayList<>();
        for (Task objects : mTaskDao.loadAll()) {
            if (objects.getmState() == States.TODO &&
                    objects.getUserid().equals(userId))
                todoList.add(objects);
        }
        return todoList;

       /* return mTaskDao.queryBuilder()
                .and(TaskDao.Properties.MState.eq(States.TODO.toString()),
                        TaskDao.Properties.Userid.eq(userId))

                .list();*/
    }

    public List<Task> getDoingList(long userId) {
       /* List<Task> doingList = new ArrayList<>();
        for (Task objects : tasksList) {
            if (objects.getmState() == States.DOING &&
                    objects.getUserid().equals(id))
                doingList.add(objects);
        }
        return doingList;*/

       /* List<Task> taskList = new ArrayList<>();
        String where = Cols.STATE + " = ? AND " + Cols.USERID + " = ?";
        String[] whereArgs = new String[]{States.DOING.toString(), String.valueOf(userId)};
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
        }*/
/*        return mTaskDao.queryBuilder()
                .where(TaskDao.Properties.MState.eq(States.DOING.toString()),
                        TaskDao.Properties.Userid.eq(userId))
                .list();*/
        User user = UserRepository.getInstance(mContext).getUser(userId);
        if(user.getRole()==Role.ADMIN) {
            List<Task> doneTask = new ArrayList<>();
            for (Task objects : mTaskDao.loadAll()) {
                if (objects.getmState() == States.DOING)
                    doneTask.add(objects);
            }
            return doneTask;
        }

        List<Task> doingList = new ArrayList<>();
        for (Task objects : mTaskDao.loadAll()) {
            if (objects.getmState() == States.DOING &&
                    objects.getUserid().equals(userId))
                doingList.add(objects);
        }
        return doingList;
    }

    public List<Task> getDoneList(long userId) {
        /*List<Task> doneList = new ArrayList<>();
        for (Task objects : tasksList) {
            if (objects.getmState() == States.DONE &&
                    objects.getUserid().equals(id))
                doneList.add(objects);
        }
        return doneList;*/

       /* List<Task> taskList = new ArrayList<>();
        String where = Cols.STATE + " = ? AND " + Cols.USERID + " = ?";
        String[] whereArgs = new String[]{States.DONE.toString(), String.valueOf(userId)};
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
        }*/
/*        return mTaskDao.queryBuilder()
                .where(TaskDao.Properties.MState.eq(States.DONE.toString()),
                        TaskDao.Properties.Userid.eq(userId))
                .list();*/

        User user = UserRepository.getInstance(mContext).getUser(userId);
        if(user.getRole()==Role.ADMIN) {
            List<Task> doneTask = new ArrayList<>();
            for (Task objects : mTaskDao.loadAll()) {
                if (objects.getmState() == States.DONE)
                    doneTask.add(objects);
            }
            return doneTask;
        }

        List<Task> doneTask = new ArrayList<>();
        for (Task objects : mTaskDao.loadAll()) {
            if (objects.getmState() == States.DONE &&
                    objects.getUserid().equals(userId))
                doneTask.add(objects);
        }
        return doneTask;
    }

    public int getUserTaskListSize(long userId) {


//        User user = UserRepository.getInstance(mContext).getUser(userId);
            List<Task> taskList = new ArrayList<>();
            for (Task task : mTaskDao.loadAll()) {
                if (task.getUserid() == userId)
                    taskList.add(task);
            }
        return taskList.size();
    }

    public void updateTask(Task task) throws Exception {
        if (task == null)
            throw new Exception(String.valueOf(R.string.task_not_exist));
       /* for (int i = 0; i < tasksList.size(); i++) {
            if (task.getId() == tasksList.get(i).getId()) {
                tasksList.set(i, task);
                break;}
            }*/

/*        ContentValues values = getContentValues(task);
        String where = Cols.UUID + " = ?";
        String[] whereArgs = new String[]{task.getId().toString()};
        mDataBase.update(NAME, values, where, whereArgs);*/
        mTaskDao.update(task);
    }

    public void deleteTask(Task task) throws Exception {
        if (task == null)
            throw new Exception(String.valueOf(R.string.task_not_exist));
/*        for (int i = 0; i < tasksList.size(); i++) {
            if (task.getId() == tasksList.get(i).getId()) {
                tasksList.remove(i);
                break;
            }
        }*/

/*        String where = Cols.UUID + " = ?";
        String[] whereArgs = new String[]{task.getId().toString()};
        mDataBase.delete(NAME, where, whereArgs);*/
        mTaskDao.delete(task);
    }

    public Task getTask(UUID taskId) {

        /*for (int i = 0; i < tasksList.size(); i++) {
            if (id == tasksList.get(i).getId())
                return tasksList.get(i);
        }
        return null;
    }*/

        /*String where = Cols.UUID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        TaskCursorWrapper cursor = queryTask(where, whereArgs);

        try {
            if (cursor == null || cursor.getCount() == 0)
                return null;

            cursor.moveToFirst();
            return cursor.getTask();

        } finally {
            cursor.close();
        }*/
        return mTaskDao.queryBuilder()
                .where(TaskDao.Properties.MId.eq(taskId.toString()))
                .unique();
    }

    public void deleteUserTasks(Long userId){
        for (Task task : mTaskDao.loadAll()) {
            if(userId.equals(task.getUserid()))
                mTaskDao.delete(task);
        }
    }
}
