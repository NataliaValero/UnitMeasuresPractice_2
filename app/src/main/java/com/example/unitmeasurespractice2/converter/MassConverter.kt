package com.example.unitmeasurespractice2.converter

import com.example.unitmeasurespractice2.model.ConversionResult
import com.example.unitmeasurespractice2.model.MeasureUnit

class MassConverter() : Converter {
    override fun convert(amount: Double, fromUnit: MeasureUnit): List<ConversionResult> {
        return when(fromUnit) {
            MeasureUnit.KILOGRAM -> convertFromKilograms(amount)
            MeasureUnit.GRAM -> convertFromGrams(amount)
            MeasureUnit.POUND -> convertFromPounds(amount)
            else -> emptyList()
        }
    }

    private fun convertFromKilograms(amount: Double): List<ConversionResult> {
        val grams = amount * 1000
        val pounds = amount * 2.20462

        return listOf(
            ConversionResult(MeasureUnit.KILOGRAM, amount),
            ConversionResult(MeasureUnit.GRAM, grams),
            ConversionResult(MeasureUnit.POUND, pounds)
        )

    }

    private fun convertFromGrams(amount: Double): List<ConversionResult> {
        val kg = amount / 1000
        val pounds = kg * 2.20462
        return listOf(
            ConversionResult(MeasureUnit.KILOGRAM, kg),
            ConversionResult(MeasureUnit.GRAM, amount),
            ConversionResult(MeasureUnit.POUND, pounds)
        )
    }

    private fun convertFromPounds(amount: Double): List<ConversionResult> {
        val kg = amount / 2.20462
        val grams = kg * 1000
        return listOf(
            ConversionResult(MeasureUnit.KILOGRAM, kg),
            ConversionResult(MeasureUnit.GRAM, grams),
            ConversionResult(MeasureUnit.POUND, amount)
        )
    }
}