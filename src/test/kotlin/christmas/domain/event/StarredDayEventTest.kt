package christmas.domain.event

import christmas.domain.MenuItem
import christmas.domain.Reservation
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class StarredDayEventTest{
    @Test
    fun `예약 메뉴가 음료로만 되어 있을 경우 혜택을 받을 수 없다`() {
        //given
        val reservation = Reservation(listOf(MenuItem.CHAMPAGNE), 3)
        val starredDayEvent = StarredDayEvent(reservation = reservation)

        //when
        val actual = starredDayEvent.isEligibleForEvent()

        //then
        Assertions.assertThat(actual).isFalse()
    }

    @Test
    fun `총 예약 금액이 10000원 이하일 경우 예약이 불가능하다`() {
        //given
        val reservation = Reservation(listOf(MenuItem.MUSHROOM_SOUP), 3)
        val starredDayEvent = StarredDayEvent(reservation)

        //when
        val actual = starredDayEvent.isEligibleForEvent()

        //then
        Assertions.assertThat(actual).isFalse()
    }
}