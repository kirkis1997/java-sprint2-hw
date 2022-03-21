package manager;

import manager.interfaces.HistoryManager;
import manager.interfaces.TaskManager;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.util.HashMap;

public class InMemoryTaskManager implements TaskManager {

    private static Integer count = 0;//Поле для передачи значения уникального модификатора
    private final HashMap<Integer, Task> allTasks = new HashMap<>();//HashMap для списка всех задач
    HistoryManager historyManager = Managers.getDefaultHistory();

    public static Integer getCount() {
        return count;
    }

    @Override
    public HashMap<Integer, Task> getAllTasks() {//Вывести все задачи
        return allTasks;
    }

    @Override
    public void removeAllTasks() {//Очисить список задач
        allTasks.clear();
        count = 0;
    }

    @Override
    public Task getTaskById(Integer id) {//Получить задачу по идентификатору
        Task task = null;
        if (allTasks.get(id) != null) {
            task = allTasks.get(id);
            historyManager.add(task);
        }
        return task;
    }

    @Override
    public void createNewTask(Task task) {//Создание новой задачи
        allTasks.put(count, task);
        count++;
    }

    @Override
    public void updateTask(Integer id, Task task, Status status) {//Обновление задач
        if (getTaskById(id) instanceof Subtask) {
            allTasks.put(id, task);
            task.setStatus(status);
            Subtask subtask = (Subtask) task;//Привести полученный объект к типу подзадача
            Epic epic = (Epic) getTaskById(subtask.getEpicId());//Получить эпик, в котором находится эта подзадача
            epic.addSubtasks(subtask);//Добавить измененный объект подзадачи в список подзадач текущего эпика
            //для для вызова метода по обновлению эпика
            epic.setEpicStatus();//Получить объект tasks.Epic с обновленным статусом
        } else if (getTaskById(id) instanceof Epic) {
            allTasks.put(id, task);//Если введенный объект является епиком, то обновляем все его поля, кроме статуса
        } else {
            allTasks.put(id, task);//Обновляем задачу с помощью передачи нового объекта, который заменяет старый
            task.setStatus(status);//Обновляем статус вручную, т.к. по умолчанию стоит статус Status.NEW
        }


    }

    @Override
    public void removeTaskById(Integer id) {//Удаление задачи по идентификатору
        allTasks.remove(id);
    }

    @Override
    public HashMap<Integer, Subtask> getAllSubtasks(Epic epic) {//Получить все подзадачи эпика
        return epic.getSubtasks();
    }


}
