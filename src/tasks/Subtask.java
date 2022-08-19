package tasks;

import utility.Status;
import utility.TaskTypes;

public class Subtask extends Task {

    private Integer epicId;

    public Subtask(String title, String description, Integer epicId) {
        super(title, description);
        this.epicId = epicId;
    }

    public Subtask(String title, String description, Status status, Integer uniqueId, Integer epicId) {
        super(title, description, status, uniqueId);
        this.epicId = epicId;
    }

    public Integer getEpicId() {
        return epicId;
    }

    @Override
    public String toString() {//Напишите метод сохранения задачи в строку String toString(Task task)
        return  String.format("%d,%s,%s,%s,%s,%d", uniqueId, TaskTypes.SUBTASK,
                title, description, status.toString(), epicId);
    }
}
