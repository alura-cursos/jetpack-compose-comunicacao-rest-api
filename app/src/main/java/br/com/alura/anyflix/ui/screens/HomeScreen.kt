package br.com.alura.anyflix.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.anyflix.model.Movie
import br.com.alura.anyflix.sampleData.sampleMovieSections
import br.com.alura.anyflix.sampleData.sampleMovies
import br.com.alura.anyflix.ui.components.AnyflixMainBanner
import br.com.alura.anyflix.ui.theme.AnyFlixTheme
import br.com.alura.anyflix.ui.uistates.HomeUiState
import coil.compose.AsyncImage

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onMovieClick: (Movie) -> Unit,
    onRetryLoadSections: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (uiState) {
        HomeUiState.Loading -> {
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    Modifier.align(Alignment.Center)
                )
            }
        }
        is HomeUiState.Success -> {
            val sections = uiState.sections
            Box(modifier) {
                LazyColumn {
                    item {
                        uiState.mainBannerMovie?.let { movie ->
                            AnyflixMainBanner(
                                movie = movie,
                                Modifier.height(300.dp),
                                onMovieClick = onMovieClick,
                            )
                        }
                    }
                    sections.forEach {
                        val title = it.key
                        val movies = it.value
                        item {
                            Column {
                                Text(
                                    text = title,
                                    Modifier.padding(
                                        horizontal = 16.dp,
                                        vertical = 8.dp
                                    ),
                                    style = TextStyle.Default.copy(
                                        fontSize = MaterialTheme.typography.titleMedium.fontSize
                                    )
                                )
                                LazyRow(
                                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                                    contentPadding = PaddingValues(
                                        horizontal = 16.dp,
                                        vertical = 8.dp
                                    )
                                ) {
                                    items(movies) { movie ->
                                        AsyncImage(
                                            model = movie.image,
                                            contentDescription = null,
                                            Modifier
                                                .width(150.dp)
                                                .height(200.dp)
                                                .clip(RoundedCornerShape(8.dp))
                                                .clickable {
                                                    onMovieClick(movie)
                                                },
                                            placeholder = ColorPainter(Color.Gray),
                                            contentScale = ContentScale.Crop
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }

        HomeUiState.Empty -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Nenhuma seção encontrada",
                    style = TextStyle.Default.copy(
                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                    )
                )
                TextButton(onClick = { onRetryLoadSections() }) {
                    Text(text = "Tentar buscar novamente")
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    AnyFlixTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            HomeScreen(
                uiState = HomeUiState.Success(
                    sections = sampleMovieSections,
                    mainBannerMovie = sampleMovies.random()
                ),
                onRetryLoadSections = {},
                onMovieClick = {}
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenWithoutSections() {
    AnyFlixTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            HomeScreen(
                uiState = HomeUiState.Empty,
                onRetryLoadSections = {},
                onMovieClick = {}
            )
        }
    }
}