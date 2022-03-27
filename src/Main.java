
import manager.InMemoryHistoryManager;
import manager.InMemoryTaskManager;
import manager.interfaces.HistoryManager;
import manager.interfaces.TaskManager;
import tasks.*;
import utility.Managers;
import utility.Status;


public class Main {
    public static void main(String[] args) {

        TaskManager manager = (InMemoryTaskManager) Managers.getDefault();
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
        Subtask subtask2 = new Subtask("Купить картофель", "Для гарнира", epic.getUniqueId());

        Epic epic1 = new Epic("Купить электронику", "Чтобы было за что платить КУ");
        manager.createNewTask(epic1);
        Subtask subtask3 = new Subtask("Купить тостер", "Для тостов", epic1.getUniqueId());
        manager.createNewTask(subtask3);
        Subtask subtask4 = new Subtask("Купить чайник", "Варить пельмени", epic1.getUniqueId());
        manager.createNewTask(subtask4);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                manager.getTaskById(j);
            }

        }

        manager.updateTask(3, subtask, Status.DONE);
        manager.updateTask(4, subtask1, Status.DONE);

        manager.createNewTask(subtask2);


        System.out.println(((InMemoryTaskManager) manager).historyManager.getHistory());

    }
}
