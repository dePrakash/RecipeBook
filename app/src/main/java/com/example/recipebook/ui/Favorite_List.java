package com.example.recipebook.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.recipebook.R;

public class Favorite_List extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_list);

        getSupportActionBar().setTitle("My Favorite List");

//        Toolbar toolbar = findViewById(R.id.main_list);
//        setSupportActionBar(toolbar);
    }
}

