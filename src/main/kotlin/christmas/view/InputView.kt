package christmas.view

import camp.nextstep.edu.missionutils.Console

object InputView {
    fun readDate(): String {
        println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)")
        val input = Console.readLine().trim()
        return input
    }

    fun readOrder(): String {
        println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)")
        val input = Console.readLine()
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
            readOrder()
        }
        return ordersPair
    }
}