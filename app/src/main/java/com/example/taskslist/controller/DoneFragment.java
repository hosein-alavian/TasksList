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
public class DoneFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TITLE = "com.example.taskslist.model.Title";
    private static final String TASKSNUMBER = "com.example.taskslist.model.tasksnumber";
    public static final String STATE_FARGMENT = "state fargment";


    // TODO: Rename and change types of parameters
    private RecyclerView mRecyclerView;
    private Adapter mTasksAdapter;


    public DoneFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DoneFragment newInstance() {
        DoneFragment fragment = new DoneFragment();
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
        View view = inflater.inflate(R.layout.fragment_done, container, false);
        mRecyclerView = view.findViewById(R.id.donetasks_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();

        return view;
    }

    private void updateUI() {
        List<Task> tasksList =
                TaskRepository.getInstance().getDoneList();
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
        Log.d(STATE_FARGMENT,"Down Frag On Pause");
    }
}
