package com.example.taskslist.controller;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.taskslist.R;
import com.google.android.material.tabs.TabLayout;

import java.util.UUID;

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
    private TaskListViewPagerFragmentCallBack mCallBack;

    public TasksListPagerAdapter getTasksListPagerAdapter() {
        return mTasksListPagerAdapter;
    }

    public int getTabPosition(){
       return mTabLayout.getSelectedTabPosition();
    }


    public TasksListViewPagerFragment() {
        // Required empty public constructor
    }

    public static TasksListViewPagerFragment newInstance() {

        Bundle args = new Bundle();

        TasksListViewPagerFragment fragment = new TasksListViewPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof TaskListViewPagerFragmentCallBack)
            mCallBack = (TaskListViewPagerFragmentCallBack) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallBack = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tasks_list_view_pager, container, false);

        initUI(view);
        updateUI();

/*        if(savedInstanceState!=null) {
            mCurrentItem = savedInstanceState.getInt(CURRENT_VIEWPAGER_ITEM);
            Log.d("tlvpfragment","current item"+mCurrentItem);
            mViewPager.setCurrentItem(mCurrentItem);
        }*/

        return view;
    }

    private void initUI(View view) {
        setHasOptionsMenu(true);
        mTabLayout = view.findViewById(R.id.tasks_tablayout);
        mViewPager = view.findViewById(R.id.tasks_viewPager);
        mToolbar = view.findViewById(R.id.toolbar);
        if (mToolbar != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    public void updateUI() {
        if (mTasksListPagerAdapter == null) {
            mTasksListPagerAdapter = new TasksListPagerAdapter(getFragmentManager(),mCallBack.getUserId(),getContext());
            mViewPager.setAdapter(mTasksListPagerAdapter);
        } else
            mTasksListPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);

        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        if (searchView != null) {
            SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
            searchView.setMaxWidth(Integer.MAX_VALUE);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    try {
                        mCallBack.gettaskAdapter().getFilter().filter(query);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String query) {
                    try {
                        mCallBack.gettaskAdapter().getFilter().filter(query);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return true;
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_exit_profile:
                startActivity(new Intent(getContext(), SigninSignupActivity.class));
                return true;
            case R.id.action_search:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

/*    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mCurrentItem = mViewPager.getCurrentItem();
        outState.putInt(CURRENT_VIEWPAGER_ITEM,mCurrentItem);
    }*/

    public interface TaskListViewPagerFragmentCallBack {
        public TasksAdapter gettaskAdapter() throws Exception;
        public UUID getUserId();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
}

