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
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.vickbt.carrystore.ui.navigation.NavigationItem
import com.vickbt.carrystore.ui.theme.Gray

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
                            Badge {
                                Text(text = cartItemCount.toString())
                            }
                        }
                    }) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    }
                },
                label = { Text(text = item.title) },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = Gray
                ),
                selected = isSelected,
                onClick = {
                    if (item.route != currentDestination) navHostController.navigate(route = item.route)
                }
            )
        }
    }
}
