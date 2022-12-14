package fr.fragan.ccm.androidcloudproject.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.fragan.ccm.androidcloudproject.R
import fr.fragan.ccm.androidcloudproject.databinding.MovieListItemFooterRecyclerBinding
import fr.fragan.ccm.androidcloudproject.databinding.MovieListItemHeaderRecyclerBinding
import fr.fragan.ccm.androidcloudproject.model.MovieDataSample
import fr.fragan.ccm.androidcloudproject.databinding.MovieListItemRecyclerBinding
import fr.fragan.ccm.androidcloudproject.model.MovieDataFooterSample
import fr.fragan.ccm.androidcloudproject.model.MovieDataHeaderSample
import fr.fragan.ccm.androidcloudproject.model.SealedRecyclerViewItem

private val diffItemUtils = object : DiffUtil.ItemCallback<SealedRecyclerViewItem>() {

    override fun areItemsTheSame(
        oldItem: SealedRecyclerViewItem,
        newItem: SealedRecyclerViewItem
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: SealedRecyclerViewItem,
        newItem: SealedRecyclerViewItem
    ): Boolean {
        return oldItem == newItem
    }
}

class AndroidVersionAdapter(
    private val onItemClick: (quoteUi: MovieDataSample, view: View) -> Unit,
) :
    ListAdapter<SealedRecyclerViewItem, RecyclerView.ViewHolder>(diffItemUtils) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =

        when (viewType) {
            MyItemType.ROW.type -> {
                AndroidVersionViewHolder(
                    MovieListItemRecyclerBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ), parent, false
                    ),
                    onItemClick
                )
            }
            MyItemType.FOOTER.type -> {
                AndroidVersionFooterViewHolder(
                    MovieListItemFooterRecyclerBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            MyItemType.HEADER.type -> {
                AndroidVersionHeaderViewHolder(
                    MovieListItemHeaderRecyclerBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> throw RuntimeException("Wrong view type received $viewType")
        }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            MyItemType.ROW.type -> (holder as AndroidVersionViewHolder).bind(getItem(position) as MovieDataSample)
            MyItemType.HEADER.type -> (holder as AndroidVersionHeaderViewHolder).bind(
                getItem(
                    position
                ) as MovieDataHeaderSample
            )
            MyItemType.FOOTER.type -> (holder as AndroidVersionFooterViewHolder).bind(
                getItem(
                    position
                ) as MovieDataFooterSample
            )
            else -> throw RuntimeException("Wrong view type received ${holder.itemView}")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is MovieDataSample -> MyItemType.ROW.type
            is MovieDataHeaderSample -> MyItemType.HEADER.type
            is MovieDataFooterSample -> MyItemType.FOOTER.type
        }
    }

}

enum class Lang(val lang: String) {
    EN("English"),
    KO("Korean"),
    ES("Spanish"),
    PT("Portuguese"),
    JA("Japanese"),
    IT("Italian"),
    HI("Hindi"),
    ZH("Chinese")
}

class AndroidVersionViewHolder(
    private val binding: MovieListItemRecyclerBinding,
    onItemClick: (MovieDataSample: MovieDataSample, view: View) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var ui: MovieDataSample

    init {
        binding.root.setOnClickListener {
            onItemClick(ui, itemView)
        }
    }

    fun bind(MovieDataSample: MovieDataSample) {
        ui = MovieDataSample
        binding.movieItemLabel.text = MovieDataSample.title
        binding.movieItemVoteCount.text = MovieDataSample.voteCount.toString().plus(" Votes")
        binding.movieItemOverview.text = MovieDataSample.overview
        binding.movieRating.rating = (MovieDataSample.voteAverage / 2).toFloat()

        Glide.with(itemView.context)
            .load("https://image.tmdb.org/t/p/w500".plus(MovieDataSample.poster))
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.movieItemImage)
    }


}

class AndroidVersionHeaderViewHolder(
    private val binding: MovieListItemHeaderRecyclerBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(objectDataHeaderSample: MovieDataHeaderSample) {
        binding.movieItemHeader.text = parseLang(objectDataHeaderSample.label)
    }

    private fun parseLang(lang: String): String {
        return try {
            Lang.valueOf(lang.uppercase()).lang
        } catch (e: IllegalArgumentException) {
            "Unknown"
        }
    }

}

class AndroidVersionFooterViewHolder(
    private val binding: MovieListItemFooterRecyclerBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(objectDataFooterSample: MovieDataFooterSample) {
        binding.movieItemFooterLabel.text = objectDataFooterSample.label
        binding.movieItemFooterCount.text =
            objectDataFooterSample.count.toString()
    }
}


enum class MyItemType(val type: Int) {
    ROW(0),
    HEADER(1),
    FOOTER(2)
}
