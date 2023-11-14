package christmas.util

import christmas.util.OrderParser.parseOrders
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class OrderParserTest {
    @Test
    fun `메뉴를 주문할 때, 메뉴 개수로 문자를 넣으면 에러를 반환한다`() {
        //given
        val input = "타파스-가,제로콜라-나"

        //when

        //then
        assertThrows<IllegalArgumentException> {
            parseOrders(input)
        }
    }

    @Test
    fun `메뉴를 주문할 때, 메뉴 개수로 숫자를 넣으면 에러를 반환하지 않는다`() {
        //given
        val input = "타파스-1,제로콜라-2"

        //when

        //then
        assertDoesNotThrow {
            parseOrders(input)
        }
    }

    @Test
    fun `메뉴를 주문할 때, 메뉴판에 없는 메뉴를 주문히면 에러를 반환한다`() {
        //given
        val input = "tgyuu-1,제로콜라-2"

        //when

        //then
        assertThrows<IllegalArgumentException> {
            parseOrders(input)
        }
    }

    @Test
    fun `메뉴를 주문할 때, 메뉴판에 있는 메뉴를 주문히면 에러를 반환하지 않는다`() {
        //given
        val input = "티본스테이크-1,제로콜라-2"

        //when

        //then
        assertDoesNotThrow {
            parseOrders(input)
        }
    }
}