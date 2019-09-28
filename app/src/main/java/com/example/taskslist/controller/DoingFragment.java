package com.example.taskslist.controller;


import android.os.Bundle;
import android.util.Log;
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
public class DoingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TITLE = "com.example.taskslist.model.title";
    private static final String TASKSNUMBER = "com.example.taskslist.model.tasksnumber";


    // TODO: Rename and change types of parameters
    private RecyclerView mRecyclerView;
    private Adapter mTasksAdapter;


    public DoingFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static DoingFragment newInstance() {
        DoingFragment fragment = new DoingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doing, container, false);
        mRecyclerView= view.findViewById(R.id.doingtasks_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();

        return view;
    }

    private void updateUI() {
        List<Task> tasksList =
                TaskRepository.getInstance().getDoingList();
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

    @Override
    public void onPause() {
        super.onPause();
        Log.d("state","Doing Frag On Pause");
    }
}
