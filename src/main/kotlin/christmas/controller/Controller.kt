package christmas.controller

import christmas.domain.menu.MenuItem
import christmas.validator.InputValidator.validateInputIsInt
import christmas.util.OrderParser.parseOrders
import christmas.view.InputView

class Controller {
    fun run() {
        val reservationDate = getReservationDate()
        val reservationOrders = getReservationOrders()
    }

    private fun getReservationDate(): Int {
        val input = InputView.readDate()
        try {
            validateInputIsInt(input, "유효하지 않은 날짜입니다.")
        } catch (e: IllegalArgumentException) {
            getReservationDate()
        }
        return input.toInt()
    }

    private fun getReservationOrders(): List<Pair<MenuItem, Int>> {
        val input = InputView.readOrder()
        val ordersPair: List<Pair<MenuItem, Int>> = parseOrders(input)
        return ordersPair
    }
}