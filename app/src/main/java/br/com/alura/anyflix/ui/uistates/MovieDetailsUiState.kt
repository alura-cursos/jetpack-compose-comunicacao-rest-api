package br.com.alura.anyflix.ui.uistates

import br.com.alura.anyflix.model.Movie

sealed class MovieDetailsUiState {

    object Loading : MovieDetailsUiState()

    data class Success(
        val movie: Movie,
        val suggestedMovies: List<Movie> = emptyList()
    ) : MovieDetailsUiState()
}
