package tasks;

import manager.InMemoryTaskManager;
import utility.Status;
import utility.TaskTypes;

import java.util.Objects;

public class Task {

    protected String title;
    protected String description;
    protected Status status;
    protected Integer uniqueId;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = Status.NEW;
        this.uniqueId = InMemoryTaskManager.getCount();
    }

    public Task(String title, String description, Status status, Integer uniqueId) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.uniqueId = uniqueId;
    }

    public void setUniqueId(Integer id) {
        this.uniqueId = id;
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
    public String toString() {//Напишите метод сохранения задачи в строку String toString(Task task)
        return String.format("%d,%s,%s,%s,%s,", uniqueId, TaskTypes.TASK,
                title, description, status.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return title.equals(task.title) && description.equals(task.description) && status.equals(task.status)
                && uniqueId.equals(task.uniqueId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, status, uniqueId);
    }
}
