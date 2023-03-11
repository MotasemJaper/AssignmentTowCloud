package com.example.assignmenttowcloud;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterRecycleVIew extends RecyclerView.Adapter<AdapterRecycleVIew.Viewholder> {

    private Context context;
    private ArrayList<Model> list;
    ProgressDialog dialog;

    public AdapterRecycleVIew(Context context, ArrayList<Model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {


        Model model = list.get(position);
        holder.name.setText(model.getName());
        holder.address.setText(model.getAddress());
        holder.phone.setText(model.getPhone());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        TextView name;
        TextView phone;
        TextView address;
        TextView delete;
        public Viewholder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nameItem);
            phone = itemView.findViewById(R.id.phoneItem);
            address = itemView.findViewById(R.id.addressItem);

        }
    }
}
