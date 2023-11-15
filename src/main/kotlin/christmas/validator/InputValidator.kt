package christmas.validator

import christmas.domain.menu.MenuItem
import christmas.domain.menu.MenuItem.Companion.convertStringToMenuItemOrNull
import christmas.exception.ValidationException

object InputValidator {
    fun validateInputIsInt(input: String, message: String): Result<Int> =
        input.toIntOrNull()?.let {
            Result.success(it)
        } ?: Result.failure(ValidationException(message))

    fun validateInputIsMenuItem(input: String, message: String): Result<MenuItem> =
        convertStringToMenuItemOrNull(input)?.let {
            Result.success(it)
        } ?: Result.failure(ValidationException(message))
}