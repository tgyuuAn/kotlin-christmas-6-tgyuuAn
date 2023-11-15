package christmas.validator

import christmas.domain.menu.MenuItem
import christmas.domain.menu.MenuItem.Companion.convertInputToMenuItemOrNull
import christmas.exception.ValidationException

object InputValidator {
    fun validateDateInput(input: String, message: String): Result<Int> {
        val date = validateInputIsInt(input, message).getOrThrow()
        return when(date){
            in 1..31 -> Result.success(date)
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