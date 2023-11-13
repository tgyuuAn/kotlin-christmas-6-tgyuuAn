package christmas

import christmas.domain.Benefit
import christmas.domain.event.EventType

fun main() {
    val benefit = Benefit()
    benefit.accumulateBenefit(EventType.CHRISTMAS_D_DAY,2500)
    print(benefit.toString())
}
