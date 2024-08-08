package com.vickbt.carrystore.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import carrystore.shared.generated.resources.Res
import carrystore.shared.generated.resources.nunito
import com.vickbt.carrystore.ui.navigation.NavigationItem
import com.vickbt.carrystore.ui.theme.Gray
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    cartItemCount: Int = 0,
    bottomNavItems: List<NavigationItem>
) {
    NavigationBar(
        modifier = modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = .85f)
    ) {
        bottomNavItems.iterator().forEach { item ->

            val currentDestination =
                navHostController.currentBackStackEntryAsState().value?.destination?.route
            val isSelected = item.route == currentDestination

            NavigationBarItem(
                icon = {
                    BadgedBox(badge = {
                        if (item == NavigationItem.Cart && cartItemCount > 0) {
                            Badge(
                                containerColor = MaterialTheme.colorScheme.primary.copy(alpha = .6f),
                                contentColor = MaterialTheme.colorScheme.onSurface
                            ) {
                                Text(
                                    text = cartItemCount.toString(),
                                    fontFamily = FontFamily(Font(Res.font.nunito)),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = stringResource(item.title)
                        )
                    }
                },
                label = {
                    Text(
                        text = stringResource(item.title),
                        fontFamily = FontFamily(Font(Res.font.nunito)),
                        fontWeight = FontWeight.Bold
                    )
                },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = Gray,
                    indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = .30f)
                ),
                selected = isSelected,
                onClick = {
                    if (item.route != currentDestination) {
                        navHostController.navigate(route = item.route) {
                            popUpTo(
                                navHostController.graph.startDestinationRoute
                                    ?: NavigationItem.Products.route
                            )
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}
