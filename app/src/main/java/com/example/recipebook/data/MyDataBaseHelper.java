package com.example.recipebook.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.recipebook.data.RecipeListItem;

import java.util.ArrayList;
import java.util.List;

public class MyDataBaseHelper extends SQLiteOpenHelper {


    private Context context;
    private static final String DATABASE_NAME = "RecipeList.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_recipes";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_HEADING = "image";     // <--heading
    private static final String COLUMN_IMAGE = "imageType";
    private static final String COLUMN_NAME = "title";
    private static final String COLUMN_INSTRUCTION = "instructions";


    public MyDataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_HEADING + " TEXT," +
                COLUMN_IMAGE + " TEXT," +
                COLUMN_NAME + " TEXT," +
                COLUMN_INSTRUCTION + " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    // <--- start step 1 for add or insert
    public void addToFavorite(String HEADING, String IMAGE, String NAME , String INSTRUCTIONS) {
        Log.d("SQLITE", "Adding fav into db");
        Log.d("SQLITE", HEADING + "," + IMAGE + "," + NAME + "," + INSTRUCTIONS );

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_HEADING, IMAGE);
        cv.put(COLUMN_IMAGE, IMAGE);
        cv.put(COLUMN_NAME, NAME);
        cv.put(COLUMN_INSTRUCTION, INSTRUCTIONS);

        long result = db.insert(TABLE_NAME, null, cv);

    } // end step 1 --->


    // <--- start step 1  get or read
   public List<RecipeListItem> readFavoriteData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        List<RecipeListItem> list = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0);
                String heading = cursor.getString(2);
                String image = cursor.getString(1);
                String name = cursor.getString(3);
                String instructions = cursor.getString(4);

                list.add(new RecipeListItem(id,heading, image, null, name, instructions));
            } while (cursor.moveToNext());

            cursor.close();
        }
        if (db != null) {
            db.close();
        }
        return list;
    }

//    public Cursor readAllData() {
//        return null;
//    }
                        // End step 1 --->

}