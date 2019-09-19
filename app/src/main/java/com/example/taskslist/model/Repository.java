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
        for (int i = 0; i < tasksNumber; i++) {
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

    public List<TasksObjects> getTodDoList() {
        List<TasksObjects> toDoList=new ArrayList<>();
        for (TasksObjects objects : usertasksList) {
            if(objects.getState()==States.TODO)
                toDoList.add(objects);
        }
        return toDoList;
    }

    public List<TasksObjects> getDoingList() {
        List<TasksObjects> doingList=new ArrayList<>();
        for (TasksObjects objects : usertasksList) {
            if(objects.getState()==States.DOING)
                doingList.add(objects);
        }
        return doingList;
    }

    public List<TasksObjects> getDoneList() {
        List<TasksObjects> doneList=new ArrayList<>();
        for (TasksObjects objects : usertasksList) {
            if(objects.getState()==States.DONE)
                doneList.add(objects);
        }
        return doneList;
    }
}
