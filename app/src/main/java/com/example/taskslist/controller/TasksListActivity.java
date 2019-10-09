package com.example.taskslist.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.taskslist.R;
import com.example.taskslist.model.Task;
import com.example.taskslist.model.TaskRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TasksListActivity extends AppCompatActivity {

    public static final String ADD_TASK = "com.example.taskslist.addtask";

    private FloatingActionButton mFloatingActionButton;
    private DialogueFragment dialogueFragment;
    private TasksListViewPagerFragment mTasksListViewPagerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskslist);

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
                Task task = new Task();
                TaskRepository.getInstance().insertTask(task);
                Fragment fragmentById = getSupportFragmentManager().findFragmentById(R.id.taskslist_container_layout);
                if (fragmentById instanceof TasksListViewPagerFragment)
                    mTasksListViewPagerFragment = (TasksListViewPagerFragment) fragmentById;
                dialogueFragment = DialogueFragment.newInstance(task.getId(), mTasksListViewPagerFragment.getTasksListPagerAdapter());
                dialogueFragment.show(getSupportFragmentManager(), ADD_TASK);

            }
        });

    }


    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, TasksListActivity.class);
        return intent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_exit_profile:
                startActivity(new Intent(getApplicationContext(), SigninSignupActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
