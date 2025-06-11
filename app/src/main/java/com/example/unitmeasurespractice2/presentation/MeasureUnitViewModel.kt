package com.example.unitmeasurespractice2.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.unitmeasurespractice2.converter.Converter
import com.example.unitmeasurespractice2.model.ConversionResult
import com.example.unitmeasurespractice2.model.MeasureUnit
import com.example.unitmeasurespractice2.model.MeasureCategory


class MeasureUnitViewModel : ViewModel() {

    private var measureCategorySelected: MeasureCategory= getCategories().first()

    private var units: List<MeasureUnit> = getUnitsByCategory(measureCategorySelected)

    private var unitSelected: MeasureUnit = units.first()

    private var amount: Double = INITIAL_RESULT_VALUE

    private var _conversionResult = MutableLiveData<List<Double>>()
    val conversionResult: LiveData<List<Double>> get() = _conversionResult

    companion object {
        private const val INITIAL_RESULT_VALUE = 0.0
    }


    fun generateInitialResults(): List<ConversionResult> {
        return units.map { ConversionResult(it, INITIAL_RESULT_VALUE) }
    }

    fun onAmountUpdated(newAmount: Double?) {
        amount = newAmount?: INITIAL_RESULT_VALUE
        updateConversionResults()
    }

    fun onMeasureUnitUpdated(measurableUnit: MeasureUnit) {
        unitSelected = measurableUnit
        updateConversionResults()
    }

    fun onMeasureCategorySelected(newCategory: MeasureCategory) {

        // Update measure category
        measureCategorySelected = newCategory

        // Update all units from category and unit selected (IMPORTANT)
        units = getUnitsByCategory(newCategory)
        unitSelected = units.first()

        // Reset conversion result live data
        updateConversionResults(reset = true)
    }

    fun getMeasureCategories() : List<MeasureCategory>{
        return MeasureCategory.getMeasureCategories()
    }

    private fun calculateResults() : List<Double> {

        val converter = Converter.getConverterByCategory(measureCategorySelected)
        return converter.convert(amount = amount, fromUnit = unitSelected).map {
            it.result
        }
    }


    private fun updateConversionResults(reset: Boolean = false) {
        val results = if (reset) {
            units.map { INITIAL_RESULT_VALUE }
        } else {
            calculateResults()
        }

        _conversionResult.value = results
    }

    private fun getUnitsByCategory(category: MeasureCategory) : List<MeasureUnit>{
        return MeasureUnit.getUnitsByCategory(category)
    }

    private fun getCategories() : List<MeasureCategory> {
        return MeasureCategory.getMeasureCategories()
    }
}