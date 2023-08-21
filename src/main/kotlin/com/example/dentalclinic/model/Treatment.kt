package com.example.dentalclinic.model

class Treatment(
    val id: String, val name: String,
    val type: TreatmentType,
) {
    val duration = type.duration
    // the rest of the class
}