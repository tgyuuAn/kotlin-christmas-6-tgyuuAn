package christmas.domain.event

import christmas.domain.menu.MenuCategory.*
import christmas.domain.Reservation
import christmas.util.Calendar

class WeekendEvent(
    event: Event,
    private val reservation: Reservation,
) : WoowaEvent(event, reservation) {

    override fun isEligibleDayForEvent(): Boolean {
        val day = (reservation.visitDate % Calendar.WEEK_LENGTH)
        return (day == Calendar.FRIDAY) || (day == Calendar.SATURDAY)
    }

    override fun calculateDiscountAmount(): Int {
        val totalDessertCount = reservation.orderedMenus.count { menuItem ->
            menuItem.menuItem.menuCategory == MAIN
        }
        return totalDessertCount * DISCOUNT_PER_MAIN
    }

    companion object {
        const val DISCOUNT_PER_MAIN = 2023
    }
}