package br.com.alura.anyflix.network.services

import br.com.alura.anyflix.database.entities.MovieEntity
import br.com.alura.anyflix.model.Movie
import retrofit2.http.GET

data class MovieResponse(
    val id: String,
    val title: String,
    val image: String,
    val year: Int,
    val plot: String,
    val inMyList: Boolean
) {
}

fun MovieResponse.toMovieEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        title = title,
        image = image,
        year = year,
        plot = plot,
        inMyList = inMyList
    )
}

fun MovieResponse.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        image = image,
        year = year,
        plot = plot,
        inMyList = inMyList
    )
}

interface MovieService {

    @GET("movies")
    suspend fun findAll(): List<MovieResponse>

}