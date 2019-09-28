package com.example.taskslist.model;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskRepository {
    private static TaskRepository instance;
    private static List<Task> tasksList;

    public static TaskRepository getInstance() {
        if(instance==null)
             instance = new TaskRepository();
//        taskInit(title, tasksNumber);
        return instance;
    }

    private static void taskInit() {
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
    }

    private TaskRepository() {
//        tasksList =new ArrayList<>();
        taskInit();
    }

    public List<Task> getTasksList() {
        return tasksList;
    }

    public void insertTask(Task task) {
        tasksList.add(task);
    }

    public void setUserTasksList(List<Task> usertasksList) {
        this.tasksList = usertasksList;
    }

    public List<Task> getTodDoList() {
        List<Task> toDoList=new ArrayList<>();
        for (Task objects : tasksList) {
            if(objects.getmState()==States.TODO)
                toDoList.add(objects);
        }
        return toDoList;
    }

    public List<Task> getDoingList() {
        List<Task> doingList=new ArrayList<>();
        for (Task objects : tasksList) {
            if(objects.getmState()==States.DOING)
                doingList.add(objects);
        }
        return doingList;
    }

    public List<Task> getDoneList() {
        List<Task> doneList=new ArrayList<>();
        for (Task objects : tasksList) {
            if(objects.getmState()==States.DONE)
                doneList.add(objects);
        }
        return doneList;
    }

    public void updateTask(Task task){
        for (int i = 0; i < tasksList.size(); i++) {
            if(task.getId()== tasksList.get(i).getId())
                tasksList.set(i,task);
        }
    }

    public void deleteTask(Task task){
        for (int i = 0; i < tasksList.size(); i++) {
            if(task.getId()== tasksList.get(i).getId())
                tasksList.remove(i);
        }
    }

    public Task getTask(UUID id){

        for (int i = 0; i < tasksList.size(); i++) {
            if(id== tasksList.get(i).getId())
                return tasksList.get(i);
        }
        return null;
    }

}
