package christmas.domain.event

import christmas.domain.MenuType.*
import christmas.domain.Reservation
import christmas.uitl.Calendar.FRIDAY
import christmas.uitl.Calendar.SATURDAY
import christmas.uitl.Calendar.WEEK_LENGTH

class WeekdayEvent(private val reservation: Reservation) : WoowaEvent(reservation) {

    override fun isEligibleDayForEvent(): Boolean {
        val day = (reservation.visitDate % WEEK_LENGTH)
        return day != FRIDAY && day != SATURDAY
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