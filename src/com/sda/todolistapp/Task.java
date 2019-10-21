package com.sda.todolistapp;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task implements Serializable{
    private String title;
    private String description;
    private String dueDate;
    private boolean done;

    public Task() {
        setDone(false);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (done != task.done) return false;
        if (title != null ? !title.equals(task.title) : task.title != null) return false;
        if (description != null ? !description.equals(task.description) : task.description != null) return false;
        return dueDate != null ? dueDate.equals(task.dueDate) : task.dueDate == null;

        /**
         * if (done != task.done) return false;
         * if (!Objects.equals(title, task.title)) return false;
         *         if (!Objects.equals(description, task.description)) return false;
         *         return Objects.equals(dueDate, task.dueDate);
         */
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
        result = 31 * result + (done ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("Title: %s, Desciption: %s; Due date: %s, Done: %s;", title, description, dueDate, done);
    }
}
