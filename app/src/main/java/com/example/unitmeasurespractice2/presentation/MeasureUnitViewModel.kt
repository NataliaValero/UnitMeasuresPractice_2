package com.example.unitmeasurespractice2.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.unitmeasurespractice2.converter.Converter
import com.example.unitmeasurespractice2.model.ConversionResult
import com.example.unitmeasurespractice2.model.MeasureUnit
import com.example.unitmeasurespractice2.model.MeasureCategory


class MeasureUnitViewModel : ViewModel() {

    // --- Constants ---
    companion object {
        private const val INITIAL_AMOUNT_VALUE = 0.0
    }

    // --- Initial State Setup ---
    private val allCategories = MeasureCategory.getMeasureCategories()
    private val initialCategory = allCategories.first()
    private val initialUnitsForCategory = getUnitsByCategory(initialCategory)
    private val initialUnitSelected = initialUnitsForCategory.first()


    // Live data measure ui state
    private var _uiState = MutableLiveData(
        MeasureUiState(
            measureCategorySelected = initialCategory,
            units = initialUnitsForCategory,
            unitSelected = initialUnitSelected,
            amount = INITIAL_AMOUNT_VALUE,
            conversionResults = initialUnitsForCategory.map {
                ConversionResult(initialUnitSelected, INITIAL_AMOUNT_VALUE)
            }
        )
    )

    val uiState: LiveData<MeasureUiState> get() = _uiState


    fun generateInitialResults(): List<ConversionResult> {
        val currentState = _uiState.value?: return emptyList()
        return currentState.conversionResults
    }

    fun onAmountUpdated(newAmount: Double?) {

        _uiState.value?.let { currentState ->
            val updatedAmount = newAmount?: INITIAL_AMOUNT_VALUE //Default to 0.0 if null
            _uiState.value = currentState.copy(amount = updatedAmount)
            updateConversionResults()
        }
    }

    fun onUnitSelected(measurableUnit: MeasureUnit) {

        _uiState.value?.let { currentState ->
            val updatedState = currentState.copy(unitSelected = measurableUnit)
            _uiState.value = updatedState

            updateConversionResults()
        }
    }

    fun onCategorySelected(newCategory: MeasureCategory) {

        _uiState.value?.let { currentState ->
            val newUnits = getUnitsByCategory(newCategory)
            val defaultSelectedUnit = newUnits.first()

            _uiState.value = currentState.copy(
                measureCategorySelected = newCategory,
                units = newUnits,
                unitSelected = defaultSelectedUnit
            )
            updateConversionResults(reset = true)
        }
    }


    fun getAllCategories() : List<MeasureCategory>{
        return MeasureCategory.getMeasureCategories()
    }

    private fun calculateResults(state: MeasureUiState) :  List<ConversionResult> {

        val converter = Converter.getConverterByCategory(state.measureCategorySelected)

        return converter.convert(
            amount = state.amount,
            fromUnit =  state.unitSelected)
    }


    private fun updateConversionResults(reset: Boolean = false) {

        _uiState.value?.let { currentState ->


            val results = if(reset) {  // results List<CalculationResult>

                currentState.units.map { ConversionResult(it, INITIAL_AMOUNT_VALUE) }
            } else {
                calculateResults(currentState)
            }

            _uiState.value = currentState.copy(conversionResults = results)

        }
    }

    private fun getUnitsByCategory(category: MeasureCategory) : List<MeasureUnit>{
        return MeasureUnit.getUnitsByCategory(category)
    }
}