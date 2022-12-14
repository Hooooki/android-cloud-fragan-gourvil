package fr.fragan.ccm.androidcloudproject.architecture

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.fragan.ccm.androidcloudproject.dao.MovieDao
import fr.fragan.ccm.androidcloudproject.model.MovieDataSourceSample

@Database(
    entities = [
        MovieDataSourceSample::class
    ],
    version = 1,
    exportSchema = false
)
abstract class CustomRoomDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}
