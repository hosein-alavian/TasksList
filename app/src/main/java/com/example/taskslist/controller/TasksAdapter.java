package com.example.taskslist.controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskslist.R;
import com.example.taskslist.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksViewHolder> implements Filterable {
    public static final String BIND_ROW = "bind row";
    private List<Task> mTasksList;
    private List<Task> mTasksListFiltered;
    private Activity mActivity;

    public void setTasksList(List<Task> tasksList) {
        this.mTasksList = tasksList;
    }

    public void setTasksListFiltered(List<Task> tasksListFiltered) {
        mTasksListFiltered = tasksListFiltered;
    }

    public TasksAdapter(List<Task> objectList) {
        this.mTasksList = objectList;
        this.mTasksListFiltered = objectList;
    }

    @NonNull
    @Override
    public TasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mActivity = (Activity) parent.getContext();
        View view = mActivity.getLayoutInflater().inflate(R.layout.view_holder_row,
                parent,
                false);

        return new TasksViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull TasksViewHolder holder, int position) {
        holder.bind(mTasksListFiltered.get(position));
    }

    @Override
    public int getItemCount() {
        return mTasksListFiltered ==
                null ? 0 : mTasksListFiltered.size();
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(mTasksListFiltered.get(position).getId().toString());
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String query = charSequence.toString().toLowerCase();

                List<Task> filtered = new ArrayList<>();

                if (query.isEmpty() || query == null)
                    filtered = mTasksList;
                else
                    for (Task task : mTasksList) {
                        if (task.getmTitle().toLowerCase().contains(query) ||
                                task.getmDescription().toLowerCase().contains(query) ||
                                task.getmDate().toString().contains(query) ||
                                task.getHour().toString().contains(query)) {
                            filtered.add(task);
                        }
                    }
                FilterResults results = new FilterResults();
                results.count = filtered.size();
                results.values = filtered;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {
                mTasksListFiltered = (ArrayList<Task>) results.values;
                notifyDataSetChanged();
                if(mTasksListFiltered!=null)
                Log.d(BIND_ROW, "filtered task size: " + mTasksListFiltered.size());

            }
        };
    }


    public class TasksViewHolder extends RecyclerView.ViewHolder {
        public static final String EDIT_TASK = "edit task";
        private TextView mTitleRowTV;
        private TextView mStateRowTV;
        private TextView mDateRowTV;
        private Task mTask;

        public TasksViewHolder(@NonNull final View itemView) {
            super(itemView);
            mTitleRowTV = itemView.findViewById(R.id.title_row_textview);
            mStateRowTV = itemView.findViewById(R.id.state_row_textview);
            mDateRowTV = itemView.findViewById(R.id.date_row_textview);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    FragmentManager fManager = ((FragmentActivity) mActivity)
                            .getSupportFragmentManager();
                    DialogFragment dialogueFragment = DialogFragment
                            .newInstance(mTask.getId());
                    dialogueFragment.show(fManager, EDIT_TASK);
                }
            });
        }

        @SuppressLint({"ResourceAsColor", "SetTextI18n"})
        public void bind(Task task) {
            if (task.getmTitle() != null && task.getmTitle().length()!=0) {
                mTitleRowTV.setText(task.getmTitle().substring(0, 1));
            }
            mStateRowTV.setText(task.getmState().toString());
            mDateRowTV.setText(task.getStrDate() + " " + task.getStrHour());
            mTask = task;
        }
    }

}
