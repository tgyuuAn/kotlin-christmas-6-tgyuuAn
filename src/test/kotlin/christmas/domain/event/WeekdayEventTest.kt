package christmas.domain.event

import christmas.domain.MenuItem
import christmas.domain.MenuItem.*
import christmas.domain.Reservation
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource


class WeekdayEventTest {

    @ParameterizedTest
    @ValueSource(ints = [3, 4, 5, 6, 7, 10, 11, 12])
    fun `평일 이벤트는 주말에 혜택을 받을 수 없다`(reservationDate: Int) {
        //given
        val reservation = Reservation(listOf(MUSHROOM_SOUP, MUSHROOM_SOUP), reservationDate)
        val weekdayEvent = WeekdayEvent(reservation = reservation)

        //when
        val actual = weekdayEvent.isEligibleForEvent()

        //then
        assertThat(actual).isFalse()
    }

    @Test
    fun `예약 메뉴가 음료로만 되어 있을 경우 혜택을 받을 수 없다`(reservationDate: Int) {
        //given
        val reservation = Reservation(listOf(CHAMPAGNE), reservationDate)
        val weekdayEvent = WeekdayEvent(reservation = reservation)

        //when
        val actual = weekdayEvent.isEligibleForEvent()

        //then
        assertThat(actual).isFalse()
    }

    @Test
    fun `총 예약 금액이 10000원 이하일 경우 예약이 불가능하다`() {
        //given
        val reservation = Reservation(listOf(MUSHROOM_SOUP), 2)
        val weekdayEvent = WeekdayEvent(reservation)

        //when
        val actual = weekdayEvent.isEligibleForEvent()

        //then
        assertThat(actual).isFalse()
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 8, 9])
    fun `평일 이벤트는 평일에 혜택을 받을 수 있다`(reservationDate: Int) {
        //given
        val reservation = Reservation(listOf(MUSHROOM_SOUP, MUSHROOM_SOUP), reservationDate)
        val weekdayEvent = WeekdayEvent(reservation = reservation)

        //when
        val actual = weekdayEvent.isEligibleForEvent()

        //then
        assertThat(actual).isTrue()
    }

    @Test
    fun `디저트 메뉴가 예약 메뉴에 없을 경우 할인 금액은 없다`(reservationDate: Int) {
        //given
        val reservation = Reservation(listOf(MUSHROOM_SOUP, MUSHROOM_SOUP), reservationDate)
        val weekdayEvent = WeekdayEvent(reservation = reservation)

        //when
        val actual = weekdayEvent.calculateDiscountAmount()

        //then
        val expected = 0
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `디저트 메뉴가 예약 메뉴에 있을 경우 하나 당 2023원이 할인된다`(reservationDate: Int) {
        //given
        val reservation = Reservation(listOf(CHOCOLATE_CAKE), reservationDate)
        val weekdayEvent = WeekdayEvent(reservation = reservation)

        //when
        val actual = weekdayEvent.calculateDiscountAmount()

        //then
        val expected = 2023
        assertThat(actual).isEqualTo(expected)
    }
}