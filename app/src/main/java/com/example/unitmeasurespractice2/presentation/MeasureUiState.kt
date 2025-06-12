package com.example.unitmeasurespractice2.presentation

import com.example.unitmeasurespractice2.model.ConversionResult
import com.example.unitmeasurespractice2.model.MeasureCategory
import com.example.unitmeasurespractice2.model.MeasureUnit

data class MeasureUiState(
    val measureCategorySelected: MeasureCategory,
    val units: List<MeasureUnit>,
    val unitSelected: MeasureUnit,
    val amount: Double,
    val conversionResults: List<ConversionResult>
)


/*----------Nota-----------------
* En general, las propiedades dentro de una data class deberían ser val, porque:
* La data class es inmutable.
* El estado se modifica con copy(...), no mutando directamente
* Se haría así:
* state = state.copy(amount = nuevoValor)
* */