package com.moviesapi.presentation.ui

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.moviesapi.Constants
import com.moviesapi.presentation.ui.filter.FilterPage
import com.moviesapi.presentation.ui.search.SearchPage

@Composable
fun MyScaffold(nav: NavController) {

    Scaffold(
        bottomBar = { MyBottomNavigationBar(nav) },
        content = { Navigation(nav) }
    )
}


@Composable
fun Navigation(nav: NavController) {

    NavHost(navController = nav as NavHostController, startDestination = Route.Search.route)
    {
        composable(Route.Search.route)
        {
            SearchPage()
        }
        composable(Route.Filter.route)
        {
            FilterPage()
        }
    }
}

@Composable
fun MyBottomNavigationBar(navController: NavController) {
    BottomNavigation() {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        Constants.navItems.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(Route.Search.route)
                        launchSingleTop = true
                    }
                },
                icon = { Icon(item.iv, item.label) },
                label = { Text(text = item.label) },
                alwaysShowLabel = true
            )
        }
    }
}

sealed class Route(val route: String, val label: String, val iv: ImageVector) {
    object Search : Route("Search", "Search", Icons.Default.Search)
    object Filter : Route("Filter", "Filter", Icons.Default.List)
}