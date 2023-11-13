package christmas.domain.event

import christmas.domain.event.EventType.*
import christmas.domain.Reservation

object EventFactory {
    fun getEvent(eventType: EventType, reservation: Reservation): WoowaEvent = when (eventType) {
        CHRISTMAS_D_DAY -> ChristmasDdayEvent(eventType = eventType, reservation = reservation)
        WEEK_DAY -> WeekdayEvent(eventType = eventType, reservation = reservation)
        WEEKEND -> WeekendEvent(eventType = eventType, reservation = reservation)
        STARRED_DAY -> StarredDayEvent(eventType = eventType, reservation = reservation)
        FREEBIE -> FreebieEvent(eventType = eventType, reservation = reservation)
    }
}