package manager.interfaces;

import manager.Status;
import tasks.Epic;
import tasks.Task;

import java.util.HashMap;

public interface TaskManager {

    HashMap<Integer, Task> getAllTasks();

    void removeAllTasks();

    Task getTaskById(Integer id);

    void createNewTask(Task task);

    void updateTask(Integer id, Task task, Status status);

    void removeTaskById(Integer id);

    Object getAllSubtasks(Epic epic);
}
