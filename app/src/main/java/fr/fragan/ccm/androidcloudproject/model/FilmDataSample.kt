package fr.fragan.ccm.androidcloudproject.model

sealed class SealedRecyclerViewItem

data class FilmDataHeaderSample(
    val label: String
): SealedRecyclerViewItem()

data class FilmDataFooterSample(
    val label: String,
    val count: Int
): SealedRecyclerViewItem()

data class FilmDataSample(
    val title: String,
    val voteCount: Int,
    val poster: String,
    val voteAverage: Double,
    val overview: String
): SealedRecyclerViewItem()