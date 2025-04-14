package com.example.myapplication.presentaition.viewmodelfactories.studentfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.domain.usecases.studentusecase.GetStudentsUseCase
import com.example.myapplication.presentaition.viewmodels.studentviewmodel.GetStudentViewModel

@Suppress("UNCHECKED_CAST")
class GetStudentViewModelFactory(private val getStudentsUseCase: GetStudentsUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GetStudentViewModel(getStudentsUseCase) as T
    }
}