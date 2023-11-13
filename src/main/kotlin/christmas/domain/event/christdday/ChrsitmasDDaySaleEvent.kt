package christmas.domain.event.christdday

import christmas.domain.event.woowaEvent

class ChrsitmasDdaySaleEvent() : woowaEvent {
    override fun applyDiscount(): Long {
        return 0.toLong()
    }
}