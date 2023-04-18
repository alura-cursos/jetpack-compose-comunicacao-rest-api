package br.com.alura.anyflix.network.restapi

import br.com.alura.anyflix.network.restapi.MoviesRoutes.ADD_TO_MY_LIST
import br.com.alura.anyflix.network.restapi.MoviesRoutes.MOVIES
import br.com.alura.anyflix.network.restapi.MoviesRoutes.MY_LIST
import br.com.alura.anyflix.network.restapi.MoviesRoutes.REMOVE_FROM_MY_LIST
import br.com.alura.anyflix.network.responses.MovieResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.put
import javax.inject.Inject
import javax.inject.Singleton

object MoviesRoutes {
    private const val BASE_URL = "http://192.168.5.147:8080"
    const val MOVIES = "$BASE_URL/movies"
    const val MY_LIST = "$BASE_URL/movies/myList"
    const val REMOVE_FROM_MY_LIST = "$BASE_URL/movies/removeFromMyList"
    const val ADD_TO_MY_LIST = "$BASE_URL/movies/addToMyList"
}

@Singleton
class MovieRestApi @Inject constructor(
    private val client: HttpClient
) {

    suspend fun findAll(): List<MovieResponse> =
        client.get(MOVIES).body()

    suspend fun myList(): List<MovieResponse> =
        client.get(MY_LIST).body()

    suspend fun findMovieById(id: String): MovieResponse =
        client.get("$MOVIES/$id").body()

    suspend fun removeFromMyList(id: String) {
        client.put("$REMOVE_FROM_MY_LIST/$id")
    }

    suspend fun addToMyList(id: String) {
        client.put("$ADD_TO_MY_LIST/$id")
    }

}