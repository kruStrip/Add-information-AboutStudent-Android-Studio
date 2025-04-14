package com.example.myapplication.presentaition.viewmodels.studentviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.models.Student
import com.example.myapplication.domain.usecases.studentusecase.GetStudentsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GetStudentViewModel(private val getStudentUseCase: GetStudentsUseCase) : ViewModel(){

    private val _student = MutableStateFlow<List<Student>>(emptyList())
    val student: StateFlow<List<Student>> = _student

    init {
        fetchStudent()
    }

    private fun fetchStudent() = viewModelScope.launch {

        val result = getStudentUseCase()
        _student.value = result
    }
}