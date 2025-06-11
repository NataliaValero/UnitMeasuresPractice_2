package com.example.unitmeasurespractice2.converter

import com.example.unitmeasurespractice2.model.ConversionResult
import com.example.unitmeasurespractice2.model.Length
import com.example.unitmeasurespractice2.model.MeasureUnit

class LengthConverter : Converter{
    override fun convert(amount: Double, fromUnit: MeasureUnit): List<ConversionResult> {

        val unit = fromUnit as Length
        return when(unit) {
            Length.METER -> convertFromMeters(amount)
            Length.CENTIMETER -> convertFromCentimeters(amount)
            Length.INCH -> convertFromInches(amount)
        }

    }

    private fun convertFromMeters(amount: Double): List<ConversionResult> {
        val cm = amount * 100
        val inches = amount / 0.0254  // 1 in = 0.0254 m
        return listOf(
            ConversionResult(Length.METER, amount),
            ConversionResult(Length.CENTIMETER, cm),
            ConversionResult(Length.INCH, inches)
        )
    }

    private fun convertFromCentimeters(amount: Double): List<ConversionResult> {
        val meters = amount / 100
        val inches = meters / 0.0254  // primero a metros, luego a pulgadas
        return listOf(
            ConversionResult(Length.METER, meters),
            ConversionResult(Length.CENTIMETER, amount),
            ConversionResult(Length.INCH, inches)
        )
    }

    private fun convertFromInches(amount: Double): List<ConversionResult> {
        val meters = amount * 0.0254
        val cm = meters * 100
        return listOf(
            ConversionResult(Length.METER, meters),
            ConversionResult(Length.CENTIMETER, cm),
            ConversionResult(Length.INCH, amount)
        )
    }
}