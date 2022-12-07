package com.example.recipebook.ui;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
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
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import retrofit2.Call;

public class RecipeDetailsActivity extends AppCompatActivity {
     RecipeApi api;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

                                            //down blow the onResult method ::v
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        api = RetrofitClient.getInstance().create(RecipeApi.class);

        String id = getIntent().getStringExtra("id");

//        recipeRepository.getDetails(id ,OnResult);

//        text.setText(getIntent().getStringExtra("id"));
        getList(id);

    }




    private void  getList(String id) {
        new RecipeRepository().getDetails(id, new RecipeRepository.OnDetailResult(){


            @Override
            public void onResult(RecipeDetails details) {

                getSupportActionBar().setTitle(""+details.component31());  // here we are set recipe name in the action bar

                TextView textView = (TextView) findViewById(R.id.summery);              // get recipe instruction from api
                textView.setText(Html.fromHtml("<html><body>"+ details.getInstructions()+"</body></html>"));    //


                Log.d(TAG, "Summary: " + details.component29());

                TextView text = (TextView) findViewById(R.id.heading);
                text.setText(" "+details.component26());

                TextView text3 = (TextView) findViewById(R.id.title_d);
                text3.setText(" "+details.component31());


                ImageView image = (ImageView) findViewById(R.id.imageView);

                Picasso.get().load(details.component15()).into(image);

            }
            @Override
            public void onError(String errorMessage) {

            }
        });
    }

}
