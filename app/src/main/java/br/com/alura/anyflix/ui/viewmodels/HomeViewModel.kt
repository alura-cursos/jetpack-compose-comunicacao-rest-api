package br.com.alura.anyflix.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.alura.anyflix.database.dao.MovieDao
import br.com.alura.anyflix.database.entities.toMovie
import br.com.alura.anyflix.model.Movie
import br.com.alura.anyflix.ui.uistates.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dao: MovieDao
) : ViewModel() {

    private var currentUiStateJob: Job? = null
    private val _uiState = MutableStateFlow<HomeUiState>(
        HomeUiState.Loading
    )
    val uiState = _uiState.asStateFlow()

    init {
        loadUiState()
    }

    private fun loadUiState() {
        currentUiStateJob?.cancel()
        currentUiStateJob = viewModelScope.launch {
            dao.findAll()
                .onStart {
                    _uiState.update { HomeUiState.Loading }
                }
                .map { entities ->
                    val movies = entities.map { it.toMovie() }
                    if (movies.isEmpty()) {
                        emptyMap()
                    } else {
                        createSections(movies)
                    }
                }.collectLatest { sections ->
                    if (sections.isEmpty()) {
                        _uiState.update {
                            HomeUiState.Empty
                        }
                    } else {
                        val movie = sections
                            .entries.random()
                            .value.random()
                        _uiState.update {
                            HomeUiState.Success(
                                sections = sections,
                                mainBannerMovie = movie
                            )
                        }
                    }
                }
        }
    }

    fun loadSections() {
        loadUiState()
    }

    private fun createSections(movies: List<Movie>) = mapOf(
        "Em alta" to movies.shuffled().take(7),
        "Novidades" to movies.shuffled().take(7),
        "Continue assistindo" to movies.shuffled().take(7)
    )

}