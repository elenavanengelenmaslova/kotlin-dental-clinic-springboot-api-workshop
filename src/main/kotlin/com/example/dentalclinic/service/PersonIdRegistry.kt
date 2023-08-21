package com.example.dentalclinic.service

object PersonIdRegistry {
    private val registeredIDs = mutableSetOf<String>()

    fun registerID(id: String) {
        if (!registeredIDs.add(id)) {
            throw IllegalArgumentException("ID already exists: $id")
        }
    }

    fun unregisterID(id: String) {
        registeredIDs.remove(id)
    }
}