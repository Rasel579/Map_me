package com.teck.ui.labels

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.teck.domain.models.Place
import com.teck.ui.R
import com.teck.ui.databinding.FragmentLabelsBinding
import com.teck.ui.labels.adapter.LabelsAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.getKoin

class LabelsFragment : Fragment(R.layout.fragment_labels) {
    private val viewBinding: FragmentLabelsBinding by viewBinding()
    private val scope = getKoin().createScope<LabelsFragment>()
    private val viewModel: LabelsViewModel = scope.get()
    private var adapter: LabelsAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViewModel()
    }

    private fun initViewModel() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.takeData().collect { places ->
                renderData(places)
            }
        }
    }


    private fun renderData(places: List<Place>) {
        adapter = LabelsAdapter(places, click())
        viewBinding.recycleViewLabels.adapter = adapter
    }
    private fun click() = View.OnClickListener {
        Log.e("interface", "click" )
    }

    companion object {
        fun newInstance() = LabelsFragment()
    }
}