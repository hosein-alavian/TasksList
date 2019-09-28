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

    public States setState() {
        Random random=new SecureRandom();
        int randomNumber=random.nextInt(3);
        switch (randomNumber){
            case 0:
                mState =States.TODO;
                break;
            case 1:
                mState =States.DOING;
                break;
            case 2:
                mState =States.DONE;
                break;
        }
        return mState;
    }

    public void setState(States mState) {
        this.mState = mState;
    }

    public Task() {
        mId=UUID.randomUUID();
        mDate=new Date();
        mHour=new Date();
    }

/*    private Date generateRandomDate() {
        GregorianCalendar gc = new GregorianCalendar();
        int year = randBetween(2000, 2019);
        gc.set(gc.YEAR, year);
        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
        gc.set(gc.DAY_OF_YEAR, dayOfYear);

        return gc.getTime();
    }

    private int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }*/

/*    private Date generateDate(){
        GregorianCalendar gc=new GregorianCalendar();
        Date date=new Date();
        gc.setTime(date);
        return gc.getTime();
}
    private Date generateHour(){
        GregorianCalendar gc = new GregorianCalendar();
        gc.get(gc.HOUR_OF_DAY);
        gc.get(gc.MINUTE);
        return gc.getTime();
    }*/

    @SuppressLint("SimpleDateFormat")
    public String getStrHour(){
       return new SimpleDateFormat("hh:mm a").format(getHour());
    }

    @SuppressLint("SimpleDateFormat")
    public String getStrDate(){
        return new SimpleDateFormat("MMM dd,yyyy").format(getHour());
    }
}
