package christmas.validator

import christmas.domain.menu.MenuItem
import christmas.domain.menu.MenuItem.Companion.convertInputToMenuItemOrNull
import christmas.exception.ValidationException
import christmas.util.Calendar.DECEMBER_END_DAY
import christmas.util.Calendar.DECEMBER_START_DAY

object InputValidator {
    fun validateDateInput(input: String, message: String): Result<Int> {
        val date = validateInputIsInt(input, message).getOrThrow()
        return when (date) {
            in DECEMBER_START_DAY..DECEMBER_END_DAY -> Result.success(date)
            else -> Result.failure(ValidationException(message))
        }
    }

    fun validateInputIsInt(input: String, message: String): Result<Int> =
        input.toIntOrNull()?.let {
            Result.success(it)
        } ?: Result.failure(ValidationException(message))

    fun validateInputIsMenuItem(input: String, message: String): Result<MenuItem> =
        convertInputToMenuItemOrNull(input)?.let {
            Result.success(it)
        } ?: Result.failure(ValidationException(message))
}