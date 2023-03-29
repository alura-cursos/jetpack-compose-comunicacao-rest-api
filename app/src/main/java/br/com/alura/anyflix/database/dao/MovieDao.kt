package br.com.alura.anyflix.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.alura.anyflix.database.entities.MovieEntity
import br.com.alura.anyflix.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(movie: MovieEntity)

    @Query("SELECT * FROM movies")
    fun findAll(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE inMyList = 1")
    fun myList(): Flow<List<MovieEntity>>

    @Query("UPDATE movies SET inMyList = 0 WHERE id = :id")
    suspend fun removeFromMyList(id: String)

    @Query("UPDATE movies SET inMyList = 1 WHERE id = :id")
    suspend fun addToMyList(id: String)

    @Query("SELECT * FROM movies WHERE id = :id")
    fun findMovieById(id: String): Flow<MovieEntity>

    @Query("SELECT * FROM movies WHERE inMyList = 0 and id != :id")
    fun suggestedMovies(id: String): Flow<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(vararg entities: MovieEntity)

}