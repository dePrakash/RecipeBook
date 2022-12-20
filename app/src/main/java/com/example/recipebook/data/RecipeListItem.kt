package com.example.recipebook.data


import com.google.gson.annotations.SerializedName

data class RecipeListItem(
    @SerializedName("id")
    val id: String?,
    @SerializedName("sourceName")
    val sourceName: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("imageType")
    val imageType: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("instructions")
    val instructions: String?


)