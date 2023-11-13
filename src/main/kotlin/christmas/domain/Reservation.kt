package christmas.domain

data class Reservation(
    val orderedMenus: List<MenuItem>,
    val visitDate: Int,
) {
    fun getTotalAmount(): Int {
        var totalAmount = 0
        orderedMenus.forEach { orderedMenu ->
            totalAmount += orderedMenu.price
        }
        return totalAmount
    }
}