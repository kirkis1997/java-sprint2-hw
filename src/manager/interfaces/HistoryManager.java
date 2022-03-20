package manager.interfaces;

import tasks.Task;
import java.util.List;

public interface HistoryManager {

    abstract void add(Task task);

    List<Task> getHistory();//Вернуть список просмотренных задач
}
