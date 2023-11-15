package christmas.domain

import christmas.domain.menu.MenuItem

data class Freebie(
    val menuItem: MenuItem,
    val count: Int
)