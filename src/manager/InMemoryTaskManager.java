package manager;

import manager.interfaces.HistoryManager;
import manager.interfaces.TaskManager;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;
import utility.Managers;
import utility.Status;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {

    private static Integer count = 0;//Поле для передачи значения уникального модификатора
    private final HashMap<Integer, Task> allTasks = new HashMap<>();//HashMap для списка всех задач
    private HistoryManager historyManager;

    public InMemoryTaskManager() {
        this.historyManager  = Managers.getDefaultHistory();
    }

    public HistoryManager getHistoryManager() {
        return historyManager;
    }

    public static Integer getCount() {
        return count;
    }

    @Override
    public HashMap<Integer, Task> getAllTasks() {//Вывести все задачи
        return allTasks;
    }//Получить список всех задач

    @Override
    public void removeAllTasks() throws IOException {//Очистить список задач
        allTasks.clear();
        count = 0;
    }

    @Override
    public Task getTaskById(Integer id) {//Получить задачу по идентификатору

        Task task = allTasks.get(id);
        historyManager.add(task);

        return task;
    }

    @Override
    public void createNewTask(Task task) throws IOException {//Создание новой задачи
        if (task instanceof Subtask) {
            Subtask subtask = (Subtask) task;//Привести полученный объект к типу подзадача
            Epic epic = (Epic) allTasks.get(subtask.getEpicId());//Получить эпик, в котором находится эта подзадача
            epic.addSubtasks(subtask);//Добавить измененный объект подзадачи в список подзадач текущего эпика
            //для вызова метода по обновлению эпика
            epic.setEpicStatus();//Получить объект tasks.Epic с обновленным статусом
        }
        allTasks.put(count, task);
        count++;
    }

    @Override
    public void updateTask(Integer id, Task task, Status status) throws IOException {//Обновление задач
        if (allTasks.get(id) instanceof Subtask) {
            allTasks.put(id, task);
            task.setStatus(status);
            Subtask subtask = (Subtask) task;//Привести полученный объект к типу подзадача
            Epic epic = (Epic) allTasks.get(subtask.getEpicId());//Получить эпик, в котором находится эта подзадача
            epic.addSubtasks(subtask);//Добавить измененный объект подзадачи в список подзадач текущего эпика
            //для для вызова метода по обновлению эпика
            epic.setEpicStatus();//Получить объект tasks.Epic с обновленным статусом
        } else if (allTasks.get(id) instanceof Epic) {
            allTasks.put(id, task);//Если введенный объект является эпиком, то обновляем все его поля, кроме статуса
        } else {
            allTasks.put(id, task);//Обновляем задачу с помощью передачи нового объекта, который заменяет старый
            task.setStatus(status);//Обновляем статус вручную, т.к. по умолчанию стоит статус Status.NEW
        }
    }

    @Override
    public void removeTaskById(Integer id) throws IOException {//Удаление задачи по идентификатору
        historyManager.remove(id);

        if (allTasks.get(id) instanceof Epic) {
            Epic epic = (Epic) allTasks.get(id);
            for (Subtask subtask : epic.getSubtasks().values()) {
                allTasks.remove(subtask.getUniqueId());
                historyManager.remove(subtask.getUniqueId());
            }
        }
        allTasks.remove(id);
    }

    @Override
    public HashMap<Integer, Subtask> getAllSubtasks(Epic epic) {//Получить все подзадачи эпика
        return epic.getSubtasks();
    }/*Получить список подзадач
    определенного эпика*/

    @Override
    public List<Task> history() {//Получить историю просмотров задач
        return historyManager.getHistory();
    }
}
