package com.example.myapplication.presentaition.viewmodels.studentviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.models.Student
import com.example.myapplication.domain.usecases.studentusecase.AddStudentUseCase
import kotlinx.coroutines.launch

class AddStudentViewModel(private val addStudentUseCase: AddStudentUseCase) : ViewModel() {

    fun addStudent(student: Student) = viewModelScope.launch {
        addStudentUseCase(student = student)

    }
}