package com.example.recipebook.ui;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
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

        api = RetrofitClient.getInstance().create(RecipeApi.class);

//        TextView text = (TextView) findViewById(R.id.test);

//        RecipeRepository recipeRepository = new RecipeRepository();

        String id = getIntent().getStringExtra("id");

//        recipeRepository.getDetails(id ,OnResult);

//        text.setText(getIntent().getStringExtra("id"));
        getList(id);

    }




    private void  getList(String id) {
//        RecipeRepository.OnResult OnResult = null;
        new RecipeRepository().getDetails(id, new RecipeRepository.OnDetailResult(){


            @Override
            public void onResult(RecipeDetails details) {
//                System.out.println("okkkktest" + details.component15());

                TextView text = (TextView) findViewById(R.id.heading);
                text.setText("Title "+details.component26());

                TextView text3 = (TextView) findViewById(R.id.title_d);
                text3.setText(""+details.component31());

                TextView title = (TextView) findViewById(R.id.summery);
                title.setText("Summery "+details.component29());





                ImageView image = (ImageView) findViewById(R.id.imageView);

                Picasso.get().load(details.component15()).into(image);
//                image.setImageDrawable(details.component15());

//                image.setImageBitmap(getBitmapFromURL(""+details.component15()));
//                Bitmap tesdd = getBitmapFromURL(details.component15());
//                Log.d(TAG, "onResuliiiiiiiiiiiiit: " + tesdd);



            }


            @Override
            public void onError(String errorMessage) {

            }
        });
    }



//    public static Bitmap getBitmapFromURL(String src) {
//        try {
//            Log.e("src",src);
//            URL url = new URL(src);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setDoInput(true);
//            connection.connect();
//            InputStream input = connection.getInputStream();
//            Bitmap myBitmap = BitmapFactory.decodeStream(input);
//            Log.e("Bitmap","returned");
//            return myBitmap;
//        } catch (IOException e) {
//            e.printStackTrace();
//            Log.e("Exception",e.getMessage());
//            return null;
//        }
//    }
}
