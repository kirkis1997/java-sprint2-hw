package tasks;

import manager.InMemoryTaskManager;
import manager.Status;
import java.util.Objects;

public class Task {
    protected String Title;
    protected String description;
    protected Status status;
    protected Integer uniqueId;

    public Task(String Title, String description) {
        this.Title = Title;
        this.description = description;
        this.status = Status.NEW;
        this.uniqueId = InMemoryTaskManager.getCount();
        InMemoryTaskManager.setCount();
    }

    public Integer getUniqueId() {
        return uniqueId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{Тип задачи: tasks.Task" + ", Название задачи: '" + Title + "', Описание задачи: '" + description
                + "', Идентификатор задачи: " + this.uniqueId + ", Статус задачи: '" + status + "'\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Title.equals(task.Title) && description.equals(task.description) && status.equals(task.status)
                && uniqueId.equals(task.uniqueId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Title, description, status, uniqueId);
    }
}
