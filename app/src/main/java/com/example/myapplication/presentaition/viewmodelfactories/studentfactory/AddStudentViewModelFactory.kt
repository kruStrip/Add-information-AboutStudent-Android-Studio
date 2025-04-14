package com.example.myapplication.presentaition.viewmodelfactories.studentfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.domain.usecases.studentusecase.AddStudentUseCase
import com.example.myapplication.presentaition.viewmodels.studentviewmodel.AddStudentViewModel

@Suppress("UNCHECKED_CAST")
class AddStudentViewModelFactory(private val addStudentUseCase: AddStudentUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddStudentViewModel(addStudentUseCase) as T
    }
}