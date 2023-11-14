package christmas.domain

import christmas.domain.menu.MenuItem

data class OrderMenu(
    val menuItem : MenuItem,
    val orderedCount : Int
)
