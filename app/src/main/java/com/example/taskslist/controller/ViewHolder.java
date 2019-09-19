package com.example.taskslist.controller;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskslist.R;
import com.example.taskslist.model.TasksObjects;
import com.google.android.material.card.MaterialCardView;

public class ViewHolder extends RecyclerView.ViewHolder {
    private TextView usernameRowTV;
    private TextView stateRowTV;
    private MaterialCardView rowCardView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        usernameRowTV = itemView.findViewById(R.id.username_row_textview);
        stateRowTV = itemView.findViewById(R.id.state_row_textview);
        rowCardView = itemView.findViewById(R.id.row_card_view);
    }

    @SuppressLint("ResourceAsColor")
    public void bind(TasksObjects userTasksList, int position) {
        if (position % 2 == 0)
            rowCardView.setBackgroundColor(R.color.design_default_color_primary_dark);
        usernameRowTV.setText(userTasksList.getUsername());
        stateRowTV.setText(userTasksList.getState().toString());
    }
}
