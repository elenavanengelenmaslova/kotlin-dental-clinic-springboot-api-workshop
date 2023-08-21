package com.example.dentalclinic.service

import com.example.dentalclinic.api.dto.AppointmentRequest
import com.example.dentalclinic.api.dto.AppointmentResponse
import java.util.*

interface AppointmentScheduler {
    fun schedule(
        appointmentRequest: AppointmentRequest,
    ): AppointmentResponse {
        return AppointmentResponse(
            id = UUID.randomUUID().toString(),
            message = successMessage()
        )
    }

    fun successMessage(): String
}