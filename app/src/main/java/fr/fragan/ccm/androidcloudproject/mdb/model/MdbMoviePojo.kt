package fr.fragan.ccm.androidcloudproject.mdb.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MdbMovieRetrofit(
    @Expose
    @SerializedName("title")
    val title: String,

    @Expose
    @SerializedName("overview")
    val overview: String,

    @Expose
    @SerializedName("poster_path")
    val poster: String,

    @Expose
    @SerializedName("vote_count")
    val voteCount: Int,

    @Expose
    @SerializedName("vote_average")
    val voteAverage: Double,

    @Expose
    @SerializedName("original_language")
    val originalLanguage: String,
)

data class MdbMovieRetrofitResponse(
    @Expose
    @SerializedName("page")
    val page: Int,

    @Expose
    @SerializedName("results")
    val results: List<MdbMovieRetrofit>,
)
