package manager.interfaces;
import manager.Status;
import tasks.*;

public interface TaskManager {


    Object getAllTasks();

    void removeAllTasks();

    Object getTaskById(Integer id);

    void createNewTask(Task task);

    void createNewTask(Subtask subtask);

    void createNewTask(Epic epic);

    void updateTask(Integer id, Task task, Status status);

    void updateTask(Integer id, Subtask subtask, Status status);

    void removeTaskById(Integer id);

    Object getAllSubtasks(Epic epic);
}
