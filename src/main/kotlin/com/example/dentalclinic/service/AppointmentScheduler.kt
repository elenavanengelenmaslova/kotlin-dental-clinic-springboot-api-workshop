package com.example.dentalclinic.service

import com.example.dentalclinic.api.dto.AppointmentRequest
import com.example.dentalclinic.api.dto.AppointmentResponse
import com.example.dentalclinic.model.Clinic
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import java.util.*

interface AppointmentScheduler {
    fun schedule(
        appointmentRequest: AppointmentRequest,
    ): AppointmentResponse {
        val result = Clinic.scheduleAppointment(
            appointmentRequest.patientId,
            appointmentRequest.dentalPractitionerId,
            appointmentRequest.dateTime,
            appointmentRequest.treatmentId
        )

        return when (result) {
            is ScheduleResult.Success -> AppointmentResponse(
                id = result.appointmentId,
                message = successMessage(),
            )
            is ScheduleResult.TimeSlotUnavailable -> AppointmentResponse(
                message = "Time slot unavailable!",
            )
            is ScheduleResult.TreatmentUnavailable -> {
                val ids =
                    result.availableTreatments.map { it.id }
                val msg =
                    "Treatment unavailable! Available treatments: $ids"
                AppointmentResponse(message = msg)
            }
            is ScheduleResult.DentalPractitionerUnavailable -> {
                val ids =
                    result.availableDentalPractitioners.map { it.id }
                val msg =
                    "Practitioner unavailable! Who is available: $ids"
                AppointmentResponse(message = msg)
            }
            is ScheduleResult.NotFound -> throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                result.message,
            )
        }
    }

    fun successMessage(): String
}