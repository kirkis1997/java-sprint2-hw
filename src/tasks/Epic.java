package tasks;

import java.util.HashMap;

public class Epic extends Task {
    private static HashMap<Integer, Subtask> subtasks;

    public Epic(String Title, String description) {
        super(Title, description);
        this.subtasks = new HashMap<>();
    }

    public static HashMap<Integer, Subtask> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(Subtask subtask) {
        subtasks.put(subtask.uniqueId, subtask);
    }

    public void setEpicStatus() { //Метод, устанавливающий статус tasks.Epic
        int progressCount = 0;
        int doneCount = 0;
        for (Subtask subtask : subtasks.values()) {

            if (subtask.status.equals("IN_PROGRESS")) {
                setStatus("IN_PROGRESS");
                progressCount++;
            } else if (subtask.status.equals("DONE") && subtask.status != null) {
                setStatus("IN_PROGRESS");
                doneCount++;
            }

        }
        if (progressCount == subtasks.size()) {
            setStatus("IN_PROGRESS");
        }
        if (doneCount == subtasks.size()) {
            setStatus("DONE");
        }
    }

    @Override
    public String toString() {
        return "{Тип задачи: tasks.Epic" + ", Название задачи: '" + Title + "', Описание задачи: '"
                + description + "', Идентификатор задачи: " + this.uniqueId + ", Статус задачи: '"
                + status + "'}\n";
    }
}
