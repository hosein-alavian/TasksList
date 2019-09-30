package com.example.taskslist.controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskslist.R;
import com.example.taskslist.model.Task;
import com.google.android.material.card.MaterialCardView;

public class TasksViewHolder extends RecyclerView.ViewHolder {
    public static final String EDIT_TASK = "edit task";
    private TextView mTitleRowTV;
    private TextView mStateRowTV;
    private TextView mDateRowTV;
    private MaterialCardView mRowCardView;
    private Task mTask;
    private Context mContext;
    private TasksListPagerAdapter mTasksListPagerAdapter;

    public TasksViewHolder(@NonNull final View itemView, Activity activity, TasksListPagerAdapter tasksListPagerAdapter) {
        super(itemView);
        mTasksListPagerAdapter = tasksListPagerAdapter;
        mTitleRowTV = itemView.findViewById(R.id.title_row_textview);
        mStateRowTV = itemView.findViewById(R.id.state_row_textview);
        mRowCardView = itemView.findViewById(R.id.row_card_view);
        mDateRowTV = itemView.findViewById(R.id.date_row_textview);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager manager = ((FragmentActivity) mContext).getSupportFragmentManager();
                DialogueFragment dialogueFragment = DialogueFragment.newInstance(mTask.getId(), mTasksListPagerAdapter);
                dialogueFragment.show(manager, EDIT_TASK);
            }
        });
        mContext = activity;
    }

    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    public void bind(Task task) {
        if (task.getmTitle()!=null) {
            mTitleRowTV.setText(task.getmTitle().substring(0, 1));
        }
        mStateRowTV.setText(task.getmState().toString());
        mDateRowTV.setText(task.getStrDate() + " " + task.getStrHour());
        mTask = task;
    }
}
