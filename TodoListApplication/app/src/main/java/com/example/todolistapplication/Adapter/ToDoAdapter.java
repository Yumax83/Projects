package com.example.todolistapplication.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistapplication.R;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.MyViewHolder>{

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        CheckBox checkBox;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }
}
