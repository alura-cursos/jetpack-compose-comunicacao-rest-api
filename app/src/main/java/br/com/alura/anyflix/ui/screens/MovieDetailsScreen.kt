package br.com.alura.anyflix.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlaylistAdd
import androidx.compose.material.icons.filled.PlaylistRemove
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.anyflix.model.Movie
import br.com.alura.anyflix.sampleData.sampleMovies
import br.com.alura.anyflix.ui.theme.AnyFlixTheme
import br.com.alura.anyflix.ui.uistates.MovieDetailsUiState
import coil.compose.AsyncImage

private class ToggleButton(
    val text: String,
    val icon: ImageVector,
    val action: (Movie) -> Unit,
    val buttonColor: Long
)

@Composable
fun MovieDetailsScreen(
    uiState: MovieDetailsUiState,
    onMovieClick: (Movie) -> Unit,
    onAddToMyListClick: (Movie) -> Unit,
    onRemoveFromMyList: (Movie) -> Unit,
    onRetryLoadMovie: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .fillMaxSize()
            .background(Color(0xFF2B2B2B))
    ) {
        when (uiState) {
            MovieDetailsUiState.Loading -> {
                Box(Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        Modifier.align(Center)
                    )
                }
            }
            is MovieDetailsUiState.Success -> {
                val movie = uiState.movie
                Column {
                    Column(
                        Modifier
                            .padding(horizontal = 24.dp, vertical = 36.dp)
                            .fillMaxWidth()
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            AsyncImage(
                                model = movie.image,
                                contentDescription = null,
                                Modifier
                                    .size(
                                        height = 152.dp,
                                        width = 100.dp
                                    )
                                    .clip(shape = RoundedCornerShape(4.dp)),
                                placeholder = ColorPainter(Color.Gray),
                                contentScale = ContentScale.Crop
                            )
                            Column {
                                Text(text = movie.title)
                                Text(text = movie.year.toString())
                                Text(text = movie.plot)
                            }
                        }
                        Spacer(modifier = Modifier.size(24.dp))
                        Row {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                val toggleButton = if (uiState.movie.inMyList) {
                                    ToggleButton(
                                        icon = Icons.Default.PlaylistRemove,
                                        text = "Remover da lista",
                                        action = onRemoveFromMyList,
                                        buttonColor = 0xffB20510
                                    )
                                } else {
                                    ToggleButton(
                                        icon = Icons.Default.PlaylistAdd,
                                        text = "Adicionar à lista",
                                        action = onAddToMyListClick,
                                        buttonColor = 0xff4F4F4F
                                    )
                                }
                                Box(modifier = Modifier.fillMaxWidth()) {
                                    Box(
                                        Modifier
                                            .padding(8.dp)
                                            .fillMaxWidth()
                                            .background(
                                                Color(toggleButton.buttonColor),
                                                shape = CircleShape
                                            )
                                            .align(Center)
                                            .clip(CircleShape)
                                            .clickable {
                                                toggleButton.action(movie)
                                            }
                                            .padding(8.dp)
                                    ) {
                                        Row(
                                            Modifier.align(Center),
                                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Icon(
                                                toggleButton.icon,
                                                contentDescription = null,
                                                Modifier
                                                    .size(32.dp)
                                            )
                                            Text(text = toggleButton.text)
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (uiState.suggestedMovies.isNotEmpty()) {
                        Text(
                            text = "Você também pode se interessar por...",
                            Modifier.padding(
                                horizontal = 16.dp,
                                vertical = 8.dp
                            )
                        )
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            contentPadding = PaddingValues(
                                horizontal = 16.dp,
                                vertical = 8.dp
                            )
                        ) {
                            items(uiState.suggestedMovies) { movie ->
                                Box {
                                    AsyncImage(
                                        model = movie.image,
                                        contentDescription = null,
                                        Modifier
                                            .width(100.dp)
                                            .height(150.dp)
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
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsScreenWithMovieAddedToMyListPreview() {
    AnyFlixTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            MovieDetailsScreen(
                uiState = MovieDetailsUiState.Success(
                    movie = sampleMovies.random(),
                ),
                onMovieClick = {},
                onAddToMyListClick = {},
                onRemoveFromMyList = {},
                onRetryLoadMovie = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsScreenWithoutMovieAddedToMyListPreview() {
    AnyFlixTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            MovieDetailsScreen(
                uiState = MovieDetailsUiState.Success(
                    movie = sampleMovies.first {
                        it.inMyList
                    }
                ),
                onMovieClick = {},
                onAddToMyListClick = {},
                onRemoveFromMyList = {},
                onRetryLoadMovie = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsScreenWithSuggestedMoviesPreview() {
    AnyFlixTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            MovieDetailsScreen(
                uiState = MovieDetailsUiState.Success(
                    movie = sampleMovies.random(),
                    suggestedMovies = sampleMovies.shuffled()
                ),
                onMovieClick = {},
                onAddToMyListClick = {},
                onRemoveFromMyList = {},
                onRetryLoadMovie = {}
            )
        }
    }
}
