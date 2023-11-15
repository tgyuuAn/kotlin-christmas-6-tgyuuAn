package christmas.domain.event

import christmas.domain.menu.MenuCategory.*
import christmas.domain.Reservation
import christmas.util.Calendar.FRIDAY
import christmas.util.Calendar.SATURDAY
import christmas.util.Calendar.WEEK_LENGTH

class WeekdayEvent(
    event: Event,
    private val reservation: Reservation,
) : WoowaEvent(event, reservation) {

    override fun isEligibleDayForEvent(): Boolean {
        val day = (reservation.visitDate % WEEK_LENGTH)
        return (day != FRIDAY) && (day != SATURDAY)
    }

    override fun calculateDiscountAmount(): Int {
        var totalDessertCount = 0
        reservation.orderedMenus.forEach { menuItem ->
            if (menuItem.menuItem.menuCategory == DESSERT) {
                totalDessertCount += menuItem.orderedCount
            }
        }
        return totalDessertCount * DISCOUNT_PER_BEVERAGE
    }

    companion object {
        const val DISCOUNT_PER_BEVERAGE = 2023
    }
}