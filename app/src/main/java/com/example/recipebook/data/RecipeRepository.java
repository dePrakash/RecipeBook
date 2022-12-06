package com.example.recipebook.data;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeRepository {

    RecipeApi api = RetrofitClient.getInstance().create(RecipeApi.class);

    public void getResults(String str, OnResult callback) {
        api.getResults(str).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject jsonObject = response.body();
                    JsonArray array = jsonObject.getAsJsonArray("results");
                    List<RecipeListItem> items = new Gson().fromJson(array, new TypeToken<List<RecipeListItem>>() {
                    }.getType());
                    callback.onResult(items);
                } else {
                    callback.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    public void getDetails(String str, OnDetailResult callback) {
        api.getDetails(str).enqueue(new Callback<RecipeDetails>() {
            @Override
            public void onResponse(Call<RecipeDetails> call, Response<RecipeDetails> response) {
                System.out.println("testing function");
                if (response.isSuccessful()) {
                    RecipeDetails jsonObject = response.body();
                    callback.onResult(jsonObject);
                } else {
                    callback.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<RecipeDetails> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    public interface OnResult {
        void onResult(List<RecipeListItem> recipeListItems);

        void onError(String errorMessage);
    }


    public interface OnDetailResult {
        void onResult(RecipeDetails details);

        void onError(String errorMessage);
    }
}
