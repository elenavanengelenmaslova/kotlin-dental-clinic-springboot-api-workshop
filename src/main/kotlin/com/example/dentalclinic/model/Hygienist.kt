package com.example.dentalclinic.model

class Hygienist(
    id: String,
    name: String,
    override val role: String = "Hygienist",
) : Person(id, name), Employee, DentalPractitioner {
    override fun scheduleLeave(days: Int) {
        println("Hygienist $name scheduled leave for $days days.")
    }

    override fun performTreatment(
        patient: Patient,
        treatment: Treatment,
    ): String {
        val message = "Hygienist $name is performing ${treatment.name}"
        println(message)
        patient.receiveTreatment(treatment)
        return message
    }
}