package ca.qc.cgodin.roomstudent.repository

import androidx.lifecycle.LiveData
import ca.qc.cgodin.roomstudent.DAOS.StudentDao
import ca.qc.cgodin.roomstudent.models.Student

class StudentRepository(private val studentDao: StudentDao) {

    val allStudents: LiveData<List<Student>> = studentDao.getStudents()
    fun insert(student: Student){
        studentDao.insert(student)
    }

    fun getStudentsByName(name: String): LiveData<List<Student>> {
        return studentDao.getStudentsByName("%$name%")

    }
    suspend fun update(student: Student) {
        studentDao.update(student)
    }

    suspend fun delete(student: Student) {
        studentDao.delete(student)
    }
}