plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    // No necesitas kotlin-compose aquí a menos que lo uses en otros módulos
}