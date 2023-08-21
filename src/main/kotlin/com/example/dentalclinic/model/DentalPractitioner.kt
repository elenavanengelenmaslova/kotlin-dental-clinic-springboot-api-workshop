package com.example.dentalclinic.model

interface DentalPractitioner {
    fun performTreatment(patient: Patient, treatment: Treatment): String
}