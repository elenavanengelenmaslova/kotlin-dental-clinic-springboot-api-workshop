package com.example.dentalclinic.model

class Receptionist(
    id: String,
    name: String,
    override val role: String = "Receptionist"
) : Person(id, name), Employee {
    override fun scheduleLeave(days: Int) {
        println("Receptionist $name scheduled leave for $days days.")
    }
    // Receptionist specific methods here
}