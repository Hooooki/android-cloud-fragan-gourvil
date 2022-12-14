package fr.fragan.ccm.androidcloudproject.model

sealed class SealedRecyclerViewItem

data class MovieDataHeaderSample(
    val label: String
): SealedRecyclerViewItem()

data class MovieDataFooterSample(
    val label: String,
    val count: Int
): SealedRecyclerViewItem()

data class MovieDataSample(
    val title: String,
    val voteCount: Int,
    val poster: String,
    val voteAverage: Double,
    val overview: String,
    val originalLanguage: String
): SealedRecyclerViewItem()