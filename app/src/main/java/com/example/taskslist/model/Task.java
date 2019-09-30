package com.example.taskslist.model;

import android.annotation.SuppressLint;
import android.icu.util.Calendar;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Task {
    private UUID mId;
    private String mTitle;
    private String mDescription;
    private Date mDate;
    private Date mHour;
    private States mState;

    public UUID getId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public Date getHour() {
        return mHour;
    }

    public void setHour(Date hour) {
        mHour = hour;
    }

    public States getmState() {
        return mState;
    }


    public void setState(States mState) {
        this.mState = mState;
    }

    public Task() {
        mId=UUID.randomUUID();
        mDate=new Date();
        mHour=new Date();
        mState=States.TODO;
    }

    @SuppressLint("SimpleDateFormat")
    public String getStrHour(){
       return new SimpleDateFormat("hh:mm a").format(getHour());
    }

    @SuppressLint("SimpleDateFormat")
    public String getStrDate(){
        return new SimpleDateFormat("MMM dd,yyyy").format(getmDate());
    }
}
