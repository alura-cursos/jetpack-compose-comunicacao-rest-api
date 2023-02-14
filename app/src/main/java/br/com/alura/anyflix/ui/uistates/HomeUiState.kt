package br.com.alura.anyflix.ui.uistates

import br.com.alura.anyflix.model.Movie

sealed class HomeUiState {

    object Loading : HomeUiState()

    object Empty : HomeUiState()

    data class Success(
        val sections: Map<String, List<Movie>> = emptyMap(),
        val mainBannerMovie: Movie? = null
    ) : HomeUiState()

}