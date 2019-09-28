package com.example.taskslist.controller;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskslist.R;
import com.example.taskslist.model.TaskRepository;
import com.example.taskslist.model.Task;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ToDoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ToDoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TITILE = "com.example.taskslist.model.title";
    private static final String TASKSNUMBER = "com.example.taskslist.model.tasksnumber";
    public static final String TASKS_LIST_TAB = "tasks list tab";


    // TODO: Rename and change types of parameters
    private RecyclerView mRecyclerView;
    private Adapter mTasksAdapter;
    int mTasksListTab;


    public ToDoFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    static ToDoFragment newInstance(int tasksListTab) {
        ToDoFragment fragment = new ToDoFragment();
        Bundle args = new Bundle();
        args.putInt(TASKS_LIST_TAB,tasksListTab);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null)
            mTasksListTab=getArguments().getInt(TASKS_LIST_TAB);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_todo, container, false);
        mRecyclerView = view.findViewById(R.id.todotasks_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    private void updateUI() {
        List<Task> tasksList=null;
        switch (mTasksListTab){
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
            mTasksAdapter = new Adapter(tasksList);
            mRecyclerView.setAdapter(mTasksAdapter);
        }
        else {
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
