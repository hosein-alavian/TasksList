package com.example.taskslist.controller;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskslist.R;
import com.example.taskslist.model.Repository;
import com.example.taskslist.model.TasksObjects;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ToDoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoneFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String USERNAME = "com.example.taskslist.model.username";
    private static final String TASKSNUMBER = "com.example.taskslist.model.tasksnumber";


    // TODO: Rename and change types of parameters
    private String username;
    private int tasksNumber;
    private RecyclerView recyclerView;
    private Adapter tasksAdapter;


    public DoneFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param username    Parameter 1.
     * @param tasksNumber Parameter 2.
     * @return A new instance of fragment ToDoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DoneFragment newInstance(String username, int tasksNumber) {
        DoneFragment fragment = new DoneFragment();
        Bundle args = new Bundle();
        args.putString(USERNAME, username);
        args.putInt(TASKSNUMBER, tasksNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            username = getArguments().getString(USERNAME);
            tasksNumber = getArguments().getInt(TASKSNUMBER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_done, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.donetasks_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
    }

    private void updateUI() {
        List<TasksObjects> tasksList =
                Repository.getInstance(username, tasksNumber).getDoneList();
        if (tasksAdapter == null)
            tasksAdapter = new Adapter(tasksList);
        recyclerView.setAdapter(tasksAdapter);
    }

}
