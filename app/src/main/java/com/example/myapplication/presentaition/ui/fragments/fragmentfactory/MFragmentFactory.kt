package com.example.myapplication.presentaition.ui.fragments.fragmentfactory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.myapplication.domain.models.Student
import com.example.myapplication.presentaition.ui.fragments.MusicFragment
import com.example.myapplication.presentaition.ui.fragments.courses.AboutCourseFragment
import com.example.myapplication.presentaition.ui.fragments.courses.AddCourseFragment
import com.example.myapplication.presentaition.ui.fragments.courses.AddStudentFragment
import com.example.myapplication.presentaition.ui.fragments.registration.RegistrationFragment
import com.example.myapplication.presentaition.ui.fragments.registration.MUserProfileFragment
import com.example.myapplication.presentaition.ui.fragments.courses.CoursesFragment
import com.example.myapplication.presentaition.ui.fragments.students.StudentFragment
import com.example.myapplication.presentaition.ui.fragments.students.AboutStudentFragment

class MFragmentFactory(
    private val userProfile: String,
    private val email: String,
    private val age: Int,
    private val student: Student
): FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className){
            RegistrationFragment::class.java.toString() -> RegistrationFragment.newInstance(userProfile,email,age )
            MUserProfileFragment::class.java.toString() -> MUserProfileFragment.newInstance()
            CoursesFragment::class.java.toString() -> CoursesFragment.newInstance()
            AddCourseFragment::class.java.toString() -> AddCourseFragment.newInstance()
            StudentFragment::class.java.toString() -> StudentFragment.newInstance()
            AddStudentFragment::class.java.toString() -> AddStudentFragment.newInstance()
            AboutCourseFragment::class.java.toString() -> AboutCourseFragment.newInstance()
            MusicFragment::class.java.toString() -> MusicFragment.newInstance()
            AboutStudentFragment::class.java.toString() -> AboutStudentFragment.newInstance(student)
            else -> super.instantiate(classLoader, className)
        }
    }
}