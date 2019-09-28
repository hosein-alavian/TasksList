package com.example.taskslist.controller;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskslist.R;
import com.example.taskslist.model.Task;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {
    private List<Task> tasksList;

    public Adapter(List<Task> objectList) {
        this.tasksList = objectList;
    }

    public void setTasksList(List<Task> tasksList) {
        this.tasksList = tasksList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Activity activity = (Activity) parent.getContext();
        View view = activity.getLayoutInflater().inflate(R.layout.view_holder_row,
                parent,
                false);

        return new ViewHolder(view,activity);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(tasksList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return tasksList == null ? 0 : tasksList.size();
    }

}
