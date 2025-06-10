package com.example.unitmeasurespractice2.ui.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.unitmeasurespractice2.R
import com.example.unitmeasurespractice2.model.ConversionResult
import com.example.unitmeasurespractice2.model.MeasureUnit


class MeasureUnitsAdapter(
    private var conversionResults: List<ConversionResult>,
    private val listener: OnMeasureSelectedListener) :
    RecyclerView.Adapter<MeasureUnitsAdapter.MeasureUnitViewHolder>() {

    var positionSelected = 0

    // View holder
    class MeasureUnitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryItemRadioBtn = itemView.findViewById<RadioButton>(R.id.categoryItem)
        val conversionResultTv = itemView.findViewById<TextView>(R.id.conversionResultTv)
        val conversionSymbolTv = itemView.findViewById<TextView>(R.id.conversionSymbolTv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeasureUnitViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.measure_result_item, parent, false)
        return MeasureUnitViewHolder(view)
    }


    override fun onBindViewHolder(holder: MeasureUnitViewHolder, position: Int) {

        val currentConversionResult = conversionResults.get(position)

        // Cada ConversionResult tiene el measure unit y el resultado
        val measureUnit = currentConversionResult.unit
        val result = currentConversionResult.result

        holder.categoryItemRadioBtn.text = measureUnit.displayName
        holder.conversionSymbolTv.text = measureUnit.symbol
        holder.conversionResultTv.text = result.toString()

        holder.categoryItemRadioBtn.isChecked = (positionSelected == position)

        holder.categoryItemRadioBtn.setOnClickListener {
            val previousPosition = positionSelected
            positionSelected = position

            // Notifica que posiciones del adapter fueron los que se modificaron
            notifyItemChanged(previousPosition)
            notifyItemChanged(positionSelected)

            listener.onMeasureUnitSelected(measureUnit)

        }
    }

    override fun getItemCount(): Int {
       return conversionResults.size
    }

    fun setNewResults(newResults: List<Double>) {
        if (newResults.size != conversionResults.size) return

        // Mantenemos los units pero solo cambiamos los resultados
        conversionResults = conversionResults.mapIndexed { index, oldResult ->
            oldResult.copy(result = newResults[index])
        }

        notifyDataSetChanged()
    }

    interface OnMeasureSelectedListener {
        fun onMeasureUnitSelected(selectedUnit: MeasureUnit)
    }
}