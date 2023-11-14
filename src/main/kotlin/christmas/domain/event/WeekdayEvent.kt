package christmas.domain.event

import christmas.domain.menu.MenuType.*
import christmas.domain.Reservation
import christmas.util.Calendar.FRIDAY
import christmas.util.Calendar.SATURDAY
import christmas.util.Calendar.WEEK_LENGTH

class WeekdayEvent(
    eventType: EventType,
    private val reservation: Reservation,
) : WoowaEvent(eventType, reservation) {

    override fun isEligibleDayForEvent(): Boolean {
        val day = (reservation.visitDate % WEEK_LENGTH)
        return (day != FRIDAY) && (day != SATURDAY)
    }

    override fun calculateDiscountAmount(): Int {
        val totalDessertCount = reservation.orderedMenus.count { menuItem ->
            menuItem.menuType == DESSERT
        }
        return totalDessertCount * DISCOUNT_PER_BEVERAGE
    }

    companion object {
        const val DISCOUNT_PER_BEVERAGE = 2023
    }
}