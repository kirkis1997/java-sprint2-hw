package manager.interfaces;

import tasks.Epic;
import tasks.Subtask;
import tasks.Task;
import utility.Status;

import java.util.HashMap;
import java.util.List;

public interface TaskManager {

    HashMap<Integer, Task> getAllTasks();

    void removeAllTasks();

    Task getTaskById(Integer id);

    void createNewTask(Task task);

    void updateTask(Integer id, Task task, Status status);

    void removeTaskById(Integer id);

    HashMap<Integer, Subtask> getAllSubtasks(Epic epic);

    List<Task> history();
}
