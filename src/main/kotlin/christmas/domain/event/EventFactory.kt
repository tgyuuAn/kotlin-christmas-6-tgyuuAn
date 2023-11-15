package christmas.domain.event

import christmas.domain.Reservation
import christmas.domain.event.Event.CHRISTMAS_D_DAY
import christmas.domain.event.Event.FREEBIE
import christmas.domain.event.Event.STARRED_DAY
import christmas.domain.event.Event.WEEKEND
import christmas.domain.event.Event.WEEK_DAY

object EventFactory {
    fun getEvent(event: Event, reservation: Reservation): WoowaEvent = when (event) {
        CHRISTMAS_D_DAY -> ChristmasDdayEvent(event = event, reservation = reservation)
        WEEK_DAY -> WeekdayEvent(event = event, reservation = reservation)
        WEEKEND -> WeekendEvent(event = event, reservation = reservation)
        STARRED_DAY -> StarredDayEvent(event = event, reservation = reservation)
        FREEBIE -> FreebieEvent(event = event, reservation = reservation)
    }
}