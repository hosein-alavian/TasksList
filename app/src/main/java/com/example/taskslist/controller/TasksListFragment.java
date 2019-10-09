package com.example.taskslist.controller;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskslist.R;
import com.example.taskslist.model.Task;
import com.example.taskslist.model.TaskRepository;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TasksListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TasksListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String TASKS_LIST_TAB = "com.example.taskslist.taskslisttab";


    // TODO: Rename and change types of parameters
    private RecyclerView mRecyclerView;
    private TasksAdapter mTasksAdapter;
    private int mTasksListTab;
    private TasksListPagerAdapter mTasksListPagerAdapter;


    public TasksListFragment() {
        // Required empty public constructor
    }

    public TasksListFragment(TasksListPagerAdapter tasksListPagerAdapter) {
        mTasksListPagerAdapter = tasksListPagerAdapter;
    }

    // TODO: Rename and change types and number of parameters
    static TasksListFragment newInstance(int tasksListTab, TasksListPagerAdapter tasksListPagerAdapter) {
        TasksListFragment fragment = new TasksListFragment(tasksListPagerAdapter);
        Bundle args = new Bundle();
        args.putInt(TASKS_LIST_TAB, tasksListTab);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            mTasksListTab = getArguments().getInt(TASKS_LIST_TAB);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_taskslist, container, false);
        mRecyclerView = view.findViewById(R.id.tasksList_recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    private void updateUI() {
        List<Task> tasksList = null;
        switch (mTasksListTab) {
            case 0:
                tasksList =
                        TaskRepository.getInstance().getTodDoList();
                break;
            case 1:
                tasksList =
                        TaskRepository.getInstance().getDoingList();
                break;
            case 2:
                tasksList =
                        TaskRepository.getInstance().getDoneList();
                break;
        }
        if (mTasksAdapter == null) {
            mTasksAdapter = new TasksAdapter(tasksList, mTasksListPagerAdapter);
            mRecyclerView.setAdapter(mTasksAdapter);
        } else {
            mTasksAdapter.setTasksList(tasksList);
            mTasksAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
}
