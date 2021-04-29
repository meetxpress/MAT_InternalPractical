package com.example.internalpractical;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Q1Adapter extends RecyclerView.Adapter<Q1Adapter.ViewHolder> {
    ArrayList<Q1Model> list;
    Context context;
    private ViewGroup parent;
    private int viewType;

    public Q1Adapter(ArrayList<Q1Model> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.q1_displayall, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Q1Model model = list.get(position);
        holder.fImg.setImageResource(model.getImage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fImg = itemView.findViewById(R.id.q1fImg);
        }
    }
}
