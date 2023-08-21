package com.example.dentalclinic.model

import java.time.Duration

enum class TreatmentType(
    val displayName: String,
    val duration: Duration,
) {
    CHECK_UP("Check-up", Duration.ofMinutes(30)),
    CLEANING("Cleaning", Duration.ofMinutes(60)),
    TOOTH_EXTRACTION("Tooth Extraction", Duration.ofMinutes(90)),
    FILLING("Filling", Duration.ofMinutes(120)),
    ROOT_CANAL("Root Canal", Duration.ofMinutes(120));
}