package com.example.dentalclinic.model

class DentalRecord(val patientId: String) {
    private val _treatmentHistory = mutableListOf<Treatment>()
    val medicalConditions = mutableListOf<String>()
    val treatmentHistory: List<Treatment>
        get() = _treatmentHistory

    fun addTreatment(treatment: Treatment) {
        _treatmentHistory.add(treatment)
    }
}