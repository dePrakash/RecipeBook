package com.example.recipebook.ui;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.recipebook.R;
import com.example.recipebook.data.RecipeListItem;

import java.util.ArrayList;
import java.util.List;

public class Favorite_List extends AppCompatActivity {

    RecyclerView recyclerView;
   Adapter listAdapter ;

    MyDataBaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_list);

        getSupportActionBar().setTitle("My Favorite List");

        recyclerView = findViewById(R.id.fav_list);

//        Toolbar toolbar = findViewById(R.id.main_list);
//        setSupportActionBar(toolbar);
        myDB = new MyDataBaseHelper(Favorite_List.this);


        StoreDataInArrays();
    }

    //stevdza-san ------>

    void  StoreDataInArrays(){
        List<RecipeListItem> favList = myDB.readFavoriteData();
//        listAdapter
        Log.d(TAG, "StoreDataInArrays: " + favList);

    }

}



