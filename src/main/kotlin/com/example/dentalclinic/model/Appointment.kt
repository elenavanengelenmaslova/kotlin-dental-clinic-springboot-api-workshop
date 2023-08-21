package com.example.dentalclinic.model

import java.time.LocalDateTime

class Appointment(
    val id: String,
    val patient: Patient,
    val dentist: Dentist,
    val time: LocalDateTime,
    val treatment: Treatment,
)