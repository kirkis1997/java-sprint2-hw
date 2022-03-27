package utility;

import manager.InMemoryHistoryManager;
import manager.InMemoryTaskManager;
import manager.interfaces.*;

public class Managers {//Утилитарный класс Managers

    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
