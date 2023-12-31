package christmas.domain.event

import christmas.domain.Reservation
import christmas.domain.event.Event.WEEK_DAY
import christmas.domain.event.WeekdayEvent.Companion.DISCOUNT_PER_BEVERAGE
import christmas.domain.menu.MenuItem.CHAMPAGNE
import christmas.domain.menu.MenuItem.CHOCOLATE_CAKE
import christmas.domain.menu.MenuItem.MUSHROOM_SOUP
import christmas.domain.menu.OrderMenu
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class WeekdayEventTest {

    private val eventType = WEEK_DAY

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 8, 9, 15, 16, 22, 23, 29, 30])
    fun `평일 이벤트는 주말에 할인 혜택을 받을 수 없다`(reservationDate: Int) {
        //given
        val reservation = Reservation(listOf(OrderMenu(MUSHROOM_SOUP, 2)), reservationDate)
        val weekdayEvent = WeekdayEvent(event = eventType, reservation = reservation)

        //when
        val actual = weekdayEvent.isEligibleForEvent()

        //then
        assertThat(actual).isFalse
    }

    @Test
    fun `예약 메뉴가 음료로만 되어 있을 경우 할인 혜택을 받을 수 없다`() {
        //given
        val reservation = Reservation(listOf(OrderMenu(CHAMPAGNE, 1)), 3)
        val weekdayEvent = WeekdayEvent(event = eventType, reservation = reservation)

        //when
        val actual = weekdayEvent.isEligibleForEvent()

        //then
        assertThat(actual).isFalse
    }

    @Test
    fun `총 예약 금액이 10000원 이하일 경우 할인 혜택을 받을 수 없다`() {
        //given
        val reservation = Reservation(listOf(OrderMenu(MUSHROOM_SOUP, 1)), 3)
        val weekdayEvent = WeekdayEvent(event = eventType, reservation = reservation)

        //when
        val actual = weekdayEvent.isEligibleForEvent()

        //then
        assertThat(actual).isFalse
    }

    @ParameterizedTest
    @ValueSource(ints = [3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31])
    fun `평일 이벤트는 평일에 할인 혜택을 받을 수 있다`(reservationDate: Int) {
        //given
        val reservation = Reservation(listOf(OrderMenu(MUSHROOM_SOUP, 2)), reservationDate)
        val weekdayEvent = WeekdayEvent(event = eventType, reservation = reservation)

        //when
        val actual = weekdayEvent.isEligibleForEvent()

        //then
        assertThat(actual).isTrue
    }

    @Test
    fun `디저트 메뉴가 예약 메뉴에 없을 경우 할인 금액은 없다`() {
        //given
        val reservation = Reservation(listOf(OrderMenu(MUSHROOM_SOUP, 2)), 1)
        val weekdayEvent = WeekdayEvent(event = eventType, reservation = reservation)

        //when
        val actual = weekdayEvent.calculateDiscountAmount()

        //then
        val expected = 0
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `디저트 메뉴가 예약 메뉴에 있을 한 개만 있을 경우 2023원이 할인된다`() {
        //given
        val reservation = Reservation(listOf(OrderMenu(CHOCOLATE_CAKE, 1)), 1)
        val weekdayEvent = WeekdayEvent(event = eventType, reservation = reservation)

        //when
        val actual = weekdayEvent.calculateDiscountAmount()

        //then
        val expected = DISCOUNT_PER_BEVERAGE
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `디저트 메뉴가 예약 메뉴에 있을 경우 하나 당 2023원이 할인된다`() {
        //given
        val reservation = Reservation(listOf(OrderMenu(CHOCOLATE_CAKE, 2)), 1)
        val weekdayEvent = WeekdayEvent(event = eventType, reservation = reservation)

        //when
        val actual = weekdayEvent.calculateDiscountAmount()

        //then
        val expected = DISCOUNT_PER_BEVERAGE * 2
        assertThat(actual).isEqualTo(expected)
    }
}