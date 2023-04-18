package br.com.alura.anyflix.repositories

import android.util.Log
import br.com.alura.anyflix.database.dao.MovieDao
import br.com.alura.anyflix.database.entities.toMovie
import br.com.alura.anyflix.model.Movie
import br.com.alura.anyflix.network.restapi.MovieRestApi
import br.com.alura.anyflix.network.responses.toMovieEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.net.ConnectException
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class MovieRepository @Inject constructor(
    private val dao: MovieDao,
    private val restApi: MovieRestApi
) {

    suspend fun findSections(): Flow<Map<String, List<Movie>>> {

        CoroutineScope(coroutineContext).launch {
            try {
                val response = restApi.findAll()
                val entities = response.map { it.toMovieEntity() }
                dao.saveAll(*entities.toTypedArray())
            } catch (e: ConnectException) {
                Log.e("MovieRepository", "findSections: falha ao conectar na API", e)
            }
        }

        return dao.findAll()
            .map { entities ->
                val movies = entities.map { it.toMovie() }
                if (movies.isEmpty()) {
                    emptyMap()
                } else {
                    createSections(movies)
                }
            }
    }

    private fun createSections(movies: List<Movie>) = mapOf(
        "Em alta" to movies.shuffled().take(7),
        "Novidades" to movies.shuffled().take(7),
        "Continue assistindo" to movies.shuffled().take(7)
    )

    suspend fun myList(): Flow<List<Movie>> {

        CoroutineScope(coroutineContext).launch {
            try {
                val response = restApi.myList()
                val entities = response.map { it.toMovieEntity() }
                dao.saveAll(*entities.toTypedArray())
            } catch (e: ConnectException) {
                Log.e("MovieRepository", "myList: falha ao conectar na API", e)
            }
        }

        return dao.myList()
            .map { entities -> entities.map { it.toMovie() } }
    }

    suspend fun findMovieById(id: String): Flow<Movie> {

        CoroutineScope(coroutineContext).launch {
            try {
                val response = restApi.findMovieById(id)
                val entity = response.toMovieEntity()
                dao.save(entity)
            } catch (e: ConnectException) {
                Log.e("MovieRepository", "findMovieById: falha ao conectar na API", e)
            }
        }

        return dao.findMovieById(id)
            .map {
                it.toMovie()
            }
    }

    fun suggestedMovies(id: String): Flow<List<Movie>> {
        return dao.suggestedMovies(id)
    }

    suspend fun removeFromMyList(id: String) {
        CoroutineScope(coroutineContext).launch {
            try {
                restApi.removeFromMyList(id)
                dao.removeFromMyList(id)
            } catch (e: ConnectException) {
                Log.e("MovieRepository", "removeFromMyList: falha ao conectar na API", e)
            }
        }
    }

    suspend fun addToMyList(id: String) {
        CoroutineScope(coroutineContext).launch {
            try {
                restApi.addToMyList(id)
                dao.addToMyList(id)
            } catch (e: ConnectException) {
                Log.e("MovieRepository", "addToMyList: falha ao conectar na API", e)
            }
        }
    }

}
