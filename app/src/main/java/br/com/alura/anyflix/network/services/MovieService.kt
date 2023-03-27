package br.com.alura.anyflix.network.services

import retrofit2.http.GET

data class MovieResponse(
    val id: String,
    val title: String,
    val image: String,
    val year: Int,
    val plot: String,
    val inMyList: Boolean
)

interface MovieService {

    @GET("movies")
    suspend fun findAll(): List<MovieResponse>

}