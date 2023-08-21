package com.example.dentalclinic.model

class Dentist(
    id: String,
    name: String,
) : Person(id, name) {
    var treatmentCount = 0
        private set

    fun performTreatment(patient: Patient, treatment: Treatment) {
        println("Performing ${treatment.name} on ${patient.name}")
        treatmentCount++
        // additional code to handle the treatment process
        patient.receiveTreatment(treatment)
    }

    override fun introduce() = "${super.introduce()} I'm a dentist."

}