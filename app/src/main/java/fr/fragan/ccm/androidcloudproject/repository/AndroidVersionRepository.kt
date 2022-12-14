package fr.fragan.ccm.androidcloudproject.repository

import fr.fragan.ccm.androidcloudproject.model.FilmDataFooterSample
import fr.fragan.ccm.androidcloudproject.model.FilmDataHeaderSample
import fr.fragan.ccm.androidcloudproject.model.FilmDataSample
import fr.fragan.ccm.androidcloudproject.model.SealedRecyclerViewItem

class AndroidVersionRepository {

    fun generateFakeData(): MutableList<SealedRecyclerViewItem> {
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