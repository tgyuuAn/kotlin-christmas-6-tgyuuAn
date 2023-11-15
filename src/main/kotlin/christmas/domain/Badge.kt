package christmas.domain

enum class Badge(val badgeName: String, val minimumDiscountedAmount: Int) {
    SANTA("산타", 20000),
    TREE("트리", 10000),
    STAR("별", 5000),
    NOTHING("없음", 0);

    companion object {
        fun getBadge(benefit: Benefit): Badge = when (benefit.getTotalDiscountedAmount()) {
            in STAR.minimumDiscountedAmount..<TREE.minimumDiscountedAmount -> STAR
            in TREE.minimumDiscountedAmount..<SANTA.minimumDiscountedAmount -> TREE
            in SANTA.minimumDiscountedAmount..Int.MAX_VALUE -> SANTA
            else -> NOTHING
        }
    }
}