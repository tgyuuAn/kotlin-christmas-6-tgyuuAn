package christmas.domain.badge

enum class BadgeType(val badgeName: String, val minimumDiscountedAmount: Int) {
    SANTA("산타", 20000),
    TREE("트리", 10000),
    STAR("별", 5000),
    NOTHING("없음", 0),
}