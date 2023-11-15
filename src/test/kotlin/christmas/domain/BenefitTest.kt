package christmas.domain

import christmas.domain.menu.MenuItem
import christmas.domain.menu.OrderMenu
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.math.exp

class BenefitTest {
    @Test
    fun `예약을 받을 때 해당 예약에 대한 적용 가능한 혜택들이 계산된다`() {
        //given
        val reservation = Reservation(listOf(OrderMenu(MenuItem.MUSHROOM_SOUP, 2)), 25)
        val benefit = Benefit(reservation)

        //when
        val actual = benefit.getTotalDiscountedAmount()

        //then
        val expected = 4400
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `예약 받은 내역들이 차례대로 다 보여진다`() {
        //given
        val reservation = Reservation(listOf(OrderMenu(MenuItem.MUSHROOM_SOUP, 2)), 25)
        val benefit = Benefit(reservation)

        //when
        val actual = benefit.toString()

        //then
        assertThat(actual).contains(
            "크리스마스 디데이 할인: -3,400원",
            "특별 할인: -1,000원",
        )
    }

    @Test
    fun `할인 받은 혜택이 없는 경우 없음으로 출력된다`() {
        //given
        val reservation = Reservation(listOf(OrderMenu(MenuItem.MUSHROOM_SOUP, 1)), 25)
        val benefit = Benefit(reservation)

        //when
        val actual = benefit.toString()

        //then
        val expected = "없음"
        assertThat(actual).isEqualTo(expected)
    }
}