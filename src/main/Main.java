package main;

import manager.InMemoryTaskManager;
import manager.Managers;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {

        Task task = new Task("Оплатить счет за КУ", "За два месяца");
        Managers.getDefault().createNewTask(task);

        Task task2 = new Task("Сходить к ветеринару", "Сдать кошку в ремонт");
        Managers.getDefault().createNewTask(task2);

        Epic epic = new Epic("Купить продукты", "Приобрести продукты для ужина");
        Managers.getDefault().createNewTask(epic);
        Subtask subtask = new Subtask("Купить молоко", "Для молочного коктейля", epic.getUniqueId());
        Managers.getDefault().createNewTask(subtask);
        Subtask subtask1 = new Subtask("Купить мясо", "Для запекания в духовке", epic.getUniqueId());
        Managers.getDefault().createNewTask(subtask1);
        Subtask subtask2 = new Subtask("Купить картофель", "Для гарнира", epic.getUniqueId());
        Managers.getDefault().createNewTask(subtask2);
        epic.setSubtasks(subtask);
        epic.setSubtasks(subtask1);
        epic.setSubtasks(subtask2);

        Epic epic1 = new Epic("Купить электронику", "Чтобы было за что платить КУ");
        Managers.getDefault().createNewTask(epic1);
        Subtask subtask3 = new Subtask("Купить тостер", "Для тостов", epic1.getUniqueId());
        Managers.getDefault().createNewTask(subtask3);
        epic1.setSubtasks(subtask3);
        Subtask subtask4 = new Subtask("Купить чайник", "Варить пельмени", epic1.getUniqueId());
        Managers.getDefault().createNewTask(subtask4);
        epic1.setSubtasks(subtask4);


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                Managers.getDefault().getTaskById(j);
            }

        }

        System.out.println(Managers.getDefaultHistory().getHistory());
    }
}
