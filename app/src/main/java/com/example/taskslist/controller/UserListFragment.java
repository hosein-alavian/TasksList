package com.example.taskslist.controller;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskslist.R;
import com.example.taskslist.model.TaskRepository;
import com.example.taskslist.model.User;
import com.example.taskslist.model.UserRepository;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserListFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private UserAdapter mAdapter;

    public UserListFragment() {
        // Required empty public constructor
    }

    public static UserListFragment newInstance() {

        Bundle args = new Bundle();

        UserListFragment fragment = new UserListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);

        initUI(view);
        updateUI();

        return view;
    }

    private void initUI(View view) {
        setHasOptionsMenu(true);
        Toolbar toolbar = view.findViewById(R.id.toolbar_userFragment);
        if (toolbar != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            actionBar.setDisplayShowTitleEnabled(false);

            toolbar.setNavigationIcon(R.drawable.back_menu_image);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getFragmentManager().beginTransaction()
                            .replace(R.id.taskslist_container_layout, TasksListViewPagerFragment.newInstance())
                            .commit();
                }
            });
        }


        mRecyclerView = view.findViewById(R.id.userList_recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void updateUI() {
        List<User> userList = UserRepository.getInstance(getContext()).getUserList();
        if (mAdapter == null) {
            mAdapter = new UserAdapter(userList);
            mRecyclerView.setAdapter(mAdapter);
        } else
            mAdapter.notifyDataSetChanged();
    }

    public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

        private List<User> mUserList;

        public UserAdapter(List<User> userList) {
            mUserList = userList;
        }

        public void setUserList(List<User> userList) {
            mUserList = userList;
        }

        @NonNull
        @Override
        public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new UserViewHolder(getLayoutInflater()
                    .inflate(R.layout.user_viewholder_row, null));
        }

        @Override
        public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
            holder.bind(mUserList.get(position));
        }

        @Override
        public int getItemCount() {
            return mUserList == null ? 0 : mUserList.size();
        }
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private final TextView mUsernameTV;
        private final TextView mTasknumberTV;
        private final TextView mRegisterDateTV;
        private final ImageButton mRemoveUserButton;
        private User mUser;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            mUsernameTV = itemView.findViewById(R.id.username_row_textview);
            mTasknumberTV = itemView.findViewById(R.id.tasksnumber_row_textview);
            mRegisterDateTV = itemView.findViewById(R.id.registerdate_row_textview);
            mRemoveUserButton = itemView.findViewById(R.id.removeUser_imageButton);
            mRemoveUserButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AlertDialog.Builder(getContext())
                            .setMessage(R.string.delete_sure_qustion)
                            .setPositiveButton(R.string.sure_remove_dialog, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    UserRepository.getInstance(getContext()).deleteUser(mUser);
                                    TaskRepository.getInstance(getContext()).deleteUserTasks(mUser.get_id());
                                    mAdapter.setUserList(UserRepository.getInstance(getContext())
                                            .getUserList());
                                    mAdapter.notifyDataSetChanged();
//                                    mCallBack.getPagerAdapter().notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton(R.string.add_task_Dialogue_Cansel, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            })
                            .show();
                }
            });
        }

        public void bind(User user) {
            mUsernameTV.setText(user.getUserName());
            user.setTasksCount(TaskRepository.getInstance(getContext())
                    .getUserTaskListSize(user.get_id()));
            mTasknumberTV.setText(String.format(String.valueOf(R.string.task_number),
                    user.getTasksCount()));
            mRegisterDateTV.setText(user.getRegisterDate().toString());

            mUser = user;
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
}
