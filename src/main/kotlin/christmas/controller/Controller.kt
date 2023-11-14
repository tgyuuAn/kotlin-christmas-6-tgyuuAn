package christmas.controller

import christmas.domain.Reservation
import christmas.domain.menu.MenuItem
import christmas.validator.InputValidator.validateInputIsInt
import christmas.util.OrderParser.parseOrders
import christmas.view.InputView
import christmas.view.OutputView
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

    private fun getReservationOrders(): List<Pair<MenuItem, Int>> {
        var ordersPair: List<Pair<MenuItem, Int>>? = null

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