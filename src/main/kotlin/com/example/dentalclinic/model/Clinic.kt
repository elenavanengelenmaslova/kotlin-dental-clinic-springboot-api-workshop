package com.example.dentalclinic.model

import java.time.LocalDateTime
import java.util.*

object Clinic {
    val patients = mutableMapOf<String, Patient>()
    val dentists = mutableMapOf<String, Dentist>()
    val treatments = mutableMapOf<String, Treatment>()
    val appointments = mutableListOf<Appointment>()

    fun addPatient(patient: Patient) = patients.put(patient.id, patient)
    fun addDentist(dentist: Dentist) = dentists.put(dentist.id, dentist)
    fun addTreatment(treatment: Treatment) = treatments.put(treatment.id, treatment)

    fun scheduleAppointment(patientId: String, dentistId: String, time: LocalDateTime, treatmentId: String) {
        val patient = patients[patientId]
            ?: throw IllegalArgumentException("Patient not found")
        val dentist = dentists[dentistId]
            ?: throw IllegalArgumentException("Dentist not found")
        val treatment = treatments[treatmentId]
            ?: throw IllegalArgumentException("Treatment not found")
        appointments.add(
            Appointment(
                UUID.randomUUID().toString(),
                patient,
                dentist,
                time,
                treatment,
            )
        )
    }
}