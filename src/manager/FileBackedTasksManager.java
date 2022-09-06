package manager;

import manager.interfaces.HistoryManager;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;
import utility.ManagerSaveException;
import utility.Managers;
import utility.Status;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class FileBackedTasksManager extends InMemoryTaskManager {

    private Path file;

    public Path getFile() {
        return file;
    }

    @Override
    public void createNewTask(Task task) throws IOException {
        super.createNewTask(task);
        save();
    }

    public void setHistoryList(List<Integer> list) {
        for (Integer taskId : list) {
            getHistoryManager().add(getAllTasks().get(taskId));
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
    public Task getTaskById(Integer id) throws IOException {
        save();
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

    public void save() { //Метод сохранения данных в файл

        Path desktop = Paths.get(System.getProperty("user.home") + "\\Desktop\\task.csv");

        file = desktop;


        if (Files.notExists(file)) {
            try {
                file = Files.createFile(desktop);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try (FileWriter fileWriter = new FileWriter(file.toFile(), false)) {

            fileWriter.write("id,type,name,status,description,epic\n");

            for (Map.Entry<Integer, Task> taskEntry : getAllTasks().entrySet()) {

                fileWriter.write(taskEntry.getValue() + "\n");
            }

            fileWriter.write("\n" + historyToString(getHistoryManager()));


        } catch (IOException e) {
            throw new ManagerSaveException("Error");
        }


    }

    private String historyToString(HistoryManager manager) {

        String tasksId = new String();
        int count = 1;
        for (Task task : manager.getHistory()) {
            if (count != manager.getHistory().size()) {
                tasksId += (task.getUniqueId().toString()) + ",";
                count++;
            } else tasksId += task.getUniqueId().toString();

        }
        return tasksId;
    }

    private static Task fromStringToTask(String value) {

        String[] options = value.split(",");

        return new Task(options[2], options[3], Status.valueOf(options[4]), Integer.parseInt(options[0]));
    }


    static List<Integer> fromStringToHistoryManager(String value) { /*Напишите статические методы
    static List<Integer> fromString(String value)*/

        String[] tasksInHistory = value.split(",");
        List<Integer> list = new ArrayList<>();
        for (String tasksId : tasksInHistory) {
            list.add(Integer.parseInt(tasksId));
        }
        return list;
    }

    public static FileBackedTasksManager loadFromFile(Path file) {

        FileBackedTasksManager manager = new FileBackedTasksManager();

        try {
            Reader fileReader = new FileReader(file.toFile());

            Scanner scan = new Scanner(fileReader);

            ArrayList<String> lines = new ArrayList<>();
            while (scan.hasNextLine()) {
                lines.add(scan.nextLine());
            }

            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).equals("id,type,name,status,description,epic")) {
                    manager.getAllTasks().put(fromStringToTask(lines.get(i + 1)).getUniqueId(),
                            fromStringToTask(lines.get(i + 1)));
                    i++;
                } else if (lines.get(i).isBlank()) {
                    manager.setHistoryList(fromStringToHistoryManager(lines.get(i + 1)));
                    break;
                } else
                    manager.getAllTasks().put(fromStringToTask(lines.get(i)).getUniqueId(),
                            fromStringToTask(lines.get(i)));
            }

            fileReader.close();
            scan.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return manager;
    }

    public static void main(String[] args) throws IOException {

        FileBackedTasksManager manager = new FileBackedTasksManager();

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

        Epic epic1 = new Epic("Купить электронику", "Чтобы было за что платить КУ");
        manager.createNewTask(epic1);

        manager.getTaskById(0);
        manager.getTaskById(1);
        manager.getTaskById(2);
        manager.getTaskById(1);
        manager.getTaskById(2);
        manager.getTaskById(3);
        manager.getTaskById(4);

        manager.getTaskById(2);
        manager.getTaskById(4);

        manager.getTaskById(0);
        manager.getTaskById(1);


        FileBackedTasksManager newManager = loadFromFile(manager.getFile());//Создать новый
        // FileBackedTasksManager из файла

        manager.getTaskById(1);
        manager.getTaskById(2);
        manager.getTaskById(3);
        manager.getTaskById(4);
        manager.getTaskById(5);
        manager.getTaskById(0);
        manager.getTaskById(1);
        manager.getTaskById(2);//Изменить историю просмотров

    }


}
