package com.example.recipebook.ui.fav;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recipebook.R;

public class Favorite_Details extends AppCompatActivity {

        TextView heading_fav_details, title_fav_details, summery_fav_details;
        ImageView imageView_fav_details;

        String heading , image, title, summery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_details);
        getSupportActionBar().setTitle("My Favorite Details");



        heading_fav_details = findViewById(R.id.heading_fav);
        title_fav_details = findViewById(R.id.title_fav);
        summery_fav_details = findViewById(R.id.summery_fav);
        imageView_fav_details = findViewById(R.id.imageView_fav);
        getAndSetIntentData();

//

    }

    void getAndSetIntentData(){
        heading = getIntent().getStringExtra("heading");
        Log.d(TAG, "getAndSetIntentData: =====> " + heading);
        heading_fav_details.setText(heading);
        summery_fav_details.setText(summery);
        title_fav_details.setText(title);

        if (getIntent().hasExtra("heading")&& getIntent().hasExtra("image") &&
                getIntent().hasExtra("title") && getIntent().hasExtra("summery")){
            heading = getIntent().getStringExtra("heading");

            image = getIntent().getStringExtra("image");
            title = getIntent().getStringExtra("title");
            summery = getIntent().getStringExtra("summery");


           heading_fav_details.setText("Oiiiiii");
//            summery_fav_details.setText(summery);
            title_fav_details.setText(title);

//            imageView_fav_details.setText(image);

        }else{
//            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }
}
