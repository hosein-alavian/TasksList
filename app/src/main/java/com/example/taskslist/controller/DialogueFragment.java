package com.example.taskslist.controller;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.taskslist.R;
import com.example.taskslist.model.TaskRepository;
import com.example.taskslist.model.States;
import com.example.taskslist.model.Task;

import java.util.Date;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DialogueFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DialogueFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String DATE = "date";
    public static final int REQUEST_CODE_DATE_PICKER = 0;
    public static final String DATE_PICKER = "DatePicker";
    public static final String TITLE = "title";
    public static final String TASKS_NUMBER = "tasks number";
    public static final int REQUEST_CODE_TIME_PICKER = 1;
    public static final String TIME_PICKER = "time picker";
    public static final String DIALOG_TASK_ID = "dialog task id";

    // TODO: Rename and change types of parameters
    private Task mTask;
    private Date mDate;
    private Date mHour;
    private DatePicker mDatePicker;
    private EditText mDialogueTitleET;
    private EditText mDialogueDescET;
    private Button mDateButton;
    private Button mHourButton;
    private CheckBox mIsDoneCB;
    private static DialogueFragment fragment;


    public DialogueFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static DialogueFragment newInstance(UUID id) {
        fragment = new DialogueFragment();
        Bundle args = new Bundle();
        args.putSerializable(DIALOG_TASK_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            UUID id = (UUID) getArguments().getSerializable(DIALOG_TASK_ID);
            mTask = TaskRepository.getInstance().getTask(id);
            mDate = mTask.getmDate();
            mHour = mTask.getHour();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater
                .inflate(R.layout.fragment_dialogue, null, false);

        initUI(view);

        setLiscener();

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.add_task_Dialogue_title)
                .setPositiveButton(R.string.add_task_Dialogue_Save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        TaskRepository.getInstance().updateTask(mTask);
//                        Log.d("dialog fragmnet","dialog fragmnet"+String.valueOf(TaskRepository.getInstance().getTasksList().size()));

                    }
                })
                .setNegativeButton(R.string.add_task_Dialogue_Cansel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setView(view)
                .create();
    }

    private void setLiscener() {
        mDialogueTitleET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                mTask.setmTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mDialogueDescET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                mTask.setmDescription(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment dPFragment = DatePickerFragment.newInstance(mDate);
                dPFragment.setTargetFragment(fragment, REQUEST_CODE_DATE_PICKER);
                dPFragment.show(getFragmentManager(), DATE_PICKER);

            }
        });

        mHourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment tPFragment = TimePickerFragment.newInstance(mHour);
                tPFragment.setTargetFragment(fragment, REQUEST_CODE_TIME_PICKER);
                tPFragment.show(getFragmentManager(), TIME_PICKER);
            }
        });

        mIsDoneCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    mTask.setState(States.DONE);
                else
                    mTask.setState(States.TODO);
            }
        });
    }

    private void initUI(View view) {
        mDialogueTitleET = view.findViewById(R.id.title_name_editText_dF);
        mDialogueDescET = view.findViewById(R.id.description_editText_dF);
        mDateButton = view.findViewById(R.id.date_button_dF);
        mHourButton = view.findViewById(R.id.hour_button_dF);
        mIsDoneCB = view.findViewById(R.id.isdone_checkBox_dF);

        mDialogueTitleET.setText(mTask.getmTitle());
        mDialogueDescET.setText(mTask.getmDescription());
        mDateButton.setText(mTask.getStrDate());
        mHourButton.setText(mTask.getStrHour());
        mIsDoneCB.setChecked(mTask.getmState()==States.DONE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK || data == null)
            return;
        if (requestCode == REQUEST_CODE_DATE_PICKER) {
            mTask.setmDate((Date) data.getSerializableExtra(DatePickerFragment.EXTRA_TASK_DATE));
            mDateButton.setText(mTask.getStrDate());
        } else if (requestCode == REQUEST_CODE_TIME_PICKER) {
            mTask.setHour((Date) data.getSerializableExtra(TimePickerFragment.EXTRA_TASK_TIME));
            mHourButton.setText(mTask.getStrHour());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("dialogue fragment","dialogue fragment");
    }
}
