package com.example.unitmeasurespractice2.model

enum class MeasureUnit(val displayName: String, val symbol: String, val category: MeasureCategory) {

    // MASS units
    KILOGRAM("Kilogram", "kg", MeasureCategory.MASS),
    GRAM("Gram", "g", MeasureCategory.MASS),
    POUND("Pound", "lb", MeasureCategory.MASS),

    // LENGTH units
    METER("Meter", "m", MeasureCategory.LENGTH),
    CENTIMETER("Centimeter", "cm", MeasureCategory.LENGTH),
    INCH("Inch", "in", MeasureCategory.LENGTH);

    companion object {
        fun getUnitsByCategory(category: MeasureCategory): List<MeasureUnit> {
            return entries.filter { it.category == category }
        }
    }
}

