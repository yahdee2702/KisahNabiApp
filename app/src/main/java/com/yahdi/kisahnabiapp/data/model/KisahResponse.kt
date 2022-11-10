package com.yahdi.kisahnabiapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class KisahResponse(
    @SerializedName("image_url")
    val thumb: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("usia")
    val age: String,

    @SerializedName("thn_kelahiran")
    val birthYear: String,

    @SerializedName("tmp")
    val origin: String,

    @SerializedName("description")
    val content: String,
): Parcelable
