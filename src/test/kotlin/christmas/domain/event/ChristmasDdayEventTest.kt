package christmas.domain.event

import christmas.domain.event.EventType.*
import christmas.domain.menu.MenuItem.*
import christmas.domain.Reservation
import christmas.domain.menu.OrderMenu
import christmas.util.MoneyUtil.HUNDRED_UNIT
import christmas.util.MoneyUtil.THOUSAND_UNIT
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ChristmasDdayEventTest {

    private val eventType = CHRISTMAS_D_DAY

    @ParameterizedTest
    @ValueSource(ints = [26, 27, 28, 29, 30, 31])
    fun `예약 날짜가 26일 이상일 경우 할인 혜택을 받을 수 없다`(reservationDate: Int) {
        //given
        val reservation =
            Reservation(listOf(OrderMenu(MUSHROOM_SOUP, 2)), reservationDate)
        val christmasDdayEvent =
            ChristmasDdayEvent(eventType = eventType, reservation = reservation)

        //when

        //then
        val actual = christmasDdayEvent.isEligibleForEvent()
        assertThat(actual).isFalse()
    }

    @ParameterizedTest
    @ValueSource(ints = [25, 24, 23, 22, 21])
    fun `예약 날짜가 25일 이하일 경우 할인 혜택을 받을 수 있다`(reservationDate: Int) {
        //given
        val reservation =
            Reservation(listOf(OrderMenu(MUSHROOM_SOUP, 2)), reservationDate)
        val christmasDdayEvent =
            ChristmasDdayEvent(eventType = eventType, reservation = reservation)

        //when

        //then
        val actual = christmasDdayEvent.isEligibleForEvent()
        assertThat(actual).isTrue()
    }

    @Test
    fun `총 음식 예약 금액이 10000원 이하일 경우 할인 혜택을 받을 수 없다`() {
        //given
        val reservation =
            Reservation(listOf(OrderMenu(MUSHROOM_SOUP, 1)), 3)
        val christmasDdayEvent =
            ChristmasDdayEvent(eventType = eventType, reservation = reservation)

        //when
        val actual = christmasDdayEvent.isEligibleForEvent()

        //then
        assertThat(actual).isFalse()
    }

    @Test
    fun `예약 메뉴가 음료로만 되어 있을 경우 할인 혜택을 받을 수 없다`() {
        //given
        val reservation = Reservation(listOf(OrderMenu(CHAMPAGNE, 1)), 3)
        val christmasDdayEvent =
            ChristmasDdayEvent(eventType = eventType, reservation = reservation)

        //when
        val actual = christmasDdayEvent.isEligibleForEvent()

        //then
        assertThat(actual).isFalse()
    }


    @Test
    fun `총 음식 예약 금액이 10000원 이상이고, 25일 이하면 할인 혜택을 받을 수 있다`() {
        //given
        val reservation =
            Reservation(listOf(OrderMenu(MUSHROOM_SOUP, 2)), 3)
        val christmasDdayEvent =
            ChristmasDdayEvent(eventType = eventType, reservation = reservation)

        //when
        val actual = christmasDdayEvent.isEligibleForEvent()

        //then
        assertThat(actual).isTrue()
    }

    @ParameterizedTest
    @ValueSource(ints = [25, 24, 23, 22, 21])
    fun `할인 금액은 1일 1000원을 기준으로 하루가 지날 때 마다 100원씩 늘어난다`(reservationDate: Int) {
        //given
        val reservation =
            Reservation(listOf(OrderMenu(MUSHROOM_SOUP, 1)), reservationDate)
        val christmasDdayEvent =
            ChristmasDdayEvent(eventType = eventType, reservation = reservation)

        //when
        val actual = christmasDdayEvent.calculateDiscountAmount()

        //then
        val expected = THOUSAND_UNIT + (reservationDate - 1) * HUNDRED_UNIT
        assertThat(actual).isEqualTo(expected)
    }
}