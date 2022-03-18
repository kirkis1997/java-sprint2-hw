package manager;

import manager.interfaces.TaskManager;
import tasks.*;

import java.util.HashMap;

public class InMemoryTaskManager extends Managers implements TaskManager {
    private final HashMap<Integer, Task> allTasks = new HashMap<>();//HashMap для списка всех задач
    //private static InMemoryTaskManager instance;
    InMemoryHistoryManager historyManager = new InMemoryHistoryManager();


    /*public static InMemoryTaskManager getInstance() {
        if (instance == null) {
            instance = new InMemoryTaskManager();
        }
        return instance;
    }*/

    @Override
    public HashMap<Integer, Task> getAllTasks() {//Вывести все задачи
        return allTasks;
    }

    @Override
    public void removeAllTasks() {//Очисить список задач
        allTasks.clear();
        Task.setCount(0);
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

    //Переопределить и перегрузить метод создания новых задач для каждого их типа
    //



    @Override
    public void createNewTask(Task task) {//Создание новой задачи
        allTasks.put(task.getUniqueId(), task);
    }

    @Override
    public void createNewSubtask(Subtask subtask) {//Создание новой задачи
        allTasks.put(subtask.getUniqueId(), subtask);
    }

    @Override
    public void createNewEpic(Epic epic) {//Создание новой задачи
        allTasks.put(epic.getUniqueId(), epic);
    }


    @Override
    public void updateTask(Integer id, Task task, Status status) {//Обновление задач
        if (getTaskById(id) instanceof Task) {
            allTasks.put(id, task);//Обновляем задачу с помощью передачи нового объекта, который заменяет старый
            task.setStatus(status);//Обновляем статус вручную, т.к. по умолчанию стоит статус Status.NEW
        }
        

    }

    @Override
    public void updateSubtask(Integer id, Subtask subtask, Status status) {//Обновить подзадачу
        if (getTaskById(id) instanceof Subtask) {
            allTasks.put(id, subtask);
            subtask.setStatus(status);
            Epic epic = (Epic)getTaskById(subtask.getEpicId());//Получить эпик, в котором находится эта подзадача
            epic.setSubtasks(subtask);//Добавить измененный объект подзадачи в список подзадач текущего эпика
            epic.setEpicStatus();//Получить объект tasks.Epic с обновленным статусом
            allTasks.put(epic.getUniqueId(), epic);//Поместить этот объект в allTasks
        }


    }

    @Override
    public void updateEpic(Integer id, Epic epic) {//Обновить эпик
        if (getTaskById(id) instanceof  Epic) {
            allTasks.put(id, epic);
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
