import manager.FileBackedTasksManager;
import manager.InMemoryTaskManager;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {

        InMemoryTaskManager manager = new InMemoryTaskManager();

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

        manager.getTaskById(0);
        manager.getTaskById(1);
        //System.out.println(manager.getHistoryManager().getHistory());

    }
}
