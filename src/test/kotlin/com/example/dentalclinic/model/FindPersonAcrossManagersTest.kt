package com.example.dentalclinic.model

import com.example.dentalclinic.service.PersonManager
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class FindPersonAcrossManagersTest {
    private lateinit var patient1: Patient
    private lateinit var dentist1: Dentist
    private lateinit var patientManager: PersonManager<Patient>
    private lateinit var dentistManager: PersonManager<Dentist>

    @BeforeEach
    fun setUp() {
        patientManager = PersonManager()
        dentistManager = PersonManager()
        patient1 = Patient(id = "p1", name = "John Doe")
        dentist1 = Dentist(id = "d1", name = "Dr. Smith")
        patientManager.addPerson(patient1)
        dentistManager.addPerson(dentist1)
    }

    @Test
    fun findPersonAcrossManagers() {
        val foundPerson: Person? =
            findPersonAcrossManagers(
                id = "p1", patientManager, dentistManager
            )
        assertNotNull(foundPerson)
        assertTrue(foundPerson is Patient)
    }

    @AfterEach
    fun tearDown() {
        patientManager.removePerson(patient1.id)
        dentistManager.removePerson(dentist1.id)
    }
}