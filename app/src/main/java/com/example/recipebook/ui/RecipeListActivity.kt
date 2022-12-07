package com.example.recipebook.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast

import android.widget.EditText;

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipebook.R
import com.example.recipebook.data.RecipeApi
import com.example.recipebook.data.RecipeListItem
import com.example.recipebook.data.RecipeRepository
import com.example.recipebook.data.RecipeRepository.OnResult
import com.example.recipebook.data.RetrofitClient
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import retrofit2.*

class RecipeListActivity : AppCompatActivity(), Adapter.OnItemClickListener {
    lateinit var api: RecipeApi
    lateinit var listAdapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        supportActionBar?.title = "Home"

        listAdapter = Adapter(this, this)
        val retrofit = RetrofitClient.getInstance()

        api = retrofit.create(RecipeApi::class.java)

        val input = findViewById<TextInputEditText>(R.id.search)
        findViewById<MaterialButton>(R.id.search_button).setOnClickListener {
            val string = input.text.toString().trim()
            search(string)
        }

        val listView = findViewById<RecyclerView>(R.id.search_results)
        listView.adapter = listAdapter
        listView.layoutManager = LinearLayoutManager(this)
    }

    private fun search(string: String) {
        Log.d("LIST", string)
        RecipeRepository().getResults(string, object : OnResult {
            override fun onResult(recipeListItems: MutableList<RecipeListItem>?) {
                Toast.makeText(applicationContext, "Success", Toast.LENGTH_LONG).show()
                listAdapter.setRecipeListItems(recipeListItems)
            }

            override fun onError(errorMessage: String?) {
                Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_LONG).show();
            }
        })
    }

    override fun onClick(item: RecipeListItem?) {

        val intent = Intent(this@RecipeListActivity, RecipeDetailsActivity::class.java)
//        val message = messageEditText.text.toString()
        intent.putExtra("id", item?.id)
        println("list" + item?.id);
        Toast.makeText(applicationContext, item?.id, Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

//

}