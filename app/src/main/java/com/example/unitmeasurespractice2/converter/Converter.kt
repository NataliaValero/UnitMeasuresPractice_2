package com.example.unitmeasurespractice2.converter

import com.example.unitmeasurespractice2.model.ConversionResult
import com.example.unitmeasurespractice2.model.MeasureCategory
import com.example.unitmeasurespractice2.model.MeasureUnit


interface Converter  {
    fun convert(amount: Double, fromUnit: MeasureUnit) : List<ConversionResult>

    companion object {
        fun getConverterByCategory(measureCategory: MeasureCategory) : Converter {
            return when(measureCategory) {
                MeasureCategory.MASS -> MassConverter()
                MeasureCategory.LENGTH -> LengthConverter()
            }
        }
    }
}