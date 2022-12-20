package com.example.recipebook.ui.fav;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipebook.R;
import com.example.recipebook.data.RecipeListItem;
import com.example.recipebook.ui.Adapter;
import com.example.recipebook.data.MyDataBaseHelper;

import java.util.List;
                                                            // ItemClickListener implemented here  1
public class Favorite_List extends AppCompatActivity implements Adapter.OnItemClickListener {

            public String  heading;
            public String  title;
            public String  image_url;
            public String  instruction;
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

    //onClick Automatically generated here 2
    //Here Using Item Click Listener for recycler view item -> Fav_List to Fav_Details 3

    @Override
    public void onClick(RecipeListItem item) {

//        Log.d(TAG, "onClick: test ====> " + item);
        Intent intent = new Intent(this, Favorite_Details.class);
        intent.putExtra("id",item.getId());            // <---Value pass to fav details activity Using Intent.
        intent.putExtra("heading",item.getSourceName());
        intent.putExtra("image",item.getImage());
        intent.putExtra("image_type",item.getImageType());
        intent.putExtra("title",item.getTitle());
//        intent.putExtra("title","test");
        intent.putExtra("instruction",item.getInstruction());

        Log.d(TAG, "onClick: test single === > " + item.getTitle());
        startActivity(intent);


//        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();

//        TextView textView = (TextView) findViewById(R.id.summery);
//        textView.setText(Html.fromHtml("<html><body>"+""+"</body></html>"));
//        Log.d(TAG, "StoreDataInArrays: " + summery);
//       // instruction = ""+item.getInstructions();

    }

}