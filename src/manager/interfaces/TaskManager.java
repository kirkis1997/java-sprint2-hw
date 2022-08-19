package manager.interfaces;

import tasks.Epic;
import tasks.Subtask;
import tasks.Task;
import utility.Status;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface TaskManager {

    HashMap<Integer, Task> getAllTasks();

    void removeAllTasks() throws IOException;

    Task getTaskById(Integer id);

    void createNewTask(Task task) throws IOException;

    void updateTask(Integer id, Task task, Status status) throws IOException;

    void removeTaskById(Integer id) throws IOException;

    HashMap<Integer, Subtask> getAllSubtasks(Epic epic);

    List<Task> history();

    HistoryManager getHistoryManager();
}
