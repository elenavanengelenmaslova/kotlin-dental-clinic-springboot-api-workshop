package com.example.dentalclinic.api.controller

import com.example.dentalclinic.api.dto.AppointmentRequest
import com.example.dentalclinic.api.dto.AppointmentResponse
import com.example.dentalclinic.service.AppointmentScheduler
import com.example.dentalclinic.service.impl.DefaultAppointmentScheduler
import com.example.dentalclinic.service.impl.WeekendAppointmentScheduler
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.DayOfWeek
import java.time.LocalDateTime

@RestController
@RequestMapping("/appointments")
class AppointmentController(
    val appointmentSchedulers: List<AppointmentScheduler>,
) {
    @PostMapping
    fun scheduleAppointment(
        @RequestBody appointmentRequest: AppointmentRequest,
    ): ResponseEntity<AppointmentResponse> {
        val appointmentScheduler =
            findSchedulerFor(appointmentRequest.date)
        return ResponseEntity.ok(
            appointmentScheduler.schedule(
                appointmentRequest
            )
        )
    }

    private fun findSchedulerFor( // Determine the correct scheduler
        date: LocalDateTime,
    ): AppointmentScheduler {
        val weekend =
            listOf(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)
        return if (date.dayOfWeek in weekend) {
            appointmentSchedulers.first {
                it is WeekendAppointmentScheduler
            }
        } else {
            appointmentSchedulers.first {
                it is DefaultAppointmentScheduler
            }
        }
    }
}