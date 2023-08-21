package com.example.dentalclinic.model

import com.example.dentalclinic.service.PersonManager
import com.example.dentalclinic.service.ScheduleResult
import java.time.LocalDateTime
import java.util.*

object Clinic {
    val patients = PersonManager<Patient>()
    val dentists = PersonManager<Dentist>()
    val hygienists = PersonManager<Hygienist>()
    val treatments = mutableMapOf<String, Treatment>()
    val appointments = mutableListOf<Appointment>()

    fun addTreatment(treatment: Treatment) =
        treatments.put(treatment.id, treatment)

    fun scheduleAppointment(
        patientId: String,
        dentalPractitionerId: String,
        time: LocalDateTime,
        treatmentId: String,
    ) : ScheduleResult {
        val patient =
            Clinic.patients.getPersonById(patientId)
                ?: return ScheduleResult.NotFound(
                    "Patient not found"
                )

        val treatment = Clinic.treatments[treatmentId]
            ?: return ScheduleResult.TreatmentUnavailable(
                Clinic.treatments.values.toList()
            )

        val dentalPractitioner: DentalPractitioner =
            findPersonAcrossManagers(
                dentalPractitionerId,
                Clinic.dentists,
                Clinic.hygienists
            ) ?: return ScheduleResult.NotFound(
                "Dental practitioner not found"
            )
        if (Clinic.isAvailable(dentalPractitioner)) {
            val appointmentId = UUID.randomUUID().toString()
            Clinic.appointments.add(
                Appointment(
                    appointmentId,
                    patient,
                    dentalPractitioner,
                    time,
                    treatment,
                )
            )
            return ScheduleResult.Success(appointmentId)
        } else {
            val availableDentists =
                Clinic.dentists.findPersonsByCriteria {
                    Clinic.isAvailable(it)
                }
            val availableHygienists =
                Clinic.hygienists.findPersonsByCriteria {
                    Clinic.isAvailable(it)
                }
            return ScheduleResult.DentalPractitionerUnavailable(
                availableDentists + availableHygienists
            )
        }
    }

    private fun isAvailable(person: DentalPractitioner): Boolean {
        return Character.getNumericValue(person.id.last()) % 2 == 0
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