package com.example.dentalclinic.service.impl

import com.example.dentalclinic.service.AppointmentScheduler
import org.springframework.stereotype.Service

@Service("defaultScheduler")
class DefaultAppointmentScheduler :
    AppointmentScheduler {
    override fun successMessage() = "Scheduled on a weekday!"
}