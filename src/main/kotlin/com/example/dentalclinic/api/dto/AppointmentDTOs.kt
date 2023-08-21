package com.example.dentalclinic.api.dto

import java.time.LocalDateTime

data class AppointmentRequest(
    val patientId: String,
    val dentalPractitionerId: String,
    val date: LocalDateTime,
    val treatmentType: String,
)

data class AppointmentResponse(
    val id: String? = null,
    val message: String,
)