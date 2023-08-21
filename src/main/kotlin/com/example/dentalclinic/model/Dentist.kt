package com.example.dentalclinic.model

class Dentist(val id: String, val name: String) {
    var treatmentCount = 0
        private set

    fun performTreatment(patient: Patient, treatment: Treatment) {
        println("Performing ${treatment.name} on ${patient.name}")
        treatmentCount++
        // additional code to handle the treatment process
        patient.receiveTreatment(treatment)
    }
}