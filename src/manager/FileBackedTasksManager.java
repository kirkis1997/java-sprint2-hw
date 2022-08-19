package manager;

import manager.interfaces.HistoryManager;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;
import utility.ManagerSaveException;
import utility.Status;

import java.io.*;
import java.util.*;
import java.io.BufferedReader;


public class FileBackedTasksManager extends InMemoryTaskManager {

    private final HashMap<Integer, Task> allTasks = new HashMap<>(); //HashMap для списка всех задач
    private HistoryManager historyManager;

    @Override
    public void createNewTask(Task task) throws IOException {
        super.createNewTask(task);
        save();
    }

    public FileBackedTasksManager() {
        this.historyManager = new InMemoryHistoryManager();
    }

    @Override
    public HistoryManager getHistoryManager() {
        return super.getHistoryManager();
    }

    public void setHistoryList(List<Integer> list) {
        for (Integer taskId : list ) {
            historyManager.add(allTasks.get(taskId));
        }
    }

    @Override
    public HashMap<Integer, Task> getAllTasks() {
        return super.getAllTasks();
    }

    @Override
    public void removeAllTasks() throws IOException {
        super.removeAllTasks();
        save();
    }

    @Override
    public Task getTaskById(Integer id) {
        return super.getTaskById(id);
    }

    @Override
    public void updateTask(Integer id, Task task, Status status) throws IOException {
        super.updateTask(id, task, status);
        save();
    }

    @Override
    public void removeTaskById(Integer id) throws IOException {
        super.removeTaskById(id);
        save();
    }

    @Override
    public HashMap<Integer, Subtask> getAllSubtasks(Epic epic) {
        return super.getAllSubtasks(epic);
    }

    @Override
    public List<Task> history() {
        return super.history();
    }

    public void save() throws ManagerSaveException { //Метод сохранения данных в файл

        try {
            FileWriter file = new FileWriter("C:\\Users\\kirki\\Desktop\\tasks.csv", false);

            file.write("id,type,name,status,description,epic\n");

            for (Map.Entry<Integer, Task> taskEntry : getAllTasks().entrySet()  ) {

                file.write(taskEntry.getValue() + "\n");
            }
            file.write("\n" + toString(getHistoryManager()));
            file.close();
        } catch (IOException e) {
            throw new ManagerSaveException("Error");
        }


    }

    private static String toString(HistoryManager manager) { /*Напишите статические методы static
    String toString(HistoryManager manager)*/
        String tasksId = new String();
        int count = 1;
        for (Task task : manager.getHistory()) {
            if (count != manager.getHistory().size()) {
                tasksId += (task.getUniqueId().toString()) + ",";
                count++;
            }
           else tasksId += task.getUniqueId().toString();

        }
        return tasksId.toString();
    }

    private static Task fromStringToTask(String value) { //Напишите метод создания задачи из строки Task fromString(String value)

        String[] options = value.split(",");

        Task task = new Task(options[2], options[3], Status.valueOf(options[4]), Integer.parseInt(options[0]));

        return task;
    }

    /*private Epic fromStringToEpic(String value) {

        String[] options = value.split(",");

        Epic task = new Epic(options[2], options[3], Status.valueOf(options[4]), Integer.parseInt(options[0]));

        return task;
    }

    private Subtask fromStringToSubtask(String value) {

        String[] options = value.split(",");

        Subtask task = new Subtask(options[2], options[3], Status.valueOf(options[4]), Integer.parseInt(options[0]),
                Integer.parseInt(options[5]));
        return task;

    }*/

    static List<Integer> fromStringToHistoryManager(String value) { /*Напишите статические методы
    static List<Integer> fromString(String value)*/

        String[] tasksInHistory = value.split(",");
       List<Integer> list = new ArrayList<>();
       for (String tasksId : tasksInHistory) {
           list.add(Integer.parseInt(tasksId));
       }
        return list;
    }

    static FileBackedTasksManager loadFromFile(File file) throws IOException {

        FileBackedTasksManager manager = new FileBackedTasksManager();

        HashMap<Integer, Task> allTasks = new HashMap<>();
        HistoryManager historyManager = new InMemoryHistoryManager();

        Reader fileReader = new FileReader("C:\\Users\\kirki\\Desktop\\tasks.csv");

        Scanner scan = new Scanner(fileReader);

        ArrayList<String> lines = new ArrayList<>();
        while (scan.hasNextLine()) {
            lines.add(scan.nextLine());
        }

        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).equals("id,type,name,status,description,epic")) {
                allTasks.put(fromStringToTask(lines.get(i+1)).getUniqueId(), fromStringToTask(lines.get(i+1)));
                i++;
            }

            else if (lines.get(i).isBlank()) {
                manager.setHistoryList(fromStringToHistoryManager(lines.get(i)));
                break;
            }
            else allTasks.put(fromStringToTask(lines.get(i+1)).getUniqueId(), fromStringToTask(lines.get(i)));
        }

        fileReader.close();
        scan.close();

        return manager;
    }

    public static void main(String[] args) throws IOException {

        FileBackedTasksManager manager = new FileBackedTasksManager();

        /**
         * Создать две простые задачи, эпик с тремя подзадачами и пустой эпик
         */

        Task task = new Task("Оплатить счет за КУ", "За два месяца");
        manager.createNewTask(task);

        Task task2 = new Task("Сходить к ветеринару", "Сдать кошку в ремонт");
        manager.createNewTask(task2);

        Epic epic = new Epic("Купить продукты", "Приобрести продукты для ужина");
        manager.createNewTask(epic);
        Subtask subtask = new Subtask("Купить молоко", "Для молочного коктейля", epic.getUniqueId());
        manager.createNewTask(subtask);
        Subtask subtask1 = new Subtask("Купить мясо", "Для запекания в духовке", epic.getUniqueId());
        manager.createNewTask(subtask1);
        //Subtask subtask2 = new Subtask("Купить картофель", "Для гарнира", epic.getUniqueId());

        Epic epic1 = new Epic("Купить электронику", "Чтобы было за что платить КУ");
        manager.createNewTask(epic1);


        /**
         * Тест
         * Запросить созданные задачи несколько раз в произвольном порядке
         */

        manager.getTaskById(0);
        //System.out.println(manager.history());
        manager.getTaskById(1);
        //System.out.println(manager.history());
        manager.getTaskById(2);
        //System.out.println(manager.history());
        manager.getTaskById(1);
        //System.out.println(manager.history());
        manager.getTaskById(2);
        //System.out.println(manager.history());
        manager.getTaskById(3);
        //System.out.println(manager.history());
        manager.getTaskById(4);
        //System.out.println(manager.history());
        manager.getTaskById(5);
        //System.out.println(manager.history());
        manager.getTaskById(2);
        //System.out.println(manager.history());
        manager.getTaskById(4);
        //System.out.println(manager.history());

        //manager.removeTaskById(0);//Удалить задачу из списка задач
        //System.out.println(manager.history());

        //manager.removeTaskById(2);//Удалить эпик из списка задач

        //System.out.println(manager.history());

        //System.out.println(manager.getAllTasks());
        manager.getTaskById(0);
        manager.getTaskById(1);
        //System.out.println(manager.getHistoryManager().getHistory());

    }


}
