package christmas.domain.event

import christmas.domain.event.EventType.*
import christmas.domain.menu.MenuItem.*
import christmas.domain.Reservation
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class FreebieEventTest {

    private val eventType = FREEBIE

    @Test
    fun `예약 메뉴가 음료로만 되어 있을 경우 혜택을 받을 수 없다`() {
        //given
        val reservation = Reservation(listOf(CHAMPAGNE), 3)
        val freebieEvent = FreebieEvent(eventType = eventType, reservation = reservation)

        //when
        val actual = freebieEvent.isEligibleForEvent()

        //then
        Assertions.assertThat(actual).isFalse()
    }

    @Test
    fun `총 예약 금액이 10000원 이하일 경우 예약이 불가능하다`() {
        //given
        val reservation = Reservation(listOf(MUSHROOM_SOUP), 3)
        val freebieEvent = FreebieEvent(eventType = eventType, reservation = reservation)

        //when
        val actual = freebieEvent.isEligibleForEvent()

        //then
        Assertions.assertThat(actual).isFalse()
    }

    @Test
    fun `총 구매 금액이 120000원 이하일 경우 증정 혜택을 받지 못한다`() {
        //given
        val reservation = Reservation(listOf(MUSHROOM_SOUP), 3)
        val freebieEvent = FreebieEvent(eventType = eventType, reservation = reservation)

        //when
        val actual = freebieEvent.isEligibleForEvent()

        //then
        Assertions.assertThat(actual).isFalse()
    }

    @Test
    fun `총 구매 금액이 120000원 이상일 경우 증정 혜택을 받는다`() {
        //given
        val reservation = Reservation(generateDeliciousFood(), 3)
        val freebieEvent = FreebieEvent(eventType = eventType, reservation = reservation)

        //when
        val actual = freebieEvent.isEligibleForEvent()

        //then
        Assertions.assertThat(actual).isTrue()
    }

    @Test
    fun `총 구매 금액이 120000원 이상일 경우 증정 혜택은 샴페인 한 개의 가격이다`() {
        //given
        val reservation = Reservation(generateDeliciousFood(), 3)
        val freebieEvent = FreebieEvent(eventType = eventType, reservation = reservation)

        //when
        val actual = freebieEvent.calculateDiscountAmount()

        //then
        val expected = CHAMPAGNE.price
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    private fun generateDeliciousFood() =
        listOf(T_BONE_STEAK, T_BONE_STEAK, CHAMPAGNE, CHOCOLATE_CAKE, CAESAR_SALAD)
}