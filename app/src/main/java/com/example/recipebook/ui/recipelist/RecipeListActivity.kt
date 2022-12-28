package com.example.recipebook.ui.recipelist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipebook.R
import com.example.recipebook.data.RecipeApi
import com.example.recipebook.data.RecipeListItem
import com.example.recipebook.data.RecipeRepository
import com.example.recipebook.data.RecipeRepository.OnResult
import com.example.recipebook.data.RetrofitClient
import com.example.recipebook.ui.Adapter
import com.example.recipebook.ui.fav.Favorite_List
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.textfield.TextInputEditText


class RecipeListActivity : AppCompatActivity(), Adapter.OnItemClickListener {
    lateinit var api: RecipeApi
    lateinit var listAdapter: Adapter
    lateinit var progress: CircularProgressIndicator            //progress 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        supportActionBar?.title = "Home"   // ActionBar name

        listAdapter = Adapter(this, this)
        val retrofit = RetrofitClient.getInstance()

        api = retrofit.create(RecipeApi::class.java)

        progress = findViewById(R.id.progress)              //progress 2
        val input = findViewById<TextInputEditText>(R.id.search)
        findViewById<MaterialButton>(R.id.search_button).setOnClickListener {
            val string = input.text.toString().trim()
            search(string)


        }

        val listView = findViewById<RecyclerView>(R.id.search_results)
        listView.adapter = listAdapter
        listView.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.home_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.home_list) {
            val intent = Intent(this@RecipeListActivity, Favorite_List::class.java)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }



    private fun search(string: String) {
        Log.d("LIST", string)
        progress.visibility = View.VISIBLE  //progress  3
        RecipeRepository().getResults(string, object : OnResult {
            override fun onResult(recipeListItems: MutableList<RecipeListItem>?) {
                Toast.makeText(applicationContext, "Success", Toast.LENGTH_SHORT).show()
                listAdapter.setRecipeListItems(recipeListItems)
                progress.visibility = View.GONE   //progress    4
            }

            override fun onError(errorMessage: String?) {
                Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_LONG).show();
                progress.visibility = View.GONE       //progress    5
            }
        })
    }

    override fun onClick(item: RecipeListItem?) {

        val intent = Intent(this@RecipeListActivity, RecipeDetailsActivity::class.java)
//        val message = messageEditText.text.toString()
        intent.putExtra("id", item?.id)
        println("list" + item?.id);
//        Toast.makeText(applicationContext, item?.id, Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

}