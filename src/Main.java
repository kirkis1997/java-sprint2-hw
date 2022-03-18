import manager.InMemoryTaskManager;
import manager.InMemoryHistoryManager;
import manager.Status;
import tasks.*;


public class Main {
    public static void main(String[] args) {

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
        Subtask subtask2 = new Subtask("Купить картофель", "Для гарнира", epic.getUniqueId());
        manager.createNewTask(subtask2);
        epic.setSubtasks(subtask);
        epic.setSubtasks(subtask1);
        epic.setSubtasks(subtask2);

        Epic epic1 = new Epic("Купить электронику", "Чтобы было за что платить КУ");
        manager.createNewTask(epic1);
        Subtask subtask3 = new Subtask("Купить тостер", "Для тостов", epic1.getUniqueId());
        manager.createNewTask(subtask3);
        epic1.setSubtasks(subtask3);
        Subtask subtask4 = new Subtask("Купить чайник", "Варить пельмени", epic1.getUniqueId());
        manager.createNewTask(subtask4);
        epic1.setSubtasks(subtask4);



        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                manager.getTaskById(j);
            }

        }

        manager.updateSubtask(3, subtask, Status.DONE);

        System.out.println(manager.getAllTasks());
    }
}
