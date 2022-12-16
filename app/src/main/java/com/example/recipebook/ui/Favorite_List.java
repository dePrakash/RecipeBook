package com.example.recipebook.ui;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.recipebook.R;
import com.example.recipebook.data.RecipeListItem;

import java.util.ArrayList;
import java.util.List;

public class Favorite_List extends AppCompatActivity implements Adapter.OnItemClickListener {
    LinearLayoutManager layoutManager;

    RecyclerView recyclerView;
    Adapter adapter ;

    MyDataBaseHelper myDB;

    List<RecipeListItem> favList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_list);

        getSupportActionBar().setTitle("My Favorite List");
        myDB = new MyDataBaseHelper(this);


//        Toolbar toolbar = findViewById(R.id.main_list);
//        setSupportActionBar(toolbar);

        setTORs();
        StoreDataInArrays();

    }

    //stevdza-san ------>

    void  StoreDataInArrays() {
        favList = myDB.readFavoriteData();
        adapter.setRecipeListItems(favList);
        Log.d(TAG, "StoreDataInArrays: " + favList);

    }

    void setTORs(){
        recyclerView = findViewById(R.id.fav_list);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(this, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(RecipeListItem item) {

    }
}