package christmas.domain.event.christdday

import christmas.domain.MenuItem
import christmas.domain.Reservation
import christmas.domain.event.ChristmasDdayEvent
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ChristmasDdayEventTest {

    @ParameterizedTest
    @ValueSource(ints = [26, 27, 28, 29, 30, 31])
    fun `예약 날짜가 26일 이상일 경우 할인을 받지 못한다`(reservationDate: Int) {
        //given
        val reservation =
            Reservation(listOf(MenuItem.MUSHROOM_SOUP, MenuItem.MUSHROOM_SOUP), reservationDate)
        val christmasDdayEvent = ChristmasDdayEvent(reservation)

        //when

        //then
        val actual = christmasDdayEvent.isEligibleForEvent()
        assertThat(actual).isFalse()
    }

    @ParameterizedTest
    @ValueSource(ints = [25, 24, 23, 22, 21])
    fun `예약 날짜가 25일 이하일 경우 할인을 받지 못한다`(reservationDate: Int) {
        //given
        val reservation =
            Reservation(listOf(MenuItem.MUSHROOM_SOUP, MenuItem.MUSHROOM_SOUP), reservationDate)
        val christmasDdayEvent = ChristmasDdayEvent(reservation)

        //when

        //then
        val actual = christmasDdayEvent.isEligibleForEvent()
        assertThat(actual).isTrue()
    }

    @ParameterizedTest
    @ValueSource(ints = [25, 24, 23, 22, 21])
    fun `할인 금액은 1일 1000원을 기준으로 하루가 지날 때 마다 100원씩 늘어난다`(reservationDate: Int) {
        //given
        val reservation =
            Reservation(listOf(MenuItem.MUSHROOM_SOUP, MenuItem.MUSHROOM_SOUP), reservationDate)
        val christmasDdayEvent = ChristmasDdayEvent(reservation)

        //when
        val actual = christmasDdayEvent.calculateDiscountAmount()

        //then
        val expected = 1000 + (reservationDate - 1) * 100
        assertThat(actual).isEqualTo(expected)
    }
}