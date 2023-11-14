package christmas.domain.validator

import christmas.domain.menu.MenuItem

object InputValidator {
    fun validateInputIsInt(input: String, message: String): Result<Int> =
        input.toIntOrNull()?.let {
            Result.success(it)
        } ?: Result.failure(IllegalArgumentException("[ERROR]" + message + " 다시 입력해 주세요."))

    fun validateInputIsMenuItem(input: String, message: String): Result<MenuItem> =
        convertStringToMenuItemOrNull()?.let {
            Result.success(it)
        } ?: Result.failure(IllegalArgumentException("[ERROR]" + message + " 다시 입력해 주세요."))
}