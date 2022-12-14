package fr.fragan.ccm.androidcloudproject.viewmodel

import androidx.lifecycle.*
import fr.fragan.ccm.androidcloudproject.model.MovieDataFooterSample
import fr.fragan.ccm.androidcloudproject.model.MovieDataHeaderSample
import fr.fragan.ccm.androidcloudproject.model.MovieDataSample
import fr.fragan.ccm.androidcloudproject.model.SealedRecyclerViewItem
import fr.fragan.ccm.androidcloudproject.repository.MovieRepository
import fr.fragan.ccm.androidcloudproject.view.recycler.MovieListActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    private val movieRepository: MovieRepository by lazy { MovieRepository() }
    val movieList: LiveData<List<SealedRecyclerViewItem>> = movieRepository.selectAllMovie().map { list ->
        list.toSealedRecyclerViewItem()
    }

    fun fetchMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.fetchData(1)
            movieRepository.fetchData(2)
            movieRepository.fetchData(3)
        }
    }

    fun fetchOne(moveId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.fetchOne(moveId)
        }
    }

    fun insertMovie(title: String, voteCount: Int, poster: String, voteAverage: Double, overview: String, originalLanguage: String) {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.insertMovie(
                MovieDataSample(title, voteCount, poster, voteAverage, overview, originalLanguage)
            )
        }
    }

    fun deleteAllMovie() {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.deleteAllMovie()
        }
    }

    private fun List<MovieDataSample>.toSealedRecyclerViewItem(): List<SealedRecyclerViewItem> {
        val result = mutableListOf<SealedRecyclerViewItem>()

        groupBy {
            it.originalLanguage
        }.forEach { (lang, items) ->

            var voteTotal = 0

            items.forEach{ (title, voteCount) -> voteTotal += voteCount }

            result.add(MovieDataHeaderSample(lang))
            result.addAll(items)
            result.add(MovieDataFooterSample("Nombre de votes total",  voteTotal))
        }

        return result
    }


}