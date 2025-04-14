package com.example.myapplication.presentaition.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.domain.models.Student
import com.example.myapplication.presentaition.ui.fragments.registration.RegistrationFragment
import com.example.myapplication.presentaition.ui.fragments.fragmentfactory.MFragmentFactory

@Suppress("UNREACHABLE_CODE", "DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val student = Student(
        id = 1,
        profilePicture = com.example.myapplication.R.drawable.student, // или 0, если картинки пока нет
        name = "Ilya",
        surname = "Balashov",
        phoneNumber = "89001234567",
        age = 25
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = MFragmentFactory("Username", "balashov4ilya@gmail.com", 25, student)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null){
            replaceFragment(RegistrationFragment::class.java.toString())
        }

    }

    private fun replaceFragment(fragmentName: String,) {
        val fragment = supportFragmentManager.fragmentFactory.instantiate(classLoader, fragmentName)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_id, fragment)
            .commitAllowingStateLoss()
    }


}
