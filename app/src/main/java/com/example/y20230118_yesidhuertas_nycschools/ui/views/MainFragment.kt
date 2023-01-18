package com.example.y20230118_yesidhuertas_nycschools.ui.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.y20230118_yesidhuertas_nycschools.dataaccess.models.School
import com.example.y20230118_yesidhuertas_nycschools.databinding.MainFragmentBinding
import com.example.y20230118_yesidhuertas_nycschools.ui.states.SchoolState
import com.example.y20230118_yesidhuertas_nycschools.ui.adapters.SchoolAdapter
import com.example.y20230118_yesidhuertas_nycschools.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null
    private val binding: MainFragmentBinding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    private val schoolAdapter by lazy {
        SchoolAdapter {
            viewModel.selectedSchool = it
            DetailsFragment.newDetailsFragment(it.dbn)
                .show(childFragmentManager, DetailsFragment.TAG)
        }
    }

    /**
     *  used to handle changes in this fragment
     *  these changes are triggered by the view model
     *  @param schoolState this is the actual state that the view is in
     */
    private fun handleStateChanged(schoolState: SchoolState) {
        when (schoolState) {
            is SchoolState.LOADING -> {
                binding.loading.visibility = View.VISIBLE
                binding.schoolList.visibility = View.GONE
            }
            is SchoolState.ERROR -> {
                // here we handle errors, we can display a FragmentDialog displaying and error state
                Log.e(TAG, "handleStateChanged: ${schoolState.throwable.localizedMessage}")
            }
            is SchoolState.SUCCESS<*> -> {
                schoolAdapter.setNewSchools(
                    (schoolState.response as List<*>).filterIsInstance<School>()
                )

                binding.loading.visibility = View.GONE
                binding.schoolList.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)

        viewModel.schoolState.observe(viewLifecycleOwner, ::handleStateChanged)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.schoolList.adapter = schoolAdapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.getSchools()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}

// used for logging
private const val TAG = "MainFragment"