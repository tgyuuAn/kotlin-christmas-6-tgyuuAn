package christmas.domain.event

import christmas.domain.Reservation
import christmas.domain.menu.MenuCategory.DESSERT
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

    override fun calculateDiscountAmount(): Int = reservation.orderedMenus
        .filter { it.menuItem.menuCategory == DESSERT }
        .sumOf { it.orderedCount } * DISCOUNT_PER_BEVERAGE

    companion object {
        const val DISCOUNT_PER_BEVERAGE = 2023
    }
}