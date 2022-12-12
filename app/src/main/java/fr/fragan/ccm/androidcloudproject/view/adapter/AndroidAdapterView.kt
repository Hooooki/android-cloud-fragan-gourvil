package fr.fragan.ccm.androidcloudproject.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.fragan.ccm.androidcloudproject.R
import fr.fragan.ccm.androidcloudproject.databinding.PhoneListItemFooterRecyclerBinding
import fr.fragan.ccm.androidcloudproject.databinding.PhoneListItemHeaderRecyclerBinding
import fr.fragan.ccm.androidcloudproject.model.PhoneDataSample
import fr.fragan.ccm.androidcloudproject.databinding.PhoneListItemRecyclerBinding
import fr.fragan.ccm.androidcloudproject.model.PhoneDataFooterSample
import fr.fragan.ccm.androidcloudproject.model.PhoneDataHeaderSample
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
    private val onItemClick: (quoteUi: PhoneDataSample, view: View) -> Unit,
) :
    ListAdapter<SealedRecyclerViewItem, RecyclerView.ViewHolder>(diffItemUtils) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =

        when (viewType) {
            MyItemType.ROW.type -> {
                AndroidVersionViewHolder(
                    PhoneListItemRecyclerBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ), parent, false
                    ),
                    onItemClick
                )
            }
            MyItemType.FOOTER.type -> {
                AndroidVersionFooterViewHolder(
                    PhoneListItemFooterRecyclerBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            MyItemType.HEADER.type -> {
                AndroidVersionHeaderViewHolder(
                    PhoneListItemHeaderRecyclerBinding.inflate(
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
            MyItemType.ROW.type -> (holder as AndroidVersionViewHolder).bind(getItem(position) as PhoneDataSample)
            MyItemType.HEADER.type -> (holder as AndroidVersionHeaderViewHolder).bind(
                getItem(
                    position
                ) as PhoneDataHeaderSample
            )
            MyItemType.FOOTER.type -> (holder as AndroidVersionFooterViewHolder).bind(
                getItem(
                    position
                ) as PhoneDataFooterSample
            )
            else -> throw RuntimeException("Wrong view type received ${holder.itemView}")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is PhoneDataSample -> MyItemType.ROW.type
            is PhoneDataHeaderSample -> MyItemType.HEADER.type
            is PhoneDataFooterSample -> MyItemType.FOOTER.type
        }
    }

}

class AndroidVersionViewHolder(
    private val binding: PhoneListItemRecyclerBinding,
    onItemClick: (phoneDataSample: PhoneDataSample, view: View) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var ui: PhoneDataSample

    init {
        binding.root.setOnClickListener {
            onItemClick(ui, itemView)
        }
    }

    fun bind(PhoneDataSample: PhoneDataSample) {
        ui = PhoneDataSample
        binding.phoneItemLabel.text = PhoneDataSample.label
        binding.phoneItemPrice.text = PhoneDataSample.price.toString().plus(" €")
        Glide.with(itemView.context).load(PhoneDataSample.image)
            .placeholder(R.drawable.ic_launcher_background).into(binding.phoneItemImage)
    }
}

class AndroidVersionHeaderViewHolder(
    private val binding: PhoneListItemHeaderRecyclerBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(objectDataHeaderSample: PhoneDataHeaderSample) {
        binding.phoneItemHeader.text = objectDataHeaderSample.label
    }

}

class AndroidVersionFooterViewHolder(
    private val binding: PhoneListItemFooterRecyclerBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(objectDataFooterSample: PhoneDataFooterSample) {
        binding.phoneItemFooterLabel.text = objectDataFooterSample.label
        binding.phoneItemFooterCount.text =
            objectDataFooterSample.count.toString().plus(" Produits dans cette catégorie")
    }
}


enum class MyItemType(val type: Int) {
    ROW(0),
    HEADER(1),
    FOOTER(2)
}
