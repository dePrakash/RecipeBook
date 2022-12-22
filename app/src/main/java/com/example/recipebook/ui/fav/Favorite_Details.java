package com.example.recipebook.ui.fav;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipebook.R;
import com.example.recipebook.data.MyDataBaseHelper;
import com.squareup.picasso.Picasso;

public class Favorite_Details extends AppCompatActivity {

        TextView heading_fav_details, title_fav_details, summery_fav_details;
        ImageView imageView_fav_details;
        Button delete_button;

        String  id, heading , image, title, summery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_details);
        getSupportActionBar().setTitle("My Favorite Details");

        // fav_details used id lists

        heading_fav_details = findViewById(R.id.heading_fav);
        title_fav_details = findViewById(R.id.title_fav);
        summery_fav_details = findViewById(R.id.summery_fav);
        imageView_fav_details = findViewById(R.id.imageView_fav);
        delete_button = findViewById(R.id.delete_button);
        getAndSetIntentData();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
    }


    void getAndSetIntentData(){
        //getString
        id = getIntent().getStringExtra("id");         // <--- id Initialized here 1 (String 27th line)
        heading = getIntent().getStringExtra("title");    //<---- because it's show image url
        image = getIntent().getStringExtra("image") ;
        title  =   getIntent().getStringExtra("title");
        summery = getIntent().getStringExtra("instruction");

        //setString
        Log.d(TAG, "getAndSetIntentData: =====> " + heading); //log check
        heading_fav_details.setText(heading);
        summery_fav_details.setText(summery);
//        Log.d(TAG, "getAndSetIntentData: ===================================>" + summery);   //instruction from fav_list
        Picasso.get().load(image).into(imageView_fav_details);
        title_fav_details.setText(title);

        if (getIntent().hasExtra("heading")&& getIntent().hasExtra("image") &&
                getIntent().hasExtra("title") && getIntent().hasExtra("summery")){
            heading = getIntent().getStringExtra("heading");

            Log.d(TAG, "getAndSetIntentData: ");

        }else{
//            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + heading +" ?");
        builder.setMessage("You want to delete "+ " ?");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDataBaseHelper myDB = new MyDataBaseHelper(Favorite_Details.this);
                myDB.deleteonrow(id);   //here added
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
