package christmas.controller

import christmas.domain.validator.InputValidator.validateInputIsInt
import christmas.view.InputView

class Controller {
    fun run() {
        getReservationDate()
        getReservationOrders()
    }

    fun getReservationDate(): Int {
        val input = InputView.readDate()
        try {
            validateInputIsInt(input, "유효하지 않은 날짜입니다.)
        } catch (e: IllegalArgumentException) {
            getReservationDate()
        }
        return input.toInt()
    }

    fun getReservationOrders(): List<Pair<String, Int>> {
        val input = InputView.readOrder()
        val orders = input.split(",")
        var ordersPair: List<Pair<String, Int>> = listOf()
        try {
            ordersPair = orders.map {
                val splited = it.trim().split("-").map {
                    it.trim()
                }
                validateInputIsInt(splited[1], "유효하지 않은 주문입니다.")
                splited[1] to splited[1].toInt()
            }
        } catch (e: IllegalArgumentException) {
            getReservationOrders()
        }
        return ordersPair
    }
}