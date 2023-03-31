package br.com.alura.anyflix.network.services

import br.com.alura.anyflix.database.entities.MovieEntity
import br.com.alura.anyflix.model.Movie
import br.com.alura.anyflix.repositories.MovieRepository
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

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

    @GET("movies/myList")
    suspend fun myList(): List<MovieResponse>

    @GET("movies/{id}")
    suspend fun findMovieById(@Path("id") id: String): MovieResponse

    @PUT("movies/removeFromMyList/{id}")
    suspend fun removeFromMyList(@Path("id") id: String): Response<Void>

    @PUT("movies/addToMyList/{id}")
    suspend fun addToMyList(@Path("id") id: String): Response<Void>

}