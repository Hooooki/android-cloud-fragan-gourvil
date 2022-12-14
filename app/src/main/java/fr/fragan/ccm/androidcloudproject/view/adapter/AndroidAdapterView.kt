package fr.fragan.ccm.androidcloudproject.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.fragan.ccm.androidcloudproject.R
import fr.fragan.ccm.androidcloudproject.databinding.FilmListItemFooterRecyclerBinding
import fr.fragan.ccm.androidcloudproject.databinding.FilmListItemHeaderRecyclerBinding
import fr.fragan.ccm.androidcloudproject.model.FilmDataSample
import fr.fragan.ccm.androidcloudproject.databinding.FilmListItemRecyclerBinding
import fr.fragan.ccm.androidcloudproject.model.FilmDataFooterSample
import fr.fragan.ccm.androidcloudproject.model.FilmDataHeaderSample
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
    private val onItemClick: (quoteUi: FilmDataSample, view: View) -> Unit,
) :
    ListAdapter<SealedRecyclerViewItem, RecyclerView.ViewHolder>(diffItemUtils) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =

        when (viewType) {
            MyItemType.ROW.type -> {
                AndroidVersionViewHolder(
                    FilmListItemRecyclerBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ), parent, false
                    ),
                    onItemClick
                )
            }
            MyItemType.FOOTER.type -> {
                AndroidVersionFooterViewHolder(
                    FilmListItemFooterRecyclerBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            MyItemType.HEADER.type -> {
                AndroidVersionHeaderViewHolder(
                    FilmListItemHeaderRecyclerBinding.inflate(
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
            MyItemType.ROW.type -> (holder as AndroidVersionViewHolder).bind(getItem(position) as FilmDataSample)
            MyItemType.HEADER.type -> (holder as AndroidVersionHeaderViewHolder).bind(
                getItem(
                    position
                ) as FilmDataHeaderSample
            )
            MyItemType.FOOTER.type -> (holder as AndroidVersionFooterViewHolder).bind(
                getItem(
                    position
                ) as FilmDataFooterSample
            )
            else -> throw RuntimeException("Wrong view type received ${holder.itemView}")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is FilmDataSample -> MyItemType.ROW.type
            is FilmDataHeaderSample -> MyItemType.HEADER.type
            is FilmDataFooterSample -> MyItemType.FOOTER.type
        }
    }

}

class AndroidVersionViewHolder(
    private val binding: FilmListItemRecyclerBinding,
    onItemClick: (FilmDataSample: FilmDataSample, view: View) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var ui: FilmDataSample

    init {
        binding.root.setOnClickListener {
            onItemClick(ui, itemView)
        }
    }

    fun bind(FilmDataSample: FilmDataSample) {
        ui = FilmDataSample
        binding.filmItemLabel.text = FilmDataSample.title
        binding.filmItemVoteCount.text = FilmDataSample.voteCount.toString().plus(" Votes")
        binding.filmItemOverview.text = FilmDataSample.overview
        binding.filmRating.rating = (FilmDataSample.voteAverage / 2).toFloat()

        Glide.with(itemView.context)
            .load("https://image.tmdb.org/t/p/w500".plus(FilmDataSample.poster))
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.filmItemImage)
    }
}

class AndroidVersionHeaderViewHolder(
    private val binding: FilmListItemHeaderRecyclerBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(objectDataHeaderSample: FilmDataHeaderSample) {
        binding.filmItemHeader.text = objectDataHeaderSample.label
    }

}

class AndroidVersionFooterViewHolder(
    private val binding: FilmListItemFooterRecyclerBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(objectDataFooterSample: FilmDataFooterSample) {
        binding.filmItemFooterLabel.text = objectDataFooterSample.label
        binding.filmItemFooterCount.text =
            objectDataFooterSample.count.toString().plus(" Films")
    }
}


enum class MyItemType(val type: Int) {
    ROW(0),
    HEADER(1),
    FOOTER(2)
}
