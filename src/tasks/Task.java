package tasks;
import taskmanager.TaskManager;

public class Task extends TaskManager {//extends taskmanager.TaskManager
    protected String Title;
    protected String description;
    protected String status;
    protected Integer uniqueId;

    public Task(String Title, String description) {
        this.Title = Title;
        this.description = description;
        this.status = "NEW";
        this.uniqueId = getCount();
        setCount();
    }

    public Integer getUniqueId() {
        return uniqueId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{Тип задачи: tasks.Task" + ", Название задачи: '" + Title + "', Описание задачи: '" + description
                + "', Идентификатор задачи: " + this.uniqueId + ", Статус задачи: '" + status + "'\n";
    }
}
