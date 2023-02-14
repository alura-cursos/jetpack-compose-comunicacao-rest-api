package br.com.alura.anyflix.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import br.com.alura.anyflix.model.Movie
import br.com.alura.anyflix.sampleData.sampleMovies
import br.com.alura.anyflix.ui.theme.AnyFlixTheme
import coil.compose.AsyncImage

@Composable
fun AnyflixMainBanner(
    movie: Movie,
    modifier: Modifier = Modifier,
    onMovieClick: (Movie) -> Unit,
) {
    Box(
        Modifier
    ) {
        Text(
            text = movie.title,
            Modifier
                .padding(16.dp)
                .background(
                    Color.Black.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )
                .zIndex(1f)
                .align(Alignment.CenterStart)
        )
        AsyncImage(
            model = movie.image,
            contentDescription = null,
            modifier
                .fillMaxWidth()
                .drawWithContent {
                    val colors = listOf(
                        Color.Black,
                        Color.Transparent,
                        Color.Black,
                    )
                    drawContent()
                    drawRect(
                        brush = Brush.verticalGradient(
                            colors
                        ),
                    )
                }
                .clickable {
                    onMovieClick(movie)
                },
            placeholder = ColorPainter(
                Color.Gray
            ),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
fun AnyflixMainBannerPreview() {
    AnyFlixTheme {
        Surface {
            AnyflixMainBanner(
                movie = sampleMovies.random(),
                Modifier.height(200.dp),
                onMovieClick = {},
            )
        }
    }
}