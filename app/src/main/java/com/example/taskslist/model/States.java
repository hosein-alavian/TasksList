package com.example.taskslist.model;

public enum States {
    TODO(0),
    DOING(1),
    DONE(2);


private int i;

    States(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }
}

