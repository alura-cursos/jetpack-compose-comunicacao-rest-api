package br.com.alura.anyflix.sampleData

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import br.com.alura.anyflix.model.Movie
import kotlin.random.Random

val randomImage
    get() = "https://picsum.photos/${Random.nextInt(1920, 2560)}/${
        Random.nextInt(
            1080,
            1440
        )
    }"

val randomTitle
    get() = LoremIpsum(Random.nextInt(1, 5)).values
        .first().toString()
val randomYear
    get() =
        Random.nextInt(1980, 2023)
val randomPlot
    get() = LoremIpsum(Random.nextInt(5, 30)).values
        .first().toString()


val sampleMovies = List(15) {
    Movie(
        title = randomTitle,
        image = randomImage,
        year = randomYear,
        plot = randomPlot,
        inMyList = it % 2 == 0
    )
}

val sampleMovieSections = mapOf(
    "Em alta" to sampleMovies.shuffled().take(7),
    "Novidades" to sampleMovies.shuffled().take(7),
    "Continue assistindo" to sampleMovies.shuffled().take(7)
)