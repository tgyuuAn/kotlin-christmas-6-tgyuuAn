package christmas.domain.badge

import christmas.domain.Badge
import christmas.domain.Benefit
import christmas.domain.Reservation
import christmas.domain.Badge.*
import christmas.domain.menu.MenuItem.*
import christmas.domain.menu.OrderMenu
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BadgeTest {
    @Test
    fun `총 할인 금액이 20000원 이상일 경우 산타 뱃지를 받는다`() {
        //given
        val reservation = Reservation(listOf(OrderMenu(ICE_CREAM, 10)), 3)
        val benefit = Benefit(reservation)

        //when
        val actual = Badge.getBadge(benefit)

        //then
        val expected = SANTA
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `총 할인 금액이 10000원 이상, 19999원 이하일 경우 트리 뱃지를 받는다`() {
        //given
        val reservation = Reservation(listOf(OrderMenu(ICE_CREAM, 5)), 3)
        val benefit = Benefit(reservation)

        //when
        val actual = Badge.getBadge(benefit)

        //then
        val expected = TREE
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `총 할인 금액이 5000원 이상, 9999원 이하일 경우 별 뱃지를 받는다`() {
        //given
        val reservation = Reservation(listOf(OrderMenu(ICE_CREAM, 3)), 3)
        val benefit = Benefit(reservation)

        //when
        val actual = Badge.getBadge(benefit)

        //then
        val expected = STAR
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `총 할인 금액이 5000원 미만일 경우 뱃지를 받지 못한다`() {
        //given
        val reservation = Reservation(listOf(OrderMenu(CHOCOLATE_CAKE, 1)), 3)
        val benefit = Benefit(reservation)

        //when
        val actual = Badge.getBadge(benefit)

        //then
        val expected = NOTHING
        assertThat(actual).isEqualTo(expected)
    }
}