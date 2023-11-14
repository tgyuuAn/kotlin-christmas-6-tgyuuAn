package christmas.controller

import christmas.domain.Benefit
import christmas.domain.menu.OrderMenu
import christmas.domain.Reservation
import christmas.domain.event.EventFactory
import christmas.domain.event.EventType
import christmas.domain.event.FreebieEvent
import christmas.validator.InputValidator.validateInputIsInt
import christmas.util.OrderParser.parseOrders
import christmas.view.InputView
import christmas.view.OutputView.printMenus
import christmas.view.OutputView.printTotalAmount
import christmas.view.OutputView.printWelcomeMessage

class Controller {
    fun run() {
        val reservationDate = getReservationDate()
        val reservationOrders = getReservationOrders()
        val reservation = Reservation(reservationOrders, reservationDate)
        printWelcomeMessage(reservation)
        printMenus(reservation)
        printTotalAmount(reservation)
        val calculatedBenefit = caculateTotalBenefits(reservation)
    }

    private fun caculateTotalBenefits(reservation: Reservation): Benefit {
        val benefit = Benefit()
        EventType.values().forEach { eventType ->
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
            val input = InputView.readDate()
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
            val input = InputView.readOrder()
            try {
                ordersPair = parseOrders(input)
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
        return ordersPair
    }
}