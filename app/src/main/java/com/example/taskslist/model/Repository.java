package com.example.taskslist.model;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private static Repository instance;
    private static List<TasksObjects> usertasksList;

    public static Repository getInstance(String username,int tasksNumber) {
        if(instance==null)
             instance = new Repository();
        taskInit(username, tasksNumber);
        return instance;
    }

    private static void taskInit(String username, int tasksNumber) {
        usertasksList=new ArrayList<>();
        int tasksListSize=tasksNumber;
        for (int i = 0; i <tasksListSize; i++) {
            TasksObjects tasksObjects=new TasksObjects();
            tasksObjects.setUsername(username);
            usertasksList.add(tasksObjects);
        }
    }

    private Repository() {
    }

    public List<TasksObjects> getUserTasksList() {
        return usertasksList;
    }

    public void setUserTasksList(List<TasksObjects> usertasksList) {
        this.usertasksList = usertasksList;
    }
}
