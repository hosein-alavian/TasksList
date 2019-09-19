package com.example.taskslist.model;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

public class TasksObjects {
    private String username;
    private States state;
    private List<TasksObjects> taskslist;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public States getState() {
        return state;
    }

    public States setState() {
        Random random=new SecureRandom();
        int randomNumber=random.nextInt(3);
        switch (randomNumber){
            case 0:
                state=States.TODO;
                break;
            case 1:
                state=States.DOING;
                break;
            case 2:
                state=States.DONE;
                break;
        }
        return state;
    }

    public List<TasksObjects> getTasksList() {
        return taskslist;
    }

    public void addTasksList(TasksObjects tasksObjects) {
        taskslist.add(tasksObjects);
    }

    public TasksObjects() {
        this.state=setState();
    }
}
