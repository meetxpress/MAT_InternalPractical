package com.example.internalpractical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Q1_Display extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1__display);

        recyclerView = findViewById(R.id.q4RecyclerView);
        ArrayList<Q1Model> list = new ArrayList<Q1Model>();
        list.add(new Q1Model(R.drawable.bring));
        list.add(new Q1Model(R.drawable.onion));
        list.add(new Q1Model(R.drawable.tomato));
        list.add(new Q1Model(R.drawable.lf));

        Q1Adapter adapter = new Q1Adapter(list, this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
    }
}