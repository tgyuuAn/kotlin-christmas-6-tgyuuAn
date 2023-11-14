package christmas.view

import christmas.domain.Benefit
import christmas.domain.menu.OrderMenu
import christmas.domain.Reservation
import christmas.domain.event.EventType
import christmas.domain.event.EventType.*
import christmas.domain.menu.MenuItem
import christmas.domain.menu.MenuItem.CHAMPAGNE

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

    fun printFreebie(benefit : Benefit){
        println("<증정 메뉴>")
        if(benefit.discountedAmount.getValue(FREEBIE) == CHAMPAGNE.price){
            println("${CHAMPAGNE.displayName} 1개")
            return
        }
        println("없음")
    }

    fun printTotalBenefitDetail(benefit : Benefit){
        println("<혜택 내역>")
        if(benefit.toString() ==""){
            println("없음")
            return
        }
        println(benefit.toString())
    }

    private fun printTotalBenefit(benefit : Benefit){
        println("<총혜택 금액>")
        println(benefit.getTotalDiscountedAmount())
    }
}