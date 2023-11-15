package christmas.view

import christmas.domain.Benefit
import christmas.domain.menu.OrderMenu
import christmas.domain.Reservation
import christmas.domain.Badge.Companion.getBadge
import christmas.util.StringFormatter.decimalFormat

class OutputView {
    fun printPreviewMessage(){
        println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.")
    }

    fun printReservationDetails(reservation: Reservation, calculatedBenefit: Benefit) {
        printPreviewMessage(reservation)
        printMenus(reservation)
        printTotalAmount(reservation)
        printFreebie(calculatedBenefit)
        printTotalBenefitDetail(calculatedBenefit)
        printTotalBenefit(calculatedBenefit)
        printExpectedPaymentAmount(reservation, calculatedBenefit)
        printDecemberEventBadge(calculatedBenefit)
    }

    private fun printPreviewMessage(reservation: Reservation) {
        println("12월 ${reservation.visitDate}일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!")
        println()
    }

    private fun printMenus(reservation: Reservation) {
        println("<주문 메뉴>")
        reservation.orderedMenus.forEach { orderedMenu ->
            printMenu(orderedMenu)
        }
        println()
    }

    private fun printMenu(orderedMenu: OrderMenu) {
        val menuName = orderedMenu.menuItem.displayName
        val orderedCount = orderedMenu.orderedCount
        println("${menuName} ${orderedCount}개")
    }

    private fun printTotalAmount(orderedMenus: Reservation) {
        println("<할인 전 총주문 금액>")
        println(decimalFormat.format(orderedMenus.getTotalAmount()))
        println()
    }

    private fun printFreebie(benefit: Benefit) {
        println("<증정 메뉴>")
        val freebie = benefit.getFreebieOrNull()
        freebie?.let {
            println("${it.menuItem} ${it.count}")
        } ?: println("없음")
        println()
    }

    private fun printTotalBenefitDetail(benefit: Benefit) {
        println("<혜택 내역>")
        println(benefit.toString())
        println()
    }

    private fun printTotalBenefit(benefit: Benefit) {
        val totalDiscountedAmount = benefit.getTotalDiscountedAmount()
        println("<총혜택 금액>")
        if (totalDiscountedAmount > 0) {
            print("-")
        }
        println(decimalFormat.format(benefit.getTotalDiscountedAmount()))
        println()
    }

    private fun printExpectedPaymentAmount(reservation: Reservation, benefit: Benefit) {
        println("<할인 후 예상 결제 금액>")
        println(decimalFormat.format(reservation.getTotalAmount() - benefit.getTotalDiscountedAmount()))
        println()
    }

    private fun printDecemberEventBadge(benefit: Benefit) {
        println("<12월 이벤트 배지>")
        val badge = getBadge(benefit)
        println(badge.badgeName)
    }
}