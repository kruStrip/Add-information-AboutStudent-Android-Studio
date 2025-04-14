package com.example.myapplication.presentaition.ui.fragments.courses

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.presentaition.application.MyApplication
import com.example.myapplication.databinding.FragmentAddStudentBinding
import com.example.myapplication.domain.models.Student
import com.example.myapplication.domain.usecases.studentusecase.AddStudentUseCase
import com.example.myapplication.presentaition.ui.fragments.registration.MUserProfileFragment
import com.example.myapplication.presentaition.viewmodelfactories.studentfactory.AddStudentViewModelFactory
import com.example.myapplication.presentaition.viewmodels.studentviewmodel.AddStudentViewModel

class AddStudentFragment : Fragment() {

    private var _binding: FragmentAddStudentBinding? = null
    private val binding get() = _binding!!
    private lateinit var addStudentViewModel: AddStudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddStudentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val app = requireActivity().applicationContext as MyApplication
        val addStudentRepository = app.addStudentRepositoryImpl

        val addStudentUseCase = AddStudentUseCase(addStudentRepository)
        val addStudentViewModelFactory = AddStudentViewModelFactory(addStudentUseCase)

        addStudentViewModel = ViewModelProvider(this, addStudentViewModelFactory)[AddStudentViewModel::class.java]

        binding.addStudentButtonId.setOnClickListener {

            Log.d("ButtenTag", "pressed")
            val name = binding.editNameStudentId.text.toString()
            val surname = binding.editStudentSurnameId.text.toString()
            val phone = binding.editStudentPhoneId.text.toString()
            val age = binding.editStudentAgeId.text.toString()


            if(name.isNotEmpty() && phone.isNotEmpty()){

                addStudentViewModel.addStudent(Student(
                    profilePicture = R.drawable.student,
                    name = name,
                    surname= surname,
                    phoneNumber =  phone,
                    age = age.toInt()))
                replaceFragment(MUserProfileFragment::class.java.name) // Используем .name для получения полного имени класса
            }

        }
    }

    private fun replaceFragment(fragmentName: String) {
        // Проверка, что fragmentName не пустой
        if (fragmentName.isEmpty()) {
            throw IllegalArgumentException("Fragment name cannot be empty")
        }

        try {
            // Создание фрагмента
            val fragment = requireActivity().supportFragmentManager.fragmentFactory
                .instantiate(requireActivity().classLoader, fragmentName)

            // Замена фрагмента
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_id, fragment)
                .addToBackStack(null) // Добавление транзакции в back stack
                .commitAllowingStateLoss() // Подтверждение транзакции
        } catch (e: Fragment.InstantiationException) {
            // Обработка ошибки
            e.printStackTrace()
            throw RuntimeException("Failed to instantiate fragment: $fragmentName", e)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddCourseFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            AddStudentFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}