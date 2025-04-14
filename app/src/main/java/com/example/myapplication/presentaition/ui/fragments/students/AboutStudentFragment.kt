package com.example.myapplication.presentaition.ui.fragments.students

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.FragmentAboutStudentBinding
import com.example.myapplication.domain.models.Student
import com.example.myapplication.presentaition.constants.ARG_AGE
import com.example.myapplication.presentaition.constants.ARG_PHONE_NUMBER
import com.example.myapplication.presentaition.constants.ARG_PROFILE_ID
import com.example.myapplication.presentaition.constants.ARG_PROFILE_NAME
import com.example.myapplication.presentaition.constants.ARG_PROFILE_PICTURE
import com.example.myapplication.presentaition.constants.ARG_PROFILE_SURNAME

class AboutStudentFragment : Fragment() {

    private var _binding: FragmentAboutStudentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutStudentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { args ->
            binding.apply {
                studentIdInf.text = getString(
                    com.example.myapplication.R.string.student_id_inf,
                    args.getInt(ARG_PROFILE_ID)
                )
                studentNameInf.text = getString(
                    com.example.myapplication.R.string.student_name_inf,
                    args.getString(ARG_PROFILE_NAME)
                )
                studentSurnameInf.text = getString(
                    com.example.myapplication.R.string.student_surname_inf,
                    args.getString(ARG_PROFILE_SURNAME)
                )
                studentPhoneInf.text = getString(
                    com.example.myapplication.R.string.student_phone_inf,
                    args.getString(ARG_PHONE_NUMBER)
                )
                studentAgeInf.text = getString(
                    com.example.myapplication.R.string.student_age_inf,
                    args.getInt(ARG_AGE)
                )

                Glide.with(this@AboutStudentFragment)
                    .load(args.getInt(ARG_PROFILE_PICTURE))
                    .into(studentImageInf)
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(student: Student): AboutStudentFragment {
            return AboutStudentFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PROFILE_ID, student.id)
                    putString(ARG_PROFILE_NAME, student.name)
                    putString(ARG_PROFILE_SURNAME, student.surname)
                    putString(ARG_PHONE_NUMBER, student.phoneNumber)
                    putInt(ARG_AGE, student.age)
                    putInt(ARG_PROFILE_PICTURE, student.profilePicture)
                }
            }
        }
    }
}