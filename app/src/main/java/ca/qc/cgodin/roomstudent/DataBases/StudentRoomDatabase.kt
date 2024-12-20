package ca.qc.cgodin.roomstudent.DataBases
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ca.qc.cgodin.roomstudent.DAOS.StudentDao
import ca.qc.cgodin.roomstudent.models.Student


@Database(entities = arrayOf(Student::class), version = 1, exportSchema = false)
abstract class StudentRoomDatabase: RoomDatabase() {
    abstract fun studentDao(): StudentDao
    companion object{
        @Volatile
        private var INSTANCE: StudentRoomDatabase? = null
        fun getDatabase(context: Context): StudentRoomDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null)
            {
                return tempInstance
            }
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                StudentRoomDatabase::class.java,
                "student_database"
            ).build()
            return INSTANCE as StudentRoomDatabase
        }
    }
}