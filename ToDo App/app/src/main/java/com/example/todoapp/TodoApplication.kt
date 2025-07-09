import android.app.Application

class TodoApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { TaskRepository(database.taskDao()) }
}