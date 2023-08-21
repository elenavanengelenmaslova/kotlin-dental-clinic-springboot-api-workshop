package com.example.dentalclinic.annotations

import jakarta.validation.Constraint

@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY_GETTER
)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [PatientIdValidator::class])
annotation class ValidPatientId(
    val message: String = "Invalid patient id"
)