package com.example.taskslist.controller;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.taskslist.R;

import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimePickerFragment extends DialogFragment {


    public TimePickerFragment() {
        // Required empty public constructor
    }


    private static final String ARG_Task_TIME = "com.example.taskslist.taskTime";
    static final String EXTRA_TASK_TIME = "com.example.taskslist.extratasktime";

    private TimePicker mTimePicker;
    private Date mHour;

    public static TimePickerFragment newInstance(Date hour) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_Task_TIME, hour);
        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHour = (Date) getArguments().getSerializable(ARG_Task_TIME);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater
                .inflate(R.layout.fragment_time_picker, null, false);

        mTimePicker = view.findViewById(R.id.time_picker);

        initTimePicker();

        return new AlertDialog.Builder(getActivity())
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sendResult();
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setView(view)
                .create();
    }


    private void sendResult() {
        int hourOfDay = mTimePicker.getCurrentHour();
        int minutes = mTimePicker.getCurrentMinute();

        Calendar calander = Calendar.getInstance();
        calander.set(Calendar.HOUR, hourOfDay);
        calander.set(Calendar.MINUTE, minutes);
        calander.set(Calendar.AM_PM, Calendar.AM);
        Date hour = calander.getTime();

        Intent intent = new Intent();
        intent.putExtra(EXTRA_TASK_TIME, hour);

        Fragment fragment = getTargetFragment();
        fragment.onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initTimePicker() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mHour);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        mTimePicker.setCurrentHour(hour);
        mTimePicker.setCurrentMinute(minute);

    }

}
