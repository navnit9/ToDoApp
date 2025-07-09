import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(
    private val onItemClicked: (Task) -> Unit,
    private val onCheckboxClicked: (Task, Boolean) -> Unit
) : ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task, onItemClicked, onCheckboxClicked)
    }

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.task_title)
        private val descTextView: TextView = itemView.findViewById(R.id.task_description)
        private val checkbox: CheckBox = itemView.findViewById(R.id.task_checkbox)

        fun bind(
            task: Task,
            onItemClicked: (Task) -> Unit,
            onCheckboxClicked: (Task, Boolean) -> Unit
        ) {
            titleTextView.text = task.title
            descTextView.text = task.description ?: ""
            checkbox.isChecked = task.isCompleted

            itemView.setOnClickListener { onItemClicked(task) }
            checkbox.setOnCheckedChangeListener { _, isChecked ->
                onCheckboxClicked(task, isChecked)
            }
        }
    }
}

class TaskDiffCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }
}