package com.example.unitmeasurespractice2.converter

import com.example.unitmeasurespractice2.model.ConversionResult
import com.example.unitmeasurespractice2.model.MeasureUnit

class LengthConverter : Converter{
    override fun convert(amount: Double, fromUnit: MeasureUnit): List<ConversionResult> {

        return when(fromUnit) {
            MeasureUnit.METER -> convertFromMeters(amount)
            MeasureUnit.CENTIMETER -> convertFromCentimeters(amount)
            MeasureUnit.INCH -> convertFromInches(amount)
            else -> emptyList()
        }

    }

    private fun convertFromMeters(amount: Double): List<ConversionResult> {
        val cm = amount * 100
        val inches = amount / 0.0254  // 1 in = 0.0254 m
        return listOf(
            ConversionResult(MeasureUnit.METER, amount),
            ConversionResult(MeasureUnit.CENTIMETER, cm),
            ConversionResult(MeasureUnit.INCH, inches)
        )
    }

    private fun convertFromCentimeters(amount: Double): List<ConversionResult> {
        val meters = amount / 100
        val inches = (amount / 100) / 0.0254  // primero a metros, luego a pulgadas
        return listOf(
            ConversionResult(MeasureUnit.METER, meters),
            ConversionResult(MeasureUnit.CENTIMETER, amount),
            ConversionResult(MeasureUnit.INCH, inches)
        )
    }

    private fun convertFromInches(amount: Double): List<ConversionResult> {
        val meters = amount * 0.0254
        val cm = meters * 100
        return listOf(
            ConversionResult(MeasureUnit.METER, meters),
            ConversionResult(MeasureUnit.CENTIMETER, cm),
            ConversionResult(MeasureUnit.INCH, amount)
        )
    }
}