package fr.fragan.ccm.androidcloudproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_object_table")
data class MovieDataSourceSample(
    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "poster")
    val poster: String,

    @ColumnInfo(name = "voteAverage")
    val voteAverage: Double,

    @ColumnInfo(name = "voteCount")
    val voteCount: Int,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "originalLanguage")
    val originalLanguage: String

) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
