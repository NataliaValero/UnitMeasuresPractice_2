package com.example.unitmeasurespractice2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.unitmeasurespractice2.R
import com.example.unitmeasurespractice2.databinding.FragmentMeasureCategoryBinding
import com.example.unitmeasurespractice2.model.MeasureCategory
import com.example.unitmeasurespractice2.presentation.MeasureUnitViewModel
import com.example.unitmeasurespractice2.ui.adapters.MeasureCategoryAdapter

class MeasureCategoryFragment : Fragment() {

    private var _binding: FragmentMeasureCategoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var categoryAdapter: MeasureCategoryAdapter
    private val viewModel: MeasureUnitViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMeasureCategoryBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCategoryAdapter()
    }

    private fun setCategoryAdapter() {

        val categories = viewModel.getAllCategories()
        categoryAdapter = MeasureCategoryAdapter(categories, object : MeasureCategoryAdapter.onCategorySelectedListener {
            override fun onMeasureCategorySelected(measureCategory: MeasureCategory) {
                // que quiero hacer con mi category selected?
                // le aviso a view model que hay una categoria seleccionada
                viewModel.onCategorySelected(measureCategory)
                findNavController().navigate(R.id.action_measureCategoryFragment_to_measureConverterFragment)
            }
        })

        binding.categoriesRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = categoryAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}