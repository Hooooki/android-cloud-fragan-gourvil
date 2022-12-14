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
import fr.fragan.ccm.androidcloudproject.databinding.ActivityListFilmBinding
import fr.fragan.ccm.androidcloudproject.model.FilmDataFooterSample
import fr.fragan.ccm.androidcloudproject.model.FilmDataHeaderSample
import fr.fragan.ccm.androidcloudproject.model.FilmDataSample
import fr.fragan.ccm.androidcloudproject.model.SealedRecyclerViewItem
import fr.fragan.ccm.androidcloudproject.viewmodel.AndroidVersionViewModel

class FilmListActivity : AppCompatActivity() {

    private lateinit var adapter: AndroidVersionAdapter
    private lateinit var binding: ActivityListFilmBinding
    private lateinit var viewModel: AndroidVersionViewModel

    private val androidVersionListObserver = Observer<List<SealedRecyclerViewItem>> {
        adapter.submitList(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[AndroidVersionViewModel::class.java]

        adapter = AndroidVersionAdapter { item, view ->
            onItemClick(item, view)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        viewModel.androidVersionList.observe(this, androidVersionListObserver)
    }


    override fun onStop() {
        super.onStop()
        viewModel.androidVersionList.observe(this, androidVersionListObserver)
    }

    private fun onItemClick(filmDataSample: FilmDataSample, view : View) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
        Toast.makeText(this, "Vous avez aimé le film ".plus(filmDataSample.title), Toast.LENGTH_LONG).show()
    }

    private fun generateFakeData(): MutableList<SealedRecyclerViewItem> {
        val result = mutableListOf<SealedRecyclerViewItem>()
        // Create data raw
        mutableListOf(
            FilmDataSample(
                "The Godfather",
                17053,
                "/3bhkrj58Vtu7enYsRolD1fZdja1.jpg",
                8.7,
                "Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family. When organized crime family patriarch, Vito Corleone barely survives an attempt on his life, his youngest son, Michael steps in to take care of the would-be killers, launching a campaign of bloody revenge."
            )
        ).groupBy {
            it.title
        }.forEach { (brand, items) ->
            result.add(FilmDataHeaderSample(brand))
            result.addAll(items)
            result.add(FilmDataFooterSample(brand, items.count()))
        }
        return result
    }

}