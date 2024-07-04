package br.ufrn.imd.obama.usuario.infrastructure.annotation

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class PasswordConstraintValidator : ConstraintValidator<ValidPassword, String> {

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if (value == null) {
            return false
        }

        val hasUppercase = value.any { it.isUpperCase() }
        val hasLowercase = value.any { it.isLowerCase() }
        val hasNumber = value.any { it.isDigit() }
        val hasSpecial = value.any { !it.isLetterOrDigit() }

        return hasUppercase && hasLowercase && hasNumber && hasSpecial
    }
}
