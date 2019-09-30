package com.example.taskslist.controller;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.taskslist.R;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class TasksListViewPagerFragment extends Fragment {

    public static final String CURRENT_VIEWPAGER_ITEM = "current viewpager item";
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TasksListPagerAdapter mTasksListPagerAdapter;
    private Toolbar mToolbar;
    private int mCurrentItem;

    public TasksListViewPagerFragment() {
        // Required empty public constructor
    }

    public TasksListPagerAdapter getTasksListPagerAdapter() {
        return mTasksListPagerAdapter;
    }

    public static TasksListViewPagerFragment newInstance() {

        Bundle args = new Bundle();

        TasksListViewPagerFragment fragment = new TasksListViewPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tasks_list_view_pager, container, false);

        mTabLayout = view.findViewById(R.id.tasks_tablayout);
        mViewPager = view.findViewById(R.id.tasks_viewPager);
        mToolbar = view.findViewById(R.id.toolbar);
        if (mToolbar != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            actionBar.setDisplayShowTitleEnabled(false);
        }
        updateUI();

/*        if(savedInstanceState!=null) {
            mCurrentItem = savedInstanceState.getInt(CURRENT_VIEWPAGER_ITEM);
            Log.d("tlvpfragment","current item"+mCurrentItem);
            mViewPager.setCurrentItem(mCurrentItem);
        }*/

        return view;
    }

    private void updateUI() {
        if (mTasksListPagerAdapter == null) {
            mTasksListPagerAdapter = new TasksListPagerAdapter(getFragmentManager());
            mViewPager.setAdapter(mTasksListPagerAdapter);
        }
    }

/*    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mCurrentItem = mViewPager.getCurrentItem();
        outState.putInt(CURRENT_VIEWPAGER_ITEM,mCurrentItem);
    }*/
}

