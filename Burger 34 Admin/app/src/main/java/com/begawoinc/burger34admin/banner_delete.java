package com.begawoinc.burger34admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class banner_delete extends AppCompatActivity {


    private RecyclerView recyclerView;
    List<Banner> mybanner;
    Banner mBanner;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_delete);

        recyclerView = findViewById(R.id.card);
        RecyclerView.LayoutManager Manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(Manager);
        mybanner = new ArrayList<>();
        mBanner= new Banner();

        mybanner.add(mBanner);

        final MyAdapter myAdapter =new MyAdapter(banner_delete.this,mybanner);
        recyclerView.setAdapter(myAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Banner");
        eventListener=databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                mybanner.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    Banner banner= itemSnapshot.getValue(Banner.class);
                    banner.setKey(itemSnapshot.getKey());
                    mybanner.add(banner);
                }
                myAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}