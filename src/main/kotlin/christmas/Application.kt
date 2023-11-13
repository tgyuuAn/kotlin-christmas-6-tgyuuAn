package christmas

import christmas.view.InputView

fun main() {
    InputView().apply{
        readDate()
        readOrder()
    }
}
