package christmas.domain

import christmas.domain.event.Event
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BenefitTest {
    @Test
    fun `총 혜택 금액은 계속해서 누적되어 간다`() {
        //given
        val benefit = Benefit()

        //when
        benefit.accumulateBenefit(Event.CHRISTMAS_D_DAY, 2500)
        benefit.accumulateBenefit(Event.WEEK_DAY, 2023)

        //then
        val actual = benefit.getTotalDiscountedAmount()
        val expected = 2500 + 2023
        assertThat(actual).isEqualTo(expected)
    }
}