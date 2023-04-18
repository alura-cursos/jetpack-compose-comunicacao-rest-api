package br.com.alura.anyflix.network.responses

import br.com.alura.anyflix.database.entities.MovieEntity
import br.com.alura.anyflix.model.Movie
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    val id: String,
    val title: String,
    val image: String,
    val year: Int,
    val plot: String,
    val inMyList: Boolean
)

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