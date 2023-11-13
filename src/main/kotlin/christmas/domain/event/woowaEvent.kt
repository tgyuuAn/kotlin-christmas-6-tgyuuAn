package christmas.domain.event

import christmas.domain.MenuCategory

abstract class woowaEvent(
    orderedMenu: List<MenuCategory>
) {

    init {
        require(totalAmount >= 10000) {
            "총 예약 금액은 10000원 이상이어야 합니다"
        }
    }

    fun calculateDiscountAmount(): Long
    fun isEligibleForEvent(
        reservationDate: Int,
    ): Boolean
}