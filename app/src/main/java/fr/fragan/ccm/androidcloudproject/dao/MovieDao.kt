package fr.fragan.ccm.androidcloudproject.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.fragan.ccm.androidcloudproject.model.MovieDataSourceSample

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_object_table ORDER BY title ASC")
    fun selectAll(): LiveData<List<MovieDataSourceSample>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieDataSourceSample: MovieDataSourceSample)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMany(movies: List<MovieDataSourceSample>)

    @Query("DELETE FROM movie_object_table")
    fun deleteAll()

}