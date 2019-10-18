package com.example.taskslist.controller;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.taskslist.R;
import com.example.taskslist.model.States;
import com.example.taskslist.model.Task;
import com.example.taskslist.model.TaskRepository;

import java.util.Date;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DialogFragment extends androidx.fragment.app.DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final int REQUEST_CODE_DATE_PICKER = 0;
    public static final String DATE_PICKER = "com.example.taskslist.DatePicker";
    public static final int REQUEST_CODE_TIME_PICKER = 1;
    public static final String TIME_PICKER = "com.example.taskslist.timepicker";
    public static final String DIALOG_TASK_ID = "com.example.taskslist.dialogtaskid";
    public static final String SET_STATE_VIEWS = "set state views";

    // TODO: Rename and change types of parameters
    private Task mTask;
    private EditText mDialogueTitleET;
    private EditText mDialogueDescET;
    private Button mDateButton;
    private Button mHourButton;
    private CheckBox mIsDoneCB;
    private AlertDialog alertDialog;
    private LinearLayout dialogLayout;
    private boolean mSetState = false;
    private DialogFragmentCallBack mCallBack;
    private UUID mSavedTaskId;
    private Boolean mAddTaskSavePressed;


    public DialogFragment() {
        // Required empty public constructor
        mAddTaskSavePressed=false;

    }

    // TODO: Rename and change types and number of parameters
    public static DialogFragment newInstance(UUID id) {
        DialogFragment fragment = new DialogFragment();
        Bundle args = new Bundle();
        args.putSerializable(DIALOG_TASK_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof DialogFragmentCallBack)
            mCallBack = (DialogFragmentCallBack) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallBack = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            UUID id = (UUID) getArguments().getSerializable(DIALOG_TASK_ID);
            mTask = TaskRepository.getInstance(getContext()).getTask(id);
        }

        if (savedInstanceState != null) {
            mSetState = savedInstanceState.getBoolean(SET_STATE_VIEWS);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater
                .inflate(R.layout.fragment_dialogue, null, false);

        initUI(view);

        setListener();
        if (mTask.getmState()!= null) {
            editDialogCreate(view);
        } else {
            addDialogCreate(view);
        }


        return alertDialog;
    }

    private void addDialogCreate(View view) {
        mSavedTaskId = mTask.getId();
        mTask.setState(States.TODO);
        alertDialog = new AlertDialog.Builder(getContext())
                .setTitle(R.string.add_task_Dialogue_title)
                .setPositiveButton(R.string.add_task_Dialogue_Save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            TaskRepository.getInstance(getContext()).updateTask(mTask);
                            mAddTaskSavePressed = true;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        mCallBack.updatePagerAdapter();
                    }
                })
                .setNegativeButton(R.string.add_task_Dialogue_Cansel,
                        new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setView(view)
                .create();

        /*alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button button = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                            dismiss();
                        } else
                            Toast.makeText(getActivity(),
                                    getString(R.string.empty_task),
                                    Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });*/
    }

    private void editDialogCreate(View view) {
        if (!mSetState)
            setViewsEnabled(mSetState);
        alertDialog = new AlertDialog.Builder(getContext())
                .setTitle(getString(R.string.edit_task_dialog_title))
                .setNeutralButton("edit", null)
                .setNegativeButton("delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            TaskRepository.getInstance(getContext()).deleteTask(mTask);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        mCallBack.updatePagerAdapter();
                    }
                })
                .setPositiveButton(R.string.add_task_Dialogue_Save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            TaskRepository.getInstance(getContext()).updateTask(mTask);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        mCallBack.updatePagerAdapter();
                    }
                })
                .setView(view)
                .create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialogInterface) {

                Button button = alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL);
                button.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        // TODO Do something
                        mSetState = true;
                        setViewsEnabled(mSetState);
                    }
                });
            }
        });
    }

    private void setListener() {
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
                DatePickerFragment dPFragment = DatePickerFragment.newInstance(mTask.getmDate());
                dPFragment.setTargetFragment(DialogFragment.this, REQUEST_CODE_DATE_PICKER);
                dPFragment.show(getFragmentManager(), DATE_PICKER);

            }
        });

        mHourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment tPFragment = TimePickerFragment.newInstance(mTask.getHour());
                tPFragment.setTargetFragment(DialogFragment.this, REQUEST_CODE_TIME_PICKER);
                tPFragment.show(getFragmentManager(), TIME_PICKER);
            }
        });

        mIsDoneCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mIsDoneCB.isChecked())
                    mTask.setState(States.DONE);
                else
                    mTask.setState(States.TODO);
            }
        });
    }

    private void initUI(View view) {
        dialogLayout = view.findViewById(R.id.dialog_layout);
        mDialogueTitleET = view.findViewById(R.id.title_name_editText_dF);
        mDialogueDescET = view.findViewById(R.id.description_editText_dF);
        mDateButton = view.findViewById(R.id.date_button_dF);
        mHourButton = view.findViewById(R.id.hour_button_dF);
        mIsDoneCB = view.findViewById(R.id.isdone_checkBox_dF);

        mDialogueTitleET.setText(mTask.getmTitle());
        mDialogueDescET.setText(mTask.getmDescription());
        mDateButton.setText(mTask.getStrDate());
        mHourButton.setText(mTask.getStrHour());
        mIsDoneCB.setChecked(mTask.getmState() == States.DONE);
    }

    private void setViewsEnabled(Boolean setState) {

        for (int i = 0; i < dialogLayout.getChildCount(); i++) {
            View child = dialogLayout.getChildAt(i);
            child.setEnabled(setState);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK || data == null)
            return;
        if (requestCode == REQUEST_CODE_DATE_PICKER) {
            mTask.setmDate((Date) data.getSerializableExtra(DatePickerFragment.EXTRA_TASK_DATE));
            mDateButton.setText(mTask.getStrDate());
        }
        if (requestCode == REQUEST_CODE_TIME_PICKER) {
            mTask.setHour((Date) data.getSerializableExtra(TimePickerFragment.EXTRA_TASK_TIME));
            mHourButton.setText(mTask.getStrHour());
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SET_STATE_VIEWS, mSetState);
    }

    public interface DialogFragmentCallBack {
        public void updatePagerAdapter();
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mTask.getId()==mSavedTaskId && !mAddTaskSavePressed) {
            try {
                TaskRepository.getInstance(getContext()).deleteTask(mTask);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
