package com.example.unitmeasurespractice2.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.unitmeasurespractice2.databinding.FragmentMeasureConverterBinding
import com.example.unitmeasurespractice2.model.MeasureUnit
import com.example.unitmeasurespractice2.presentation.MeasureUnitViewModel
import com.example.unitmeasurespractice2.ui.adapters.MeasureUnitsAdapter


class MeasureConverterFragment : Fragment() {

    private var _binding: FragmentMeasureConverterBinding? = null
    private val binding get() = _binding!!

    private lateinit var measureUnitsAdapter: MeasureUnitsAdapter
    private val viewModel: MeasureUnitViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMeasureConverterBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setMeasureResultsAdapter()
        setAmountField()
        setObservers()
    }

    private fun setObservers() {
        // Only livedata is conversion result
        viewModel.conversionResult.observe(viewLifecycleOwner) { results->
            // when results have changes then we notify to adapter rv
           //  need a list of double here
            measureUnitsAdapter.setNewResults(results)
        }
    }

    private fun setMeasureResultsAdapter() {

        val conversionResults = viewModel.generateInitialResults()
        measureUnitsAdapter = MeasureUnitsAdapter(conversionResults, object: MeasureUnitsAdapter.OnMeasureSelectedListener{
            override fun onMeasureUnitSelected(selectedUnit: MeasureUnit) {
                Log.d("Selected unit", selectedUnit.displayName)
                viewModel.onMeasureUnitUpdated(selectedUnit)
            }
        })

        binding.measureUnitsRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = measureUnitsAdapter
        }
    }

    private fun setAmountField() {
        binding.amountTextInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Aquí captura el texto mientras se escribe
                val input = s.toString().toDoubleOrNull()
                viewModel.onAmountUpdated(input)
                Log.d("Texto", input.toString())
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}