package com.example.mobile_gl.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MainResponse(
    @SerializedName("title") var title: String,
    @SerializedName("description") var description: String,
    @SerializedName("image") var image: String
) : Serializable
