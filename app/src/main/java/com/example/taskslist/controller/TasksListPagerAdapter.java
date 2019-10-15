package com.example.taskslist.controller;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.taskslist.model.States;
import com.example.taskslist.model.TaskRepository;

import java.util.UUID;

public class TasksListPagerAdapter extends FragmentStatePagerAdapter {

    private String mTag;
    private TasksListFragment mToDoFragment;
    private TasksListFragment mDoingFragment;
    private TasksListFragment mDoneFragment;
    private final UUID mId;
    private final Context mContext;

    public TasksListFragment getToDoFragment() {
        return mToDoFragment;
    }

    public TasksListFragment getDoingFragment() {
        return mDoingFragment;
    }

    public TasksListFragment getDoneFragment() {
        return mDoneFragment;
    }

    public String getTag() {
        return mTag;
    }

    public TasksListPagerAdapter(@NonNull FragmentManager fm, UUID id, Context context) {
        super(fm);
        mId = id;
        mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                if (TaskRepository.getInstance(mContext).getToDoList(mId).size() == 0)
                    return BlankFragment.newInstance();
                else {
                    return TasksListFragment.newInstance(position);
                }

            case 1:
                if (TaskRepository.getInstance(mContext).getDoingList(mId).size() == 0)
                    return BlankFragment.newInstance();
                else {
                    return TasksListFragment.newInstance(position);
                }
            case 2:
                if (TaskRepository.getInstance(mContext).getDoneList(mId).size() == 0)
                    return BlankFragment.newInstance();
                else {
                    return TasksListFragment.newInstance(position);
                }
            default:
                return null;
        }
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Fragment createdFragment = (Fragment) super.instantiateItem(container, position);
        // save the appropriate reference depending on position
        switch (position) {
            case 0:
                if (TaskRepository.getInstance(mContext).getToDoList(mId).size() != 0)
                    mToDoFragment = (TasksListFragment) createdFragment;
                break;

            case 1:
                if (TaskRepository.getInstance(mContext).getDoingList(mId).size() != 0)
                    mDoingFragment = (TasksListFragment) createdFragment;
                break;

            case 2:
                if (TaskRepository.getInstance(mContext).getDoneList(mId).size() != 0)
                    mDoneFragment = (TasksListFragment) createdFragment;
                break;
        }
        return createdFragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
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

}
