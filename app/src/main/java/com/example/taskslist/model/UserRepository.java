package com.example.taskslist.model;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.taskslist.model.greendaoDB.GreenDaoOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*import static com.example.taskslist.model.database.TaskDBSchema.Task.NAME;*/

public class UserRepository {
    private static UserRepository instance;
    private final UserDao mUserDao;
    private final SQLiteDatabase mDb;
//    private final TaskDao mTaskDao;

/*    public TaskDao getTaskDao() {
        return mTaskDao;
    }*/

    private UserRepository(Context context) {

//        userList = new ArrayList<>();
//        mDataBase = new UserOpenHelper(context).getWritableDatabase();

        mDb = new GreenDaoOpenHelper(context.getApplicationContext())
                .getWritableDatabase();
        DaoMaster daoMaster=new DaoMaster(mDb);
        DaoSession daoSession=daoMaster.newSession();
        mUserDao = daoSession.getUserDao();
//        mTaskDao = daoSession.getTaskDao();
        User admin = new User("admin", "admin", UUID.randomUUID());
        if(mUserDao.getKey(admin)!=null) {
            admin.setMRole(Role.ADMIN);
            mUserDao.insert(admin);
        }

    }

    public static UserRepository getInstance(Context context) {
        if (instance == null) {
            instance = new UserRepository(context);
        }
        return instance;
    }

    public void insertUser(User user) throws WrongUserNameException {
//        if (userList.contains(user))
//            throw new WrongUserNameException();
//        this.userList.add(user);
//        mDataBase.insert(UserDBSchema.User.NAME, null, getContentValues(user));
        if (mUserDao.hasKey(user))
                throw new WrongUserNameException();
        mUserDao.insert(user);
    }

/*    private ContentValues getContentValues(User user) {
        ContentValues values = new ContentValues();
        values.put(Cols.UUID, user.getId().toString());
        values.put(Cols.USERNAME, user.getUserName());
        values.put(Cols.PASSWORD, user.getPassword());
        return values;
    }*/

/*    private UserCursorWrapper queryUser(String where, String[] whereArgs) {
        Cursor cursor = mDataBase.query(UserDBSchema.User.NAME,
                null,
                where,
                whereArgs,
                null,
                null,
                null);

        return new UserCursorWrapper(cursor);
    }*/

    public boolean login(String userName, String password) {
       /* for (User user : userList)
            if (user.getUserName().equals(userName) &&
                    user.getPassword().equals(password))
                return true;
        return false;*/

/*        String where = Cols.USERNAME + " = ? AND "+Cols.PASSWORD+" = ?";
        String[] whereArgs = new String[]{userName,password};
        UserCursorWrapper cursor = queryUser(where, whereArgs);

        try {
            if (cursor == null || cursor.getCount() == 0)
                return false;

            cursor.moveToFirst();
            return true;

        } finally {
            cursor.close();
        }*/
        return mUserDao.queryBuilder()
                .where(UserDao.Properties.MUserName.eq(userName),
                        UserDao.Properties.MPassword.eq(password))
                .unique()!=null;
    }

    public User getUser(String username, String password) {
        /*for (User user : userList) {
            if (user.getUserName().equals(username) &&
                    user.getPassword().equals(password))
                return user;
        }
        throw new Exception("username or password is not exist");*/

/*        String where = Cols.USERNAME + " = ? AND "+Cols.PASSWORD+" = ?";
        String[] whereArgs = new String[]{username,password};
        UserCursorWrapper cursor = queryUser(where, whereArgs);

        try {
            if (cursor == null || cursor.getCount() == 0)
                return null;

            cursor.moveToFirst();
            return cursor.getUser();

        } finally {
            cursor.close();
        }*/
        return mUserDao.queryBuilder()
                .where(UserDao.Properties.MUserName.eq(username),
                        UserDao.Properties.MPassword.eq(password))
                .unique();
    }

    public User getUser(Long userId) {

        return mUserDao.queryBuilder()
                .where(UserDao.Properties._id.eq(userId))
                .unique();
    }

    public void deleteUser(User user){
        mUserDao.delete(user);
    }

    public List<User> getUserList(){
        return mUserDao.loadAll();
    }
}

