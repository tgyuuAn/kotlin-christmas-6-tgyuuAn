package christmas.controller

import christmas.view.InputView

class Controller {
    fun run() {
        getReservationDate()
        getReservationOrders()
    }

    fun getReservationDate(): Int {
        val input = InputView.readDate()
        try {
            requireNotNull(input.toIntOrNull()) {
                println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.")
            }
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
                requireNotNull(splited[1].toIntOrNull()) {
                    println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.")
                }
                splited[1] to splited[1].toInt()
            }
        } catch (e: IllegalArgumentException) {
            getReservationOrders()
        }
        return ordersPair
    }
}