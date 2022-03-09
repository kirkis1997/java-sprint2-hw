package taskmanager;

import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.util.HashMap;

public class TaskManager {
    private static Integer count = 0;//Поле для передачи значения уникального модификатора
    private HashMap<Integer, Object> taskMap = new HashMap<>();// HashMap для хранения всех видов задач
    private HashMap<Integer, Task> allTasks = new HashMap<>();//HashMap для tasks.Task
    private HashMap<Integer, Subtask> allSubtasks = new HashMap<>();//HashMap для tasks.Subtask
    private HashMap<Integer, Epic> allEpics = new HashMap<>();//HashMap для tasks.Epic

    public static Integer getCount() {
        return count;
    }

    public static void setCount() {
        TaskManager.count++;
    }

    public Object getAllTasks() {//Вывод всех задач
        taskMap.put(0, allTasks);
        taskMap.put(1, allEpics);
        taskMap.put(2, allSubtasks);

        return taskMap;

    }

    public void removeAllTasks() {//Очистка спика задач
        allTasks.clear();
        count = 0;
    }

    public Object getTaskById(Integer id) {//Получение задачи по идентификатору
        Object object = null;
        if (allTasks.get(id) != null) {
            object = allTasks.get(id);
        } else if (allSubtasks.get(id) != null) {
            object = allSubtasks.get(id);
        } else if (allEpics.get(id) != null) {
            object = allEpics.get(id);
        }
        return object;
    }


    public void createNewTask(Task task) {//Создание новой задачи
        allTasks.put(task.getUniqueId(), task);
    }

    public void createNewTask(Subtask subtask) {//Создание новой задачи
        allSubtasks.put(subtask.getUniqueId(), subtask);
    }

    public void createNewTask(Epic epic) {//Создание новой задачи
        allEpics.put(epic.getUniqueId(), epic);
    }

    public void updateTask(Integer id, Task task, String status) {//Обновление задач
        if (status.equals("NEW") || status.equals("IN_PROGRESS") || status.equals("DONE")) {
            allTasks.put(id, task);
            task.setStatus(status);
        }
    }

    public void updateTask(Integer id, Subtask subtask, String status) {//Обновление подзадач
        if (status.equals("NEW") || status.equals("IN_PROGRESS") || status.equals("DONE")) {
            allTasks.put(id, subtask);
            subtask.setStatus(status);
            allEpics.get(subtask.getEpicId()).setEpicStatus();//Получаем объект tasks.Epic с обновленным статусом
            allEpics.put(subtask.getEpicId(), allEpics.get(subtask.getEpicId()));//Помещаем этот объект в allEpics
        }
    }


    public void removeTaskById(Integer id) {//Удаление задачи по идентификатору
        allTasks.remove(id);
    }

    public Object getAllSubtasks(Epic epic) {//Получение всех подзадач эпика
        return epic.getSubtasks();

    }

}
