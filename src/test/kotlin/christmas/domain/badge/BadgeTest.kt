package christmas.domain.badge

import christmas.domain.Benefit
import christmas.domain.badge.Badge.*
import christmas.domain.event.Event
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BadgeTest {
    @Test
    fun `총 할인 금액이 20000원 이상일 경우 산타 뱃지를 받는다`() {
        //given
        val benefit = Benefit()
        benefit.accumulateBenefit(Event.FREEBIE, 25000)

        //when
        val actual =Badge.getBadge(benefit)

        //then
        val expected = SANTA
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `총 할인 금액이 10000원 이상일 경우 트리 뱃지를 받는다`() {
        //given
        val benefit = Benefit()
        benefit.accumulateBenefit(Event.WEEKEND, 10000)

        //when
        val actual =Badge.getBadge(benefit)

        //then
        val expected = TREE
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `총 할인 금액이 5000원 이상일 경우 별 뱃지를 받는다`() {
        //given
        val benefit = Benefit()
        benefit.accumulateBenefit(Event.WEEKEND, 7000)

        //when
        val actual =Badge.getBadge(benefit)

        //then
        val expected = STAR
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `총 할인 금액이 5000원 미만일 경우 뱃지를 받지 못한다`() {
        //given
        val benefit = Benefit()
        benefit.accumulateBenefit(Event.WEEKEND, 3000)

        //when
        val actual =Badge.getBadge(benefit)

        //then
        val expected = NOTHING
        assertThat(actual).isEqualTo(expected)
    }
}