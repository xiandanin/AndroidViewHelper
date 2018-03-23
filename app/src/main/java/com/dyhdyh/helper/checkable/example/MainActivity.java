package com.dyhdyh.helper.checkable.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv=findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        ExampleAdapter adapter=new ExampleAdapter(testData());
        rv.setAdapter(adapter);
    }

    private List<String> testData() {
        List<String> data=new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            data.add("Item "+i);
        }
        return data;
    }
}
