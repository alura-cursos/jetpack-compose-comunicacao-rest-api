package br.com.alura.anyflix.ui.uistates

import br.com.alura.anyflix.model.Movie


sealed class MyListUiState {

    object Loading : MyListUiState()

    object Empty : MyListUiState()

    data class Success(
        val movies: List<Movie> = emptyList()
    ) : MyListUiState()

}
