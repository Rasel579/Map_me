package com.teck.ui.labels

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.teck.ui.R
import com.teck.ui.databinding.FragmentLabelsBinding

class LabelsFragment : Fragment(R.layout.fragment_labels) {
    private val viewBinding: FragmentLabelsBinding by viewBinding()


    companion object {
        fun newInstance() = LabelsFragment()
    }
}