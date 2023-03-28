package br.com.alura.anyflix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.alura.anyflix.navigation.AnyflixNavHost
import br.com.alura.anyflix.navigation.homeRoute
import br.com.alura.anyflix.navigation.movieDetailsRouteFullpath
import br.com.alura.anyflix.navigation.myListRoute
import br.com.alura.anyflix.navigation.navigateToBottomAppBarItem
import br.com.alura.anyflix.ui.components.AnyflixBottomAppBar
import br.com.alura.anyflix.ui.components.BottomAppBarItem
import br.com.alura.anyflix.ui.theme.AnyFlixTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnyFlixTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AnyflixApp()
                }
            }
        }
    }

}

@Composable
fun AnyflixApp(
    navController: NavHostController = rememberNavController()
) {
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentRoute = currentDestination?.route
    val selectedBottomAppBarItem = when (currentRoute) {
        homeRoute -> BottomAppBarItem.Home
        myListRoute -> BottomAppBarItem.MyList
        else -> BottomAppBarItem.Home
    }
    val bottomAppBarItems = remember {
        listOf(
            BottomAppBarItem.Home,
            BottomAppBarItem.MyList
        )
    }
    val isShowBackNavigation = when (currentRoute) {
        myListRoute, movieDetailsRouteFullpath -> true
        else -> false
    }
    val isShowBottomAppBar = when (currentRoute) {
        homeRoute, myListRoute -> true
        else -> false
    }
    AnyflixApp(
        bottomAppBarItems = bottomAppBarItems,
        selectedBottomAppBarItem = selectedBottomAppBarItem,
        onBottomAppBarItemSelected = { item ->
            navController.navigateToBottomAppBarItem(item)
        },
        isShowBackNavigation = isShowBackNavigation,
        isShowBottomAppBar = isShowBottomAppBar,
        onBackNavigationClick = {
            navController.popBackStack()
        },
        topAppBarTitle = {
            when (currentRoute) {
                myListRoute -> {
                    Text("Minha lista")
                }

                movieDetailsRouteFullpath -> {
                    Text("Informações")
                }

                homeRoute -> {
                    Image(
                        painterResource(id = R.drawable.anyflix),
                        contentDescription = null,
                        Modifier
                            .size(30.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    )
    {
        AnyflixNavHost(
            navController = navController
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnyflixApp(
    selectedBottomAppBarItem: BottomAppBarItem,
    bottomAppBarItems: List<BottomAppBarItem>,
    onBottomAppBarItemSelected: (BottomAppBarItem) -> Unit,
    isShowBackNavigation: Boolean = false,
    isShowBottomAppBar: Boolean = false,
    topAppBarTitle: @Composable () -> Unit = {},
    onBackNavigationClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    topAppBarTitle()
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    navigationIconContentColor = MaterialTheme.colorScheme.onBackground,
                    actionIconContentColor = MaterialTheme.colorScheme.onBackground
                ),
                navigationIcon = {
                    if (isShowBackNavigation) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = null,
                            Modifier
                                .padding(16.dp)
                                .clip(CircleShape)
                                .clickable {
                                    onBackNavigationClick()
                                }
                        )
                    }
                })
        },
        bottomBar = {
            if (isShowBottomAppBar) {
                AnyflixBottomAppBar(
                    selectedItem = selectedBottomAppBarItem,
                    items = bottomAppBarItems,
                    onItemClick = {
                        onBottomAppBarItemSelected(it)
                    }
                )
            }
        }
    ) {
        Box(
            Modifier.padding(it)
        ) {
            content()
        }
    }
}

@Preview
@Composable
fun AnyflixAppPreview() {
    AnyFlixTheme {
        Surface {
            AnyflixApp()
        }
    }
}