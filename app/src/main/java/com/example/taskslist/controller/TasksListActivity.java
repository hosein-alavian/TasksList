package com.example.taskslist.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.taskslist.R;
import com.example.taskslist.model.TaskRepository;
import com.example.taskslist.model.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class TasksListActivity extends AppCompatActivity {

    private static final String TITLE = "com.example.taskslist.title";
    private static final String TASKS_NUMBER = "com.example.taskslist.tasksnumber";
    public static final int REQUEST_CODE_ADD_TASK = 0;
    public static final String ADD_TASK = "add task";
    public static final String SEC_ACTIVITY_STATE = "sec activity state";
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private AdsPagerAdapter mAdsPagerAdapter;
    private FloatingActionButton mFloatingActionButton;
    private DialogueFragment dialogueFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskslist);

        mTabLayout = findViewById(R.id.tasks_tablayout);
        mViewPager = findViewById(R.id.tasks_viewPager);
        updateUI();
        mFloatingActionButton =findViewById(R.id.floating_button);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Task task=new Task();
                TaskRepository.getInstance().insertTask(task);
                dialogueFragment=DialogueFragment.newInstance(task.getId());
                dialogueFragment.show(getSupportFragmentManager(),ADD_TASK);

            }
        });
    }

    private void updateUI() {
        if(mAdsPagerAdapter==null) {
            mAdsPagerAdapter = new AdsPagerAdapter(getSupportFragmentManager());
            mViewPager.setAdapter(mAdsPagerAdapter);
            mTabLayout.setupWithViewPager(mViewPager);
//            mViewPager.setOffscreenPageLimit(4);
        }
        else
            mAdsPagerAdapter.notifyDataSetChanged();
    }

    public static Intent newIntent(Context context) {
        Intent intent=new Intent(context, TasksListActivity.class);
        return intent;
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.d(SEC_ACTIVITY_STATE,"sec activity on pause");

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
        Log.d(SEC_ACTIVITY_STATE,"sec acti on resume");
    }
}
