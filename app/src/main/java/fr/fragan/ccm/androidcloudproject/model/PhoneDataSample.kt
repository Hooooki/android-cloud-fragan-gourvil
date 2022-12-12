package fr.fragan.ccm.androidcloudproject.model

sealed class SealedRecyclerViewItem

data class PhoneDataHeaderSample(
    val label: String
): SealedRecyclerViewItem()

data class PhoneDataFooterSample(
    val label: String,
    val count: Int
): SealedRecyclerViewItem()

data class PhoneDataSample(
    val label: String,
    val price: Double,
    val image: String,
    val brand: String
): SealedRecyclerViewItem()