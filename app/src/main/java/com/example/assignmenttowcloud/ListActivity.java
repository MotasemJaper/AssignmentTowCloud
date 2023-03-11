package com.example.assignmenttowcloud;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.Collections;

public class ListActivity extends AppCompatActivity {

    ArrayList<Model> models;
    RecyclerView recyclerView;
    FirebaseDatabase db;
    AdapterRecycleVIew adapterRecycleVIew;
    ProgressBar bar;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_list);
        models = new ArrayList<>();
        Collections.reverse(models);
        recyclerView = findViewById(R.id.recycler);
        bar = findViewById(R.id.progressBar);
        db = FirebaseDatabase.getInstance();
        db.getReference("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()){
                    Model model = new Model();
                    model.setId(ds.child("id").getValue()+"");
                    model.setName(ds.child("name").getValue()+"");
                    model.setPhone(ds.child("phone").getValue()+"");
                    model.setAddress(ds.child("address").getValue()+"");
                    models.add(model);
                    Collections.reverse(models);
                    adapterRecycleVIew = new AdapterRecycleVIew(ListActivity.this,models);
                    LinearLayoutManager manager = new LinearLayoutManager(ListActivity.this);
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setAdapter(adapterRecycleVIew);
                    adapterRecycleVIew.notifyDataSetChanged();
                    bar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }

}