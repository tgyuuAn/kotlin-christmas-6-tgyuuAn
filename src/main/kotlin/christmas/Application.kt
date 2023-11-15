package christmas

import christmas.controller.EventPlannerController
import christmas.view.InputView
import christmas.view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    EventPlannerController(inputView = inputView, outputView = outputView).run()
}
