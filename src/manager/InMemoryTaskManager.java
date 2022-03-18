package manager;

import manager.interfaces.TaskManager;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.util.HashMap;

public class InMemoryTaskManager extends Managers implements TaskManager {
    private static InMemoryTaskManager instance;

    private static Integer count = 0;//Поле для передачи значения уникального модификатора
    private final HashMap<Integer, Object> taskMap = new HashMap<>();// HashMap для хранения всех видов задач
    private final HashMap<Integer, Task> allTasks = new HashMap<>();//HashMap для tasks.Task
    private final HashMap<Integer, Subtask> allSubtasks = new HashMap<>();//HashMap для tasks.Subtask
    private final HashMap<Integer, Epic> allEpics = new HashMap<>();//HashMap для tasks.Epic

    public static InMemoryTaskManager getInstance() {
        if (instance == null) {
            instance = new InMemoryTaskManager();
        }
        return instance;
    }

    public static Integer getCount() {
        return count;
    }

    public static void setCount() {
        InMemoryTaskManager.count++;
    }

    @Override
    public Object getAllTasks() {//Вывести все задачи
        taskMap.put(0, allTasks);
        taskMap.put(1, allEpics);
        taskMap.put(2, allSubtasks);

        return taskMap;

    }

    @Override
    public void removeAllTasks() {//Очисить список задач
        allTasks.clear();
        allEpics.clear();
        allSubtasks.clear();
        taskMap.clear();
        count = 0;
    }

    @Override
    public Object getTaskById(Integer id) {//Получить задачу по идентификатору
        Object object = null;
        int i = 10;
        if (allTasks.get(id) != null) {
            object = allTasks.get(id);
            getDefaultHistory().add((Task) object);
        } else if (allSubtasks.get(id) != null) {
            object = allSubtasks.get(id);
            getDefaultHistory().add((Subtask) object);
        } else if (allEpics.get(id) != null) {
            object = (Epic) allEpics.get(id);
            getDefaultHistory().add((Epic) object);
        }


        return object;
    }

    //Переопределить и перегрузить метод создания новых задач для каждого их типа
    @Override
    public void createNewTask(Task task) {//Создание новой задачи
        allTasks.put(task.getUniqueId(), task);
    }

    @Override
    public void createNewTask(Subtask subtask) {//Создание новой задачи
        allSubtasks.put(subtask.getUniqueId(), subtask);
    }

    @Override
    public void createNewTask(Epic epic) {//Создание новой задачи
        allEpics.put(epic.getUniqueId(), epic);
    }


    @Override
    public void updateTask(Integer id, Task task, Status status) {//Обновление задач
        allTasks.put(id, task);
        task.setStatus(status);

    }

    @Override
    public void updateTask(Integer id, Subtask subtask, Status status) {//Обновление подзадач
        allSubtasks.put(id, subtask);
        ((Epic) getTaskById(subtask.getEpicId())).setSubtasks(subtask);//Добавить измененный объект подзадачи в список
        //подзадач текущего эпика
        subtask.setStatus(status);
        allEpics.get(subtask.getEpicId()).setEpicStatus();//Получить объект tasks.Epic с обновленным статусом
        allEpics.put(subtask.getEpicId(), allEpics.get(subtask.getEpicId()));//Поместить этот объект в allEpics

    }


    @Override
    public void removeTaskById(Integer id) {//Удаление задачи по идентификатору
        allTasks.remove(id);
    }

    @Override
    public Object getAllSubtasks(Epic epic) {//Получить все подзадачи эпика
        return epic.getSubtasks();

    }


}
