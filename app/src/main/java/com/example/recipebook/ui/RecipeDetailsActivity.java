package com.example.recipebook.ui;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipebook.R;
import com.example.recipebook.data.ItemDetails;
import com.example.recipebook.data.RecipeApi;
import com.example.recipebook.data.RecipeDetails;
import com.example.recipebook.data.RecipeListItem;
import com.example.recipebook.data.RecipeRepository;
import com.example.recipebook.data.RetrofitClient;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.zip.Inflater;

import retrofit2.Call;

public class RecipeDetailsActivity<favorite, override> extends AppCompatActivity {
     RecipeApi api;
     CircularProgressIndicator progress_d;  //progress_d

    public String  heading = "";     //  dp  step 1
    public String  title = "";       //  dp  step 2
    public String  image_url = "";   //  dp  step 3
    public String  instruction = ""; //  dp  step 4

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        progress_d = findViewById(R.id.progress_d);           //progress_d

                                            //down blow the onResult method ::v
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        api = RetrofitClient.getInstance().create(RecipeApi.class);

        String id = getIntent().getStringExtra("id");
//        recipeRepository.getDetails(id ,OnResult);

//        text.setText(getIntent().getStringExtra("id"));
        getList(id);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        // <--- dp  start step 9
        if (id == R.id.main_list) {
            MyDataBaseHelper myDB = new MyDataBaseHelper(RecipeDetailsActivity.this);
            myDB.addToFavorite(heading.trim() , image_url.trim() , title.trim()  ,instruction.trim());
            Log.d(TAG, "onOptionsItemSelected:   ===========   >>>>>>   " + heading);
            return true;
        }
        // dp end of step 9 --->

        return super.onOptionsItemSelected(item);
    }


  

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        int id = item.getItemId();
//
//        if(id == R.id.main_list) {
//            Intent intent = new Intent(RecipeDetailsActivity.this,Favorite_List.class);
//            startActivity(intent);
//
//            return true;
//        }else                                 for upcoming menu lists
////        if(id == R.id.main_list) {      <--  change id of the next menu
////            return true;
////        }
//
//        return super.onOptionsItemSelected(item);
//    }


    private void  getList(String id) {
        new RecipeRepository().getDetails(id, new RecipeRepository.OnDetailResult(){

            @Override
            public void onResult(RecipeDetails details) {

                getSupportActionBar().setTitle(""+details.component31());  // here we are set recipe name in the action bar

                TextView textView = (TextView) findViewById(R.id.summery);              // get recipe instruction from api
                textView.setText(Html.fromHtml("<html><body>"+ details.getInstructions()+"</body></html>"));    //
                instruction = ""+details.getInstructions();    // global access dp step 5

                Log.d(TAG, "Summary: " + details.component29());

                TextView text = (TextView) findViewById(R.id.heading);
                text.setText(""+details.getSourceName());
                heading = ""+details.getSourceName();            // global access dp step 6

                TextView text3 = (TextView) findViewById(R.id.title_d);
                text3.setText(" "+details.getTitle());
                title = ""+details.getTitle();              // global access dp step 7

                ImageView image = (ImageView) findViewById(R.id.imageView);
                Picasso.get().load(details.getImage()).into(image);
                image_url = ""+details.getImage();          // global access dp step 8

            }
            @Override
            public void onError(String errorMessage) {

            }
        });
    }

}
