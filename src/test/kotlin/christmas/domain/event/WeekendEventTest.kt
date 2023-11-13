package christmas.domain.event

import christmas.domain.event.EventType.*
import christmas.domain.menu.MenuItem.*
import christmas.domain.Reservation
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class WeekendEventTest {

    private val eventType = WEEKEND

    @ParameterizedTest
    @ValueSource(ints = [3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31])
    fun `주말 이벤트는 평일에 할인 혜택을 받을 수 없다`(reservationDate: Int) {
        //given
        val reservation =
            Reservation(listOf(MUSHROOM_SOUP, MUSHROOM_SOUP), reservationDate)
        val weekendEvent = WeekendEvent(eventType = eventType, reservation = reservation)

        //when
        val actual = weekendEvent.isEligibleForEvent()

        //then
        Assertions.assertThat(actual).isFalse()
    }

    @Test
    fun `예약 메뉴가 음료로만 되어 있을 경우 할인 혜택을 받을 수 없다`() {
        //given
        val reservation = Reservation(listOf(CHAMPAGNE), 2)
        val weekendEvent = WeekendEvent(eventType = eventType, reservation = reservation)

        //when
        val actual = weekendEvent.isEligibleForEvent()

        //then
        Assertions.assertThat(actual).isFalse()
    }

    @Test
    fun `총 예약 금액이 10000원 이하일 경우 할인 혜택을 받을 수 없다`() {
        //given
        val reservation = Reservation(listOf(MUSHROOM_SOUP), 2)
        val weekendEvent = WeekendEvent(eventType = eventType, reservation = reservation)

        //when
        val actual = weekendEvent.isEligibleForEvent()

        //then
        Assertions.assertThat(actual).isFalse()
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 8, 9, 15, 16, 22, 23, 29, 30])
    fun `주말 이벤트는 주말에 할인 혜택을 받을 수 있다`(reservationDate: Int) {
        //given
        val reservation =
            Reservation(listOf(MUSHROOM_SOUP, MUSHROOM_SOUP), reservationDate)
        val weekendEvent = WeekendEvent(eventType = eventType, reservation = reservation)

        //when
        val actual = weekendEvent.isEligibleForEvent()

        //then
        Assertions.assertThat(actual).isTrue()
    }

    @Test
    fun `메인 메뉴가 예약 메뉴에 없을 경우 할인 금액은 없다`() {
        //given
        val reservation = Reservation(listOf(MUSHROOM_SOUP, MUSHROOM_SOUP), 1)
        val weekendEvent = WeekendEvent(eventType = eventType, reservation = reservation)

        //when
        val actual = weekendEvent.calculateDiscountAmount()

        //then
        val expected = 0
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `디저트 메뉴가 예약 메뉴에 있을 한 개만 있을 경우 2023원이 할인된다`() {
        //given
        val reservation = Reservation(listOf(T_BONE_STEAK), 1)
        val weekendEvent = WeekendEvent(eventType = eventType, reservation = reservation)

        //when
        val actual = weekendEvent.calculateDiscountAmount()

        //then
        val expected = WeekendEvent.DISCOUNT_PER_MAIN
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `메인 메뉴가 예약 메뉴에 있을 경우 하나 당 2023원이 할인된다`() {
        //given
        val reservation = Reservation(listOf(T_BONE_STEAK, T_BONE_STEAK), 1)
        val weekendEvent = WeekendEvent(eventType = eventType, reservation = reservation)

        //when
        val actual = weekendEvent.calculateDiscountAmount()

        //then
        val expected = WeekendEvent.DISCOUNT_PER_MAIN * 2
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}