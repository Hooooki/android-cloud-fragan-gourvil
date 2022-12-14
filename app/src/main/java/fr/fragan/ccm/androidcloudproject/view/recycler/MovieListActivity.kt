package fr.fragan.ccm.androidcloudproject.view.recycler

import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.fragan.ccm.androidcloudproject.view.adapter.AndroidVersionAdapter
import fr.fragan.ccm.androidcloudproject.databinding.ActivityListMovieBinding
import fr.fragan.ccm.androidcloudproject.model.MovieDataSample
import fr.fragan.ccm.androidcloudproject.model.SealedRecyclerViewItem
import fr.fragan.ccm.androidcloudproject.viewmodel.MovieViewModel
import kotlin.random.Random

class MovieListActivity : AppCompatActivity() {

    private lateinit var adapter: AndroidVersionAdapter
    private lateinit var binding: ActivityListMovieBinding
    private lateinit var viewModel: MovieViewModel

    private val androidVersionListObserver = Observer<List<SealedRecyclerViewItem>> {
        adapter.submitList(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]

        adapter = AndroidVersionAdapter { item, view ->
            onItemClick(item, view)
        }

        binding.addMovieButton.setOnClickListener { addRandomMovie() }
        binding.deleteButtonMovie.setOnClickListener { viewModel.deleteAllMovie() }
        binding.reloadButtonMovie.setOnClickListener { viewModel.fetchMovies() }

        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = adapter

        viewModel.fetchMovies()

    }

    private fun addRandomMovie() {
        val random = Random.nextInt(1, 1000)
        viewModel.fetchOne(random)
    }

    override fun onStart() {
        super.onStart()
        viewModel.movieList.observe(this, androidVersionListObserver)
    }


    override fun onStop() {
        super.onStop()
        viewModel.movieList.removeObserver(androidVersionListObserver)
    }

    private fun onItemClick(movieDataSample: MovieDataSample, view : View) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
        Toast.makeText(this, "Vous avez aimé le film ".plus(movieDataSample.title), Toast.LENGTH_LONG).show()
    }

}