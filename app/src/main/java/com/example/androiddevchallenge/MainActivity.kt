/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.*
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun ThemeItem(@DrawableRes imageResId: Int, title: String) {
    Card(modifier = Modifier.height(136.dp), elevation = 1.dp) {
        Column {
            Image(
                modifier = Modifier
                    .height(96.dp)
                    .width(144.dp),
                painter = painterResource(id = imageResId),
                contentDescription = ""
            )
            Text(
                text = title,
                style = MaterialTheme.typography.h2,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun ProductItem(@DrawableRes imageResId: Int, title: String, description: String) {
    Row(modifier = Modifier.height(64.dp), verticalAlignment = Alignment.CenterVertically) {
        Image(
            modifier = Modifier.aspectRatio(1.0F),
            painter = painterResource(id = imageResId),
            contentDescription = ""
        )
        Column(with(ColumnScope) {
            Modifier
                .weight(1.0F)
                .padding(start = 16.dp)
        }) {
            Text(text = title, style = MaterialTheme.typography.h2)
            Text(text = description, style = MaterialTheme.typography.body1)
        }
        val isChecked = remember { mutableStateOf(false) }
        Checkbox(checked = isChecked.value, onCheckedChange = { isChecked.value = it })
    }
}

@Composable
fun ThemeList() {
    Text(
        stringResource(R.string.theme_list_title),
        modifier = Modifier.padding(horizontal = 16.dp),
        style = MaterialTheme.typography.h1
    )
    LazyRow(content = {
        items(12) { index ->
            ThemeItem(R.drawable.test_image, "Desert chic")
        }
    }, contentPadding = PaddingValues(16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp))
}

@Composable
fun ProductItemList() {
    Row(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(stringResource(R.string.item_list_title), style = MaterialTheme.typography.h1)
        Icon(Icons.Filled.FilterList, contentDescription = "")
    }
    // TODO: Insert sort image
    LazyColumn(
        content = {
            items(12) { index ->
                ProductItem(R.drawable.test_image_list, "Monstera", "This is a description")
                Divider()
            }
        }, contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
    )
}

// Start building your app here!
@Composable
fun MyApp() {
    Surface(color = MaterialTheme.colors.background) {
        val navController = rememberNavController()
        Scaffold(
            topBar = {
                val searchTextValue = remember { mutableStateOf("") }
                // TODO: Border and left image
                TextField(
                    leadingIcon = {
                        Icon(Icons.Filled.Search, contentDescription = "")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(stringResource(R.string.search_hint_text)) },
                    value = searchTextValue.value,
                    onValueChange = { searchTextValue.value = it })
            },
            bottomBar = {
                BottomNavigation(elevation = 16.dp) {
                    val navBackStackEntry = navController.currentBackStackEntryAsState().value
                    val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)

                    val items = listOf(
                        Screen.Home,
                        Screen.Favorites,
                        Screen.Profile,
                        Screen.Cart
                    )
                    items.forEach { screen ->
                        BottomNavigationItem(
                            icon = { Icon(screen.imageVector, contentDescription = "") },
                            label = { Text(stringResource(screen.resourceId)) },
                            selected = currentRoute == screen.route,
                            onClick = {
                                navController.navigate(screen.route) {
                                    // Pop up to the start destination of the graph to
                                    // avoid building up a large stack of destinations
                                    // on the back stack as users select items
                                    popUpTo = navController.graph.startDestination
                                    // Avoid multiple copies of the same destination when
                                    // reselecting the same item
                                    launchSingleTop = true
                                }
                            }
                        )
                    }
                }
            }
        ) {
            Column {
                ThemeList()
                // TODO: Fix items clipping at the bottom
                ProductItemList()
            }
            NavHost(navController, startDestination = Screen.Home.route) {
                composable(Screen.Home.route) { }
                composable(Screen.Favorites.route) { }
                composable(Screen.Profile.route) { }
                composable(Screen.Cart.route) { }
            }
        }
    }
}

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    val imageVector: ImageVector
) {
    object Home : Screen("home", R.string.tab_home, Icons.Filled.Home)
    object Favorites : Screen("favorites", R.string.tab_favorites, Icons.Filled.FavoriteBorder)
    object Profile : Screen("profile", R.string.tab_profile, Icons.Filled.AccountCircle)
    object Cart : Screen("cart", R.string.tab_cart, Icons.Filled.ShoppingCart)
}

@Composable
fun NavItem(imageVector: ImageVector, title: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .height(24.dp)
                .width(24.dp),
            imageVector = imageVector,
            contentDescription = ""
        )
        Text(text = title)
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
