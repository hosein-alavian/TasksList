package com.example.taskslist.controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.taskslist.model.TaskRepository;
import com.example.taskslist.model.States;

public class AdsPagerAdapter extends FragmentStatePagerAdapter {
    public AdsPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch(position){
            case 0:
                if(TaskRepository.getInstance().getTodDoList().size()==0)
                    return BlankFragment.newInstance();
                else
                    return ToDoFragment.newInstance(position);

            case 1:
                if(TaskRepository.getInstance().getDoingList().size()==0)
                    return BlankFragment.newInstance();
                else
                    return ToDoFragment.newInstance(position);

            case 2:
                if(TaskRepository.getInstance().getDoneList().size()==0)
                    return BlankFragment.newInstance();
                else
                    return ToDoFragment.newInstance(position);
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

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
