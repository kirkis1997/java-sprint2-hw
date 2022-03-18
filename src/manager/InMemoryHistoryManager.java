package manager;

import manager.interfaces.HistoryManager;
import tasks.*;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private static InMemoryHistoryManager instance;
    private static ArrayList<Task> watchHistoryList = new ArrayList<>();// ArrayList для хранения списка просмотренных задач

    public static InMemoryHistoryManager getInstance() {
        if (instance == null) {
            instance = new InMemoryHistoryManager();
        }
        return instance;
    }

    public static ArrayList<Task> getWatchHistoryList() {
        return watchHistoryList;
    }

    @Override
    public void add(Task task) {
        watchHistoryList.add(task);
        if (watchHistoryList.size() > 10) {//Убрать самый старый элемент истории, если размер списка превысит 10 эл-тов
            watchHistoryList.remove(0);
        }
    }

    public void add(Subtask subtask) {
        watchHistoryList.add(subtask);
        if (watchHistoryList.size() > 10) {//Убрать самый старый элемент истории, если размер списка превысит 10 эл-тов
            watchHistoryList.remove(0);
        }
    }

    public void add(Epic epic) {
        watchHistoryList.add(epic);
        if (watchHistoryList.size() > 10) {//Убрать самый старый элемент истории, если размер списка превысит 10 эл-тов
            watchHistoryList.remove(0);
        }
    }

    @Override
    public List<Task> getHistory () {
        return watchHistoryList;
    }

}


