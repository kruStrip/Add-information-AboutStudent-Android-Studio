package com.example.myapplication.presentaition.ui.fragments.students

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.presentaition.application.MyApplication
import com.example.myapplication.databinding.FragmentStudentBinding
import com.example.myapplication.domain.usecases.studentusecase.GetStudentsUseCase
import com.example.myapplication.presentaition.ui.adapters.ItemStudentAdapter
import com.example.myapplication.presentaition.viewmodelfactories.studentfactory.GetStudentViewModelFactory
import com.example.myapplication.presentaition.viewmodels.studentviewmodel.GetStudentViewModel
import kotlinx.coroutines.launch


class StudentFragment : Fragment() {

    private var _binding: FragmentStudentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ItemStudentAdapter
    private lateinit var getStudentViewModel: GetStudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStudentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val app = requireActivity().applicationContext as MyApplication
        val getStudentRepository = app.getStudentRepositoryImpl

        val getStudentUseCase = GetStudentsUseCase(getStudentRepository)
        val getStudentViewModelFactory = GetStudentViewModelFactory(getStudentUseCase)


        getStudentViewModel = ViewModelProvider(this, getStudentViewModelFactory)[GetStudentViewModel::class.java]


        val manager = LinearLayoutManager(requireContext()) // LayoutManager
        adapter = ItemStudentAdapter(requireActivity())
        observeViewModel()

        binding.apply {
            studentRecyclerView.layoutManager = manager
            studentRecyclerView.adapter = adapter
        }


    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                getStudentViewModel.student.collect { items ->
                    adapter.updateList(items) // Обновляем данные адаптера
                }
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            StudentFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}