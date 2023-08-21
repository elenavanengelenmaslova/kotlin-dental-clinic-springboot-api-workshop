package com.example.dentalclinic.model

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class PatientTest {

    @Test
    fun introduce() {
        val patient = Patient("1", "John", "Insurance1")
        val patientIntro =
            "Hello, my name is John. I am a patient."
        assertEquals(patientIntro, patient.introduce())
    }
}