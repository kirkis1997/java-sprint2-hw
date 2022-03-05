package tasks;

public class Subtask extends Task {
    private Integer epicId;

    public Subtask(String Title, String description, Integer epicId) {
        super(Title, description);
        this.epicId = epicId;
    }

    public Integer getEpicId() {
        return epicId;
    }

    @Override
    public String toString() {
        return "{Тип задачи: tasks.Subtask," + " Находится в эпике с идентификатором: '" + epicId + "', Название задачи: "
                + Title + "' Описание задачи: '"
                + description + "', Идентификатор задачи: " + this.uniqueId + ", Статус задачи: '"
                + status + "'}\n";
    }
}
