package br.com.alura.anyflix.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import br.com.alura.anyflix.ui.theme.AnyFlixTheme
import br.com.alura.anyflix.ui.theme.unselectedIconBottomAppBarColor
import br.com.alura.anyflix.ui.theme.unselectedTextBottomAppBarColor

sealed class BottomAppBarItem(
    val icon: ImageVector,
    val label: String
) {
    object Home : BottomAppBarItem(
        Icons.Default.Home,
        "Início"
    )

    object MyList : BottomAppBarItem(
        Icons.Default.List,
        "Minha lista"
    )
}

@Composable
fun AnyflixBottomAppBar(
    selectedItem: BottomAppBarItem,
    items: List<BottomAppBarItem>,
    modifier: Modifier = Modifier,
    onItemClick: (BottomAppBarItem) -> Unit = {},
) {
    BottomAppBar(
        modifier,
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        items.forEach {
            NavigationBarItem(
                selected = it == selectedItem,
                onClick = { onItemClick(it) },
                icon = {
                    Column {
                        Icon(
                            it.icon,
                            contentDescription = null
                        )
                    }
                },
                label = {
                    Text(text = it.label)
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.onBackground,
                    selectedTextColor = MaterialTheme.colorScheme.onBackground,
                    unselectedIconColor = unselectedIconBottomAppBarColor,
                    unselectedTextColor = unselectedTextBottomAppBarColor
                )
            )
        }
    }
}

@Preview
@Composable
fun AnyflixBottomAppBarPreview() {
    AnyFlixTheme {
        Surface {
            AnyflixBottomAppBar(
                selectedItem = BottomAppBarItem.Home,
                items = listOf(
                    BottomAppBarItem.Home,
                    BottomAppBarItem.MyList
                )
            )
        }
    }
}