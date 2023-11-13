package christmas.domain.event

import christmas.domain.MenuType
import christmas.domain.Reservation
import christmas.uitl.Calendar

class WeekendEvent(private val reservation: Reservation) : WoowaEvent(reservation) {
    override fun isEligibleDayForEvent(): Boolean {
        val day = (reservation.visitDate % Calendar.WEEK_LENGTH)
        return day == Calendar.FRIDAY || day == Calendar.SATURDAY
    }

    override fun calculateDiscountAmount(): Int {
        val totalDessertCount = reservation.orderedMenus.count { menuItem ->
            menuItem.menuType == MenuType.MAIN
        }
        return totalDessertCount * WeekendEvent.DISCOUNT_PER_MAIN
    }

    companion object {
        const val DISCOUNT_PER_MAIN = 2023
    }
}