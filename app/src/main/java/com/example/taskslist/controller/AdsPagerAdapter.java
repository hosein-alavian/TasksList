package com.example.taskslist.controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.taskslist.model.Repository;
import com.example.taskslist.model.States;

public class AdsPagerAdapter extends FragmentPagerAdapter {
    private String username;
    private int tasksNumber;
    public AdsPagerAdapter(@NonNull FragmentManager fm,String username,int tasksNumber) {
        super(fm);
        this.username=username;
        this.tasksNumber=tasksNumber;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                if(Repository.getInstance(username,tasksNumber).getTodDoList().size()==0)
                    return BlankFragment.newInstance();
                else
                    return ToDoFragment.newInstance(username,tasksNumber);
            case 1:
                if(Repository.getInstance(username,tasksNumber).getDoingList().size()==0)
                    return BlankFragment.newInstance();
                else
                    return DoingFragment.newInstance(username,tasksNumber);
            case 2:
                if(Repository.getInstance(username,tasksNumber).getDoneList().size()==0)
                    return BlankFragment.newInstance();
                else
                    return DoneFragment.newInstance(username,tasksNumber);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0:
                return States.TODO.toString();
            case 1:
                return States.DOING.toString();
            case 2:
                return States.DONE.toString();
            default:
                return null;
        }
    }
}
