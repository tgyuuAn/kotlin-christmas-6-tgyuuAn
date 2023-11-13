package christmas.domain.event

import christmas.domain.EventType
import christmas.domain.EventType.*
import christmas.domain.Reservation

object EventFactory {
    fun getEvent(eventType: EventType, reservation: Reservation): WoowaEvent = when (eventType) {
        CHRISTMAS_D_DAY -> ChristmasDdayEvent(reservation = reservation)
        WEEK_DAY -> ChristmasDdayEvent(reservation = reservation)
        WEEKEND -> ChristmasDdayEvent(reservation = reservation)
        STARED_DAY -> ChristmasDdayEvent(reservation = reservation)
    }
}