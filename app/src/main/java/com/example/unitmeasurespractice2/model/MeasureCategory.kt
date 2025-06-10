package com.example.unitmeasurespractice2.model

enum class MeasureCategory(val displayName: String) {
    MASS("Mass"),
    LENGTH("Length");

    companion object {
        fun getMeasureCategories() : List<MeasureCategory> {
            return entries
        }
    }
}