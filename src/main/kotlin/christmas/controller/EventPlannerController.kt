package christmas.controller

import christmas.domain.Benefit
import christmas.domain.menu.OrderMenu
import christmas.domain.Reservation
import christmas.domain.event.EventFactory
import christmas.domain.event.Event
import christmas.validator.InputValidator.validateInputIsInt
import christmas.util.OrderParser.parseOrders
import christmas.view.InputView
import christmas.view.OutputView

class EventPlannerController(
    private val inputView: InputView,
    private val outputView: OutputView
) {
    fun run() {
        val reservation = createReservation()
        val calculatedBenefit = calculateTotalBenefits(reservation)
        outputView.printReservationDetails(reservation, calculatedBenefit)
    }

    private fun createReservation(): Reservation {
        val reservationDate = getReservationDate()
        val reservationOrders = getReservationOrders()
        return Reservation(reservationOrders, reservationDate)
    }

    private fun calculateTotalBenefits(reservation: Reservation): Benefit {
        val benefit = Benefit()
        Event.values().forEach { eventType ->
            val event = EventFactory.getEvent(eventType, reservation)
            if (event.isEligibleForEvent()) {
                val discountAmount = event.calculateDiscountAmount()
                benefit.accumulateBenefit(eventType, discountAmount)
            }
        }
        return benefit
    }

    private fun getReservationDate(): Int {
        var reservationDate: Int? = null

        while (reservationDate == null) {
            val input = inputView.readDate()
            try {
                reservationDate = validateInputIsInt(input, "유효하지 않은 날짜입니다.")
                    .onFailure { throw it }
                    .getOrNull()
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
        return reservationDate
    }

    private fun getReservationOrders(): List<OrderMenu> {
        var ordersPair: List<OrderMenu>? = null

        while (ordersPair == null) {
            val input = inputView.readOrder()
            try {
                ordersPair = parseOrders(input)
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
        return ordersPair
    }
}