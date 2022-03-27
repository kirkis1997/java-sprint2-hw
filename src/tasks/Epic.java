package tasks;

import utility.Status;

import java.util.HashMap;

public class Epic extends Task {

    private HashMap<Integer, Subtask> subtasks;//Уникальный список подзадач каждого эпика

    public Epic(String title, String description) {
        super(title, description);
        this.subtasks = new HashMap<>();
    }

    public HashMap<Integer, Subtask> getSubtasks() {
        return subtasks;
    }

    public void addSubtasks(Subtask subtask) {
        subtasks.put(subtask.uniqueId, subtask);
    }

    public void setEpicStatus() { //Метод, устанавливающий статус tasks.Epic
        int progressCount = 0;
        int doneCount = 0;
        for (Subtask subtask : subtasks.values()) {

            if (subtask.status == Status.IN_PROGRESS) {
                setStatus(Status.IN_PROGRESS);
                progressCount++;
            } else if (subtask.status == Status.DONE) {
                setStatus(Status.IN_PROGRESS);
                doneCount++;
            }

        }
        if (progressCount == subtasks.size()) {
            setStatus(Status.IN_PROGRESS);
        }
        if (doneCount == subtasks.size()) {
            setStatus(Status.DONE);
        }
    }

    @Override
    public String toString() {
        return "{Тип задачи: tasks.Epic" + ", Название задачи: '" + title + "', Описание задачи: '"
                + description + "', Идентификатор задачи: " + this.uniqueId + ", Статус задачи: '"
                + status + "'}\n";
    }
}


