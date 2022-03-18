package manager.interfaces;
import manager.Status;
import tasks.*;

import java.util.HashMap;

public interface TaskManager {


    HashMap<Integer, Task> getAllTasks();

    void removeAllTasks();

    Task getTaskById(Integer id);

    void createNewTask(Task task);

    void createNewSubtask(Subtask subtask);

    void createNewEpic(Epic epic);

    void updateTask(Integer id, Task task, Status status);

    void updateSubtask(Integer id, Subtask subtask, Status status);

    void updateEpic(Integer id, Epic epic);

    void removeTaskById(Integer id);

    Object getAllSubtasks(Epic epic);
}
