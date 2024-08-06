package com.example.demoforjetpack

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class BottomNavItem(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val route: String
) {
    object Home : BottomNavItem(R.string.app_home, R.drawable.baseline_home_24, "home")
    object Search : BottomNavItem(R.string.app_search, R.drawable.baseline_search_24, "search")
    object Profile :
        BottomNavItem(R.string.app_profile, R.drawable.baseline_self_improvement_24, "profile")

    object Map : BottomNavItem(R.string.app_map, R.drawable.baseline_home_24, "map")


}
