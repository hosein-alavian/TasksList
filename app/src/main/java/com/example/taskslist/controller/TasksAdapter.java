package com.example.taskslist.controller;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskslist.R;
import com.example.taskslist.model.Task;

import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksViewHolder> {
    private List<Task> tasksList;
    private TasksListPagerAdapter mTasksListPagerAdapter;

    public TasksAdapter(List<Task> objectList, TasksListPagerAdapter tasksListPagerAdapter) {
        this.tasksList = objectList;
        mTasksListPagerAdapter = tasksListPagerAdapter;
    }

    public void setTasksList(List<Task> tasksList) {
        this.tasksList = tasksList;
    }

    @NonNull
    @Override
    public TasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Activity activity = (Activity) parent.getContext();
        View view = activity.getLayoutInflater().inflate(R.layout.view_holder_row,
                parent,
                false);

        return new TasksViewHolder(view, activity, mTasksListPagerAdapter);
    }


    @Override
    public void onBindViewHolder(@NonNull TasksViewHolder holder, int position) {
        holder.bind(tasksList.get(position));
    }

    @Override
    public int getItemCount() {
        return tasksList == null ? 0 : tasksList.size();
    }

}
