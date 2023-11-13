package christmas.domain.badge

import christmas.domain.Benefit
import christmas.domain.badge.BadgeType.*

class BadgeEvent(private val benefit: Benefit) {
    fun getBadge(): BadgeType = when (benefit.totalDiscountedAmount) {
        in STAR.minimumDiscountedAmount..<TREE.minimumDiscountedAmount -> STAR
        in TREE.minimumDiscountedAmount..<SANTA.minimumDiscountedAmount -> TREE
        in SANTA.minimumDiscountedAmount..Int.MAX_VALUE -> SANTA
        else -> NOTHING
    }
}