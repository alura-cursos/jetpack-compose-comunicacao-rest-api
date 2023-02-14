package br.com.alura.anyflix.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import br.com.alura.anyflix.model.Movie
import br.com.alura.anyflix.ui.screens.MovieDetailsScreen
import br.com.alura.anyflix.ui.viewmodels.MovieDetailsViewModel
import kotlinx.coroutines.launch

internal const val movieDetailsRoute = "movieDetails"
internal const val movieIdArgument = "movieId"
internal const val movieDetailsRouteFullpath = "$movieDetailsRoute/{$movieIdArgument}"

fun NavGraphBuilder.movieDetailsScreen(
    onNavigateToMovieDetails: (Movie) -> Unit,
    onPopBackStack: () -> Unit,
) {
    composable(movieDetailsRouteFullpath) { backStackEntry ->
        backStackEntry.arguments?.getString(movieIdArgument)?.let {
            val viewModel = hiltViewModel<MovieDetailsViewModel>()
            val uiState by viewModel.uiState.collectAsState()
            val scope = rememberCoroutineScope()
            MovieDetailsScreen(
                uiState = uiState,
                onMovieClick = onNavigateToMovieDetails,
                onAddToMyListClick = {
                    scope.launch {
                        viewModel.addToMyList(it)
                    }
                },
                onRemoveFromMyList = {
                    scope.launch {
                        viewModel.removeFromMyList(it)
                    }
                },
                onRetryLoadMovie = {
                    viewModel.loadMovie()
                }
            )
        } ?: LaunchedEffect(null) {
            onPopBackStack()
        }
    }
}

fun NavController.navigateToMovieDetails(
    id: String,
    navOptions: NavOptions? = null
) {
    navigate("$movieDetailsRoute/$id", navOptions)
}