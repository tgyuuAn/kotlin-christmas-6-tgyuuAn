package christmas.validator

import christmas.domain.menu.MenuItem
import christmas.domain.menu.MenuItem.Companion.convertStringToMenuItemOrNull

object InputValidator {
    fun validateInputIsInt(input: String, message: String): Result<Int> =
        input.toIntOrNull()?.let {
            Result.success(it)
        } ?: Result.failure(IllegalArgumentException("[ERROR] " + message + " 다시 입력해 주세요."))

    fun validateInputIsMenuItem(input: String, message: String): Result<MenuItem> =
        convertStringToMenuItemOrNull(input)?.let {
            Result.success(it)
        } ?: Result.failure(IllegalArgumentException("[ERROR] " + message + " 다시 입력해 주세요."))
}