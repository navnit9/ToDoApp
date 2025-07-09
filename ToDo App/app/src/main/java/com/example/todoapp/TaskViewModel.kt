import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {
    val allTasks = repository.allTasks

    fun insert(task: Task) = viewModelScope.launch {
        repository.insert(task)
    }

    fun update(task: Task) = viewModelScope.launch {
        repository.update(task)
    }

    fun delete(task: Task) = viewModelScope.launch {
        repository.delete(task)
    }
}