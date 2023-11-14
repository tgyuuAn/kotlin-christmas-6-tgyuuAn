package christmas.controller

import christmas.domain.menu.MenuItem
import christmas.validator.InputValidator.validateInputIsInt
import christmas.util.OrderParser.parseOrders
import christmas.view.InputView

class Controller {
    fun run() {
        val reservationDate = getReservationDate()
        val reservationOrders = getReservationOrders()
        println("12월 ${reservationDate}일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!")
        println()
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