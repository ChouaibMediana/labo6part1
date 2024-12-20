package ca.qc.cgodin.roomstudent.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import ca.qc.cgodin.roomstudent.models.Student
import ca.qc.cgodin.roomstudent.repository.StudentRepository
import ca.qc.cgodin.roomstudent.DataBases.StudentRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
class StudentViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: StudentRepository
    // - Using LiveData and caching what getStudents returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allStudents: LiveData<List<Student>>
    init {
        val studentsDao =
            StudentRoomDatabase.getDatabase(application).studentDao()
        repository = StudentRepository(studentsDao)
        allStudents = repository.allStudents
    }
    // Launching a new coroutine to insert the data in a non-blocking way
    fun insert(student: Student) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(student)
    }

    fun getStudentsByName(name: String): LiveData<List<Student>> {
        return repository.getStudentsByName(name)
    }
    fun delete(student: Student) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(student)
    }

    // Add the update method
    fun update(student: Student) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(student)
    }
}