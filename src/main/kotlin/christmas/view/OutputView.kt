package christmas.view

import christmas.domain.menu.OrderMenu
import christmas.domain.Reservation

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

    private fun printMenu(orderedMenu: OrderMenu) {
        val menuName = orderedMenu.menuItem.displayName
        val orderedCount = orderedMenu.orderedCount
        println("${menuName} ${orderedCount}개")
    }

    fun printTotalAmount(orderedMenus: Reservation) {
        println("<할인 전 총주문 금액>")
        println(orderedMenus.getTotalAmount())
    }
}