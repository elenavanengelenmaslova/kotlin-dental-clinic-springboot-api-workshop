package com.example.dentalclinic.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class PersonTest {
    @Test
    fun personName() {
        // test a valid name, using secondary constructor
        val hygienist = Hygienist("4", "Mary")
        assertEquals("Mary", hygienist.name.value)
        assertNull(hygienist.email)

        // test invalid names will throw IllegalArgumentException
        assertThrows<IllegalArgumentException> {
            Hygienist(
                "4",
                ""
            )
        }
        assertThrows<IllegalArgumentException> {
            Hygienist(
                "4",
                "123invalid"
            )
        }
    }
}