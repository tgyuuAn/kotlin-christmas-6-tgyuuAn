package christmas.domain.event

import christmas.domain.event.EventType.*
import christmas.domain.menu.MenuItem.*
import christmas.domain.Reservation
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class StarredDayEventTest {

    private val eventType = STARRED_DAY

    @Test
    fun `예약 메뉴가 음료로만 되어 있을 경우 혜택을 받을 수 없다`() {
        //given
        val reservation = Reservation(listOf(CHAMPAGNE), 3)
        val starredDayEvent = StarredDayEvent(eventType = eventType, reservation = reservation)

        //when
        val actual = starredDayEvent.isEligibleForEvent()

        //then
        Assertions.assertThat(actual).isFalse()
    }

    @Test
    fun `총 예약 금액이 10000원 이하일 경우 예약이 불가능하다`() {
        //given
        val reservation = Reservation(listOf(MUSHROOM_SOUP), 3)
        val starredDayEvent = StarredDayEvent(eventType = eventType, reservation = reservation)

        //when
        val actual = starredDayEvent.isEligibleForEvent()

        //then
        Assertions.assertThat(actual).isFalse()
    }

    @ParameterizedTest
    @ValueSource(ints = [3, 10, 17, 24, 25, 31])
    fun `예약 날짜가 별표된 날짜 일 경우 할인 혜택을 받는다`(reservationDate: Int) {
        //given
        val reservation = Reservation(listOf(BARBECUE_RIB), reservationDate)
        val starredDayEvent = StarredDayEvent(eventType = eventType, reservation = reservation)

        //when
        val actual = starredDayEvent.isEligibleForEvent()

        //then
        Assertions.assertThat(actual).isTrue()
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 4, 5, 6, 7, 8, 9, 11, 12, 13, 14, 15, 16, 18, 19, 20, 21, 22, 23, 26, 27, 28, 29, 30])
    fun `예약 날짜가 별표된 날짜가 아닐 경우 할인 혜택을 받지 못한다`(reservationDate: Int) {
        //given
        val reservation = Reservation(listOf(BARBECUE_RIB), reservationDate)
        val starredDayEvent = StarredDayEvent(eventType = eventType, reservation = reservation)

        //when
        val actual = starredDayEvent.isEligibleForEvent()

        //then
        Assertions.assertThat(actual).isFalse()
    }
}