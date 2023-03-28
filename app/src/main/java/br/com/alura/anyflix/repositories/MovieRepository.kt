package br.com.alura.anyflix.repositories

import br.com.alura.anyflix.database.dao.MovieDao
import br.com.alura.anyflix.model.Movie
import br.com.alura.anyflix.network.services.MovieService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val dao: MovieDao,
    private val service: MovieService
) {
    fun findAll(): Flow<List<Movie>> {
        TODO("Not yet implemented")
    }

}
