package com.example.unitmeasurespractice2.ui.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.unitmeasurespractice2.R
import com.example.unitmeasurespractice2.model.MeasureCategory

class MeasureCategoryAdapter(
    private var categories: List<MeasureCategory>,
    private val listener: onCategorySelectedListener) :
    RecyclerView.Adapter<MeasureCategoryAdapter.MeasureCategoryViewHolder>(){

    class MeasureCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryBtn = itemView.findViewById<Button>(R.id.btnCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeasureCategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.measure_category_item, parent, false)
        return MeasureCategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: MeasureCategoryViewHolder, position: Int) {
        val currentCategory = categories.get(position)
        holder.categoryBtn.text = currentCategory.displayName

        holder.categoryBtn.setOnClickListener {
            listener.onMeasureCategorySelected(currentCategory)
        }
    }

    interface onCategorySelectedListener {
        fun onMeasureCategorySelected(measureCategory: MeasureCategory)
    }
}