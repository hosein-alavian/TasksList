package com.example.taskslist.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.taskslist.R;
import com.example.taskslist.model.Task;
import com.example.taskslist.model.TaskRepository;
import com.example.taskslist.model.User;
import com.example.taskslist.model.UserRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.UUID;

public class TasksListActivity extends AppCompatActivity implements DialogFragment.DialogFragmentCallBack,
        TasksListViewPagerFragment.TaskListViewPagerFragmentCallBack,
        TasksListFragment.TaskListFragmentCallBack {

    public static final String ADD_TASK = "com.example.taskslist.addtask";
    public static final String CURRENT_ITEM = "current item";
    public static final String USERNAME_LOGIN = "username login";
    public static final String PASSWORD_LOGIN = "password login";

    private FloatingActionButton mFloatingActionButton;
    private DialogFragment dialogueFragment;
    private TasksListViewPagerFragment mTasksListViewPagerFragment;
    private String mUsername;
    private String mPassword;
    private UUID mUserId;
    private TasksListFragment mTasksListFragment;

    public static Intent newIntent(Context context, String username, String password) {
        Intent intent = new Intent(context, TasksListActivity.class);
        intent.putExtra(USERNAME_LOGIN, username);
        intent.putExtra(PASSWORD_LOGIN, password);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskslist);
        if (getIntent() != null) {
            mUsername = getIntent().getStringExtra(USERNAME_LOGIN);
            mPassword = getIntent().getStringExtra(PASSWORD_LOGIN);
            User user = UserRepository.getInstance(getApplicationContext()).getUser(
                    mUsername,
                    mPassword);
            mUserId = user.getId();
        }
        mFloatingActionButton = findViewById(R.id.floating_button);
        createUI();

    }

    private void createUI() {

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.taskslist_container_layout, TasksListViewPagerFragment.newInstance())
                .addToBackStack(TasksListViewPagerFragment.class.getSimpleName())
                .commit();
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Task task = new Task(UUID.randomUUID(), mUserId);
                TaskRepository.getInstance(getApplicationContext()).insertTask(task);
//                Log.d("task list size","size: "+TaskRepository.getInstance().getTasksList().size());
                dialogueFragment = DialogFragment.newInstance(task.getId());
                dialogueFragment.show(getSupportFragmentManager(), ADD_TASK);

            }
        });

    }


    @Override
    public void updatePagerAdapter() {
        Fragment fragmentById = getSupportFragmentManager().findFragmentById(R.id.taskslist_container_layout);
        if (fragmentById instanceof TasksListViewPagerFragment) {
            mTasksListViewPagerFragment = (TasksListViewPagerFragment) fragmentById;
            mTasksListViewPagerFragment.updateUI();
        }
    }

    @Override
    public TasksAdapter gettaskAdapter() throws Exception {

        Fragment fragmentById = getSupportFragmentManager()
                .findFragmentById(R.id.taskslist_container_layout);

        if (fragmentById instanceof TasksListViewPagerFragment) {

            mTasksListViewPagerFragment = (TasksListViewPagerFragment) fragmentById;
            int currentTab = mTasksListViewPagerFragment.getTabPosition();
            Log.d(CURRENT_ITEM, "current item " + currentTab);
            switch (currentTab) {
                case 0:
                    mTasksListFragment = mTasksListViewPagerFragment
                            .getTasksListPagerAdapter().getToDoFragment();
                    break;
                case 1:
                    mTasksListFragment = mTasksListViewPagerFragment
                            .getTasksListPagerAdapter().getDoingFragment();
                    break;
                case 2:
                    mTasksListFragment = mTasksListViewPagerFragment
                            .getTasksListPagerAdapter().getDoneFragment();
                    break;
            }
            if (mTasksListFragment != null)
                return mTasksListFragment.getTasksAdapter();
            else
                throw new Exception("task list fragment not exist");

        } else
            throw new Exception("fragmentbyid is not view pager fragment");
    }

    @Override
    public UUID getUserId() {
        return mUserId;
    }
}
