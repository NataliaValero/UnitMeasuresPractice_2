package com.example.unitmeasurespractice2.model

sealed interface MeasureUnit {
    val displayName: String
    val symbol: String

    companion object {
        fun getUnitsByCategory(category: MeasureCategory) : List<MeasureUnit> {
            return when (category) {
                MeasureCategory.MASS -> Mass.entries
                MeasureCategory.LENGTH -> Length.entries
            }
        }
    }
}

enum class Mass(
    override val displayName: String,
    override val symbol: String
) : MeasureUnit{
    KILOGRAM("Kilogram", "kg"),
    GRAM("Gram", "g"),
    POUND("Pound", "lb");
}

enum class Length(
    override val displayName: String,
    override val symbol: String
) : MeasureUnit{
    METER("Meter", "m"),
    CENTIMETER("Centimeter", "cm"),
    INCH("Inch", "in");
}


