package manager;

public class Managers {//Утилитарный класс Managers
    public static InMemoryTaskManager getDefault() {
        return InMemoryTaskManager.getInstance();
    }
    public static InMemoryHistoryManager getDefaultHistory() {
        return InMemoryHistoryManager.getInstance();
    }
}
