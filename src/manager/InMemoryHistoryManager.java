package manager;

import manager.interfaces.HistoryManager;
import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private ArrayList<Task> watchHistoryList = new ArrayList<>();// ArrayList для хранения списка просмотренных задач

    @Override
    public void add(Task task) {
        watchHistoryList.add(task);
        if (watchHistoryList.size() > 10) {//Убрать самый старый элемент истории, если размер списка превысит 10 эл-тов
            watchHistoryList.remove(0);
        }
    }


    @Override
    public List<Task> getHistory() {
        return watchHistoryList;
    }

}


