package com.example.taskslist.controller;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskslist.R;
import com.example.taskslist.model.Task;
import com.example.taskslist.model.TaskRepository;

import java.util.List;
import java.util.UUID;

public class TasksListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String TASKS_LIST_TAB = "com.example.taskslist.taskslisttab";


    // TODO: Rename and change types of parameters
    private RecyclerView mRecyclerView;
    private TasksAdapter mTasksAdapter;
    private int mTasksListTab;
    private TaskListFragmentCallBack mCallBack;

    public TasksAdapter getTasksAdapter() {
        return mTasksAdapter;
    }

    public TasksListFragment() {
        // Required empty public constructor
    }

    static TasksListFragment newInstance(int tasksListTab) {
        TasksListFragment fragment = new TasksListFragment();
        Bundle args = new Bundle();
        args.putInt(TASKS_LIST_TAB, tasksListTab);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof TaskListFragmentCallBack)
            mCallBack= (TaskListFragmentCallBack) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallBack=null;
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

    public void updateUI() {
        List<Task> tasksList = null;
        switch (mTasksListTab) {
            case 0:
                tasksList =
                        TaskRepository.getInstance(getContext()).getToDoList(mCallBack.getUserId());
                break;
            case 1:
                tasksList =
                        TaskRepository.getInstance(getContext()).getDoingList(mCallBack.getUserId());
                break;
            case 2:
                tasksList =
                        TaskRepository.getInstance(getContext()).getDoneList(mCallBack.getUserId());
                break;
        }
        if (mTasksAdapter == null) {
            mTasksAdapter = new TasksAdapter(tasksList);
            mRecyclerView.setAdapter(mTasksAdapter);
        } else {
            mTasksAdapter.setTasksListFiltered(tasksList);
            mTasksAdapter.setTasksList(tasksList);
            mTasksAdapter.notifyDataSetChanged();
        }
    }

    public interface TaskListFragmentCallBack {
        public UUID getUserId();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

}
