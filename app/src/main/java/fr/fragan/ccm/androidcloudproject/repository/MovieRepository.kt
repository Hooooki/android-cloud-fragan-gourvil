package fr.fragan.ccm.androidcloudproject.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import fr.fragan.ccm.androidcloudproject.architecture.CustomApplication
import fr.fragan.ccm.androidcloudproject.architecture.RetrofitBuilder
import fr.fragan.ccm.androidcloudproject.mdb.model.MdbMovieRetrofit
import fr.fragan.ccm.androidcloudproject.model.*

class MovieRepository {

    private val mMovieDao =
        CustomApplication.instance.mApplicationDatabase.movieDao()

    fun selectAllMovie(): LiveData<List<MovieDataSample>> {
        return mMovieDao.selectAll().map { list ->
            list.toMovieDataSample()
        }
    }

    fun insertMovie(movieDataSample: MovieDataSample) {
        mMovieDao.insert(movieDataSample.toRoomObject())
    }

    fun insertManyMovie(movies: List<MovieDataSample>) {
        mMovieDao.insertMany(movies.toMovieDataSourceSample())
    }

    fun deleteAllMovie() {
        mMovieDao.deleteAll()
    }

    suspend fun fetchData(page: Int) {
        insertManyMovie(RetrofitBuilder.getMovie().getMovieTopRated("36ef3824b96b6a5a711fb1e0409a912e", "en-US", page).results.map { item ->
            item.toRoom()
        })
    }

    suspend fun fetchOne(movieId: Int) {
        insertMovie(RetrofitBuilder.getMovie().getMovieById(movieId,"36ef3824b96b6a5a711fb1e0409a912e", "en-US").toRoom())
    }

    private fun MdbMovieRetrofit.toRoom(): MovieDataSample {
        return MovieDataSample(
            title = title,
            poster = poster,
            voteCount = voteCount,
            voteAverage = voteAverage,
            overview = overview,
            originalLanguage = originalLanguage
        )
    }


    private fun MovieDataSample.toRoomObject(): MovieDataSourceSample {
        return MovieDataSourceSample(
            title = title,
            poster = poster,
            voteCount = voteCount,
            voteAverage = voteAverage,
            overview = overview,
            originalLanguage = originalLanguage
        )
    }


    private fun List<MovieDataSourceSample>.toMovieDataSample(): List<MovieDataSample> {
        return map { item ->
            MovieDataSample(
                title = item.title,
                poster = item.poster,
                voteCount = item.voteCount,
                voteAverage = item.voteAverage,
                overview = item.overview,
                originalLanguage = item.originalLanguage
            )
        }
    }

    private fun List<MovieDataSample>.toMovieDataSourceSample(): List<MovieDataSourceSample> {
        return map { item ->
            MovieDataSourceSample(
                title = item.title,
                poster = item.poster,
                voteCount = item.voteCount,
                voteAverage = item.voteAverage,
                overview = item.overview,
                originalLanguage = item.originalLanguage
            )
        }
    }


}