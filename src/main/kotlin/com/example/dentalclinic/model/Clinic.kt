package com.example.dentalclinic.model

import com.example.dentalclinic.service.PersonManager
import java.time.LocalDateTime
import java.util.*

object Clinic {
    val patients = PersonManager<Patient>()
    val dentists = PersonManager<Dentist>()
    val treatments = mutableMapOf<String, Treatment>()
    val appointments = mutableListOf<Appointment>()

    fun addTreatment(treatment: Treatment) =
        treatments.put(treatment.id, treatment)

    fun scheduleAppointment(
        patientId: String,
        dentistId: String,
        time: LocalDateTime,
        treatmentId: String,
    ) {
        val patient = patients.getPersonById(patientId)
            ?: throw IllegalArgumentException("Patient not found")
        val dentist = dentists.getPersonById(dentistId)
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

inline fun <reified T> findPersonAcrossManagers(
    id: String,
    vararg managers: PersonManager<*>,
): T? {
    for (manager in managers) {
        val person = manager.getPersonById(id)
        if (person != null && person is T) {
            return person
        }
    }
    return null
}