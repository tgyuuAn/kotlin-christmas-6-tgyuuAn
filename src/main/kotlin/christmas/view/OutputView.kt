package christmas.view

import christmas.domain.menu.MenuItem

object OutputView {
    fun printMenus(orderedMenus: List<Pair<MenuItem, Int>>) {
        println("<주문 메뉴>")
        orderedMenus.forEach { orderedMenu ->
            printMenu(orderedMenu)
        }
    }

    private fun printMenu(orderedMenu: Pair<MenuItem, Int>) {
        val menuName = orderedMenu.first.displayName
        val orderedCount = orderedMenu.second
        println("${menuName} ${orderedCount}개")
    }
}