package fr.fragan.ccm.androidcloudproject.mdb.remote

import fr.fragan.ccm.androidcloudproject.mdb.model.MdbMovieRetrofit
import fr.fragan.ccm.androidcloudproject.mdb.model.MdbMovieRetrofitResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MdbMovieEndpoint {

    @GET("movie/top_rated")
    suspend fun getMovieTopRated(@Query("api_key") apiKey: String, @Query("language") language: String, @Query("page") page: Int) : MdbMovieRetrofitResponse

    @GET("movie/{id}")
    suspend fun getMovieById(@Path("id") movieId: Int, @Query("api_key") apiKey: String, @Query("language") language: String) : MdbMovieRetrofit

}