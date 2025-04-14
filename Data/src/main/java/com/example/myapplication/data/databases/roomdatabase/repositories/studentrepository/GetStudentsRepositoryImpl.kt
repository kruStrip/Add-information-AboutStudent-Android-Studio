package com.example.myapplication.data.databases.roomdatabase.repositories.studentrepository

import com.example.myapplication.data.databases.roomdatabase.database.AppRoomDatabase
import com.example.myapplication.domain.models.Student
import com.example.myapplication.domain.repositories.studentrepository.IGetStudentsRepository

class GetStudentsRepositoryImpl(private val database: AppRoomDatabase): IGetStudentsRepository {

    private val studentDao = database.studentDao()

    override suspend fun getStudents(): List<Student> {
        val listStudent = studentDao.getAllStudents()

        return listStudent.map{ dto ->
            Student(
                profilePicture = dto.profilePicture,
                name = dto.name,
                surname = dto.surname,
                age = dto.age,
                phoneNumber = dto.phoneNumber
            )
        }
    }
}