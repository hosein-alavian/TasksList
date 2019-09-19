package com.example.taskslist.controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskslist.R;
import com.example.taskslist.model.TasksObjects;

import java.util.List;

public class Adaptor extends RecyclerView.Adapter<ViewHolder> {
    private List<TasksObjects> objectList;

    public Adaptor(List<TasksObjects> objectList) {
        this.objectList = objectList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Activity activity = (Activity) parent.getContext();
        View view = activity.getLayoutInflater().inflate(R.layout.view_holder_row,
                parent,
                false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(objectList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return objectList == null ? 0 : objectList.size();
    }

}
