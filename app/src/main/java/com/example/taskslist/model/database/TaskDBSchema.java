package com.example.taskslist.model.database;

public class TaskDBSchema {
    public static final String NAME="task.db";

    public static final class Task {
        public static final String NAME = "Task";

        public static final class Cols {
            public static final String _ID = "_id";
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DESCRIPTION = "description";
            public static final String DATE = "date";
            public static final String HOUR = "hour";
            public static final String STATE = "state";
            public static final String USERID = "user_id";
        }
    }
}
