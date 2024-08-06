package br.ufrn.imd.obama.usuario.infrastructure.annotation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [PasswordConstraintValidator::class])
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class ValidPassword(
    val message: String = "Invalid Password",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
