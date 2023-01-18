package com.example.y20230118_yesidhuertas_nycschools.ui.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.y20230118_yesidhuertas_nycschools.R
import com.example.y20230118_yesidhuertas_nycschools.dataaccess.models.SchoolDetails
import com.example.y20230118_yesidhuertas_nycschools.databinding.FragmentDetailsBinding
import com.example.y20230118_yesidhuertas_nycschools.ui.states.SchoolState
import com.example.y20230118_yesidhuertas_nycschools.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : DialogFragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    private var _dbn = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        binding.btnClose.setOnClickListener { dismissAllowingStateLoss() }

        viewModel.schoolDetailsState.observe(viewLifecycleOwner, ::handleDetailsStateChanged)

        return binding.root
    }

    private fun handleDetailsStateChanged(schoolState: SchoolState) {
        when (schoolState) {
            is SchoolState.ERROR -> {
                // here we handle errors, we can display a FragmentDialog displaying and error state
               with(binding) {
                   schoolName.text = viewModel.selectedSchool?.schoolName ?: getString(R.string.not_available)
                   dbn.text = viewModel.selectedSchool?.dbn ?: getString(R.string.not_available)
                   numOfSatTestTakers.text = getString(R.string.not_available)
                   satMathAvgScore.text = getString(R.string.not_available)
                   satCriticalReadingAvgScore.text = getString(R.string.not_available)
                   satWritingAvgScore.text = getString(R.string.not_available)
               }

                Log.e(TAG, "handleStateChanged: ${schoolState.throwable.localizedMessage}")

                shouldDisplayContent(true)
            }

            is SchoolState.LOADING -> shouldDisplayContent(false)
            is SchoolState.SUCCESS<*> -> {
                val details = schoolState.response as SchoolDetails
                with(binding) {
                    schoolName.text = details.schoolName
                    dbn.text = details.dbn
                    numOfSatTestTakers.text = details.numOfSatTestTakers
                    satMathAvgScore.text = details.satMathAvgScore
                    satCriticalReadingAvgScore.text = details.satCriticalReadingAvgScore
                    satWritingAvgScore.text = details.satWritingAvgScore
                }
                shouldDisplayContent(true)
            }
        }
    }

    private fun shouldDisplayContent(yes: Boolean) {
        when {
            yes -> {
                binding.detailsContent.visibility = View.VISIBLE
                binding.loading.visibility = View.GONE
            }
            else -> {
                binding.detailsContent.visibility = View.GONE
                binding.loading.visibility = View.VISIBLE
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getSchoolDetails(_dbn)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // read the dbn
        arguments?.let {
            _dbn = it.getString(DBN_KEY, "")
        }

        setStyle(
            STYLE_NORMAL,
            android.R.style.Theme_Material_Light_NoActionBar_Fullscreen
        )

        dialog?.let {
            it.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "DetailsFragment"
        const val DBN_KEY = "DBN_KEY"

        @JvmStatic
        fun newDetailsFragment(dbn: String) = DetailsFragment().apply {
            Bundle().apply {
                putString(DBN_KEY, dbn)
                arguments = this
            }
        }
    }
}