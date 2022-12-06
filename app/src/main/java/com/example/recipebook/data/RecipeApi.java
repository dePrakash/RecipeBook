package com.example.recipebook.data;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RecipeApi {

    @Headers("x-api-key:e59c62d1750849e0a96eaa7668836bc3")
    @GET("complexSearch")
    Call<JsonObject> getResults(@Query("query") String value);

    @Headers("x-api-key:e59c62d1750849e0a96eaa7668836bc3")
    @GET("{id}/information")
    Call<RecipeDetails> getDetails(@Path("id") String id);

}
