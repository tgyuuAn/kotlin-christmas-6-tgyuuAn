package christmas.controller

import christmas.domain.Benefit
import christmas.domain.menu.OrderMenu
import christmas.domain.Reservation
import christmas.util.OrderParser.parseOrders
import christmas.validator.InputValidator.validateDateInput
import christmas.view.InputView
import christmas.view.OutputView

class EventPlannerController(
    private val inputView: InputView,
    private val outputView: OutputView
) {
    fun run() {
        outputView.printPreviewMessage()
        val reservation = createReservation()
        val benefit = Benefit(reservation)
        outputView.printReservationDetails(reservation, benefit)
    }

    private fun createReservation(): Reservation {
        val reservationDate = getReservationDate()
        var reservation: Reservation? = null
        while (reservation == null) {
            try {
                val reservationOrders = getReservationOrders()
                reservation = Reservation(reservationOrders, reservationDate)
            } catch (e: IllegalStateException) {
                println(e.message)
            }
        }
        return reservation
    }

    private fun getReservationDate(): Int {
        var reservationDate: Int? = null

        while (reservationDate == null) {
            val input = inputView.readDate()
            try {
                reservationDate = validateDateInput(input, "유효하지 않은 날짜입니다.")
                    .getOrThrow()
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