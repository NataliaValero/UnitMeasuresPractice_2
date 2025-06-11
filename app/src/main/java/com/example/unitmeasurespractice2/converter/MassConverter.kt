package com.example.unitmeasurespractice2.converter

import com.example.unitmeasurespractice2.model.ConversionResult
import com.example.unitmeasurespractice2.model.Mass
import com.example.unitmeasurespractice2.model.MeasureUnit

class MassConverter() : Converter {
    override fun convert(amount: Double, fromUnit: MeasureUnit): List<ConversionResult> {
        val unit = fromUnit as Mass
        return when(fromUnit) {
            Mass.KILOGRAM -> convertFromKilograms(amount)
            Mass.GRAM -> convertFromGrams(amount)
            Mass.POUND -> convertFromPounds(amount)
        }
    }

    private fun convertFromKilograms(amount: Double): List<ConversionResult> {
        val grams = amount * 1000
        val pounds = amount * 2.20462

        return listOf(
            ConversionResult(Mass.KILOGRAM, amount),
            ConversionResult(Mass.GRAM, grams),
            ConversionResult(Mass.POUND, pounds)
        )

    }

    private fun convertFromGrams(amount: Double): List<ConversionResult> {
        val kg = amount / 1000
        val pounds = kg * 2.20462
        return listOf(
            ConversionResult(Mass.KILOGRAM, kg),
            ConversionResult(Mass.GRAM, amount),
            ConversionResult(Mass.POUND, pounds)
        )
    }

    private fun convertFromPounds(amount: Double): List<ConversionResult> {
        val kg = amount / 2.20462
        val grams = kg * 1000
        return listOf(
            ConversionResult(Mass.KILOGRAM, kg),
            ConversionResult(Mass.GRAM, grams),
            ConversionResult(Mass.POUND, amount)
        )
    }
}