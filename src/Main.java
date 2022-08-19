import manager.FileBackedTasksManager;
import manager.InMemoryTaskManager;
import manager.interfaces.HistoryManager;
import manager.interfaces.TaskManager;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;
import utility.Managers;

import java.io.IOException;
import java.util.Map;


public class Main {
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
