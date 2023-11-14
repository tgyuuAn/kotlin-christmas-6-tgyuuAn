package christmas.view

import christmas.domain.Reservation
import christmas.domain.menu.MenuItem

object OutputView {
    fun printWelcomeMessage(reservation: Reservation) {
        println("12월 ${reservation.visitDate}일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!")
        println()
    }

    fun printMenus(reservation: Reservation) {
        println("<주문 메뉴>")
        reservation.orderedMenus.forEach { orderedMenu ->
            printMenu(orderedMenu)
        }
    }

    private fun printMenu(orderedMenu: Pair<MenuItem, Int>) {
        val menuName = orderedMenu.first.displayName
        val orderedCount = orderedMenu.second
        println("${menuName} ${orderedCount}개")
    }

    fun printTotalAmount(orderedMenus: Reservation) {
        println("<할인 전 총주문 금액>")
        println(orderedMenus.getTotalAmount())
    }
}