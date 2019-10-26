package com.example.taskslist.model;

public enum Role {
    NORMAL(0),
    ADMIN(1);


    private int i;

    Role(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }
}
