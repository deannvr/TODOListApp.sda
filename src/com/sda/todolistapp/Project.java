package com.sda.todolistapp;

import java.io.Serializable;
import java.net.CookieHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Project implements Serializable{
    private String name;
    private List<Task> tasks;

    public Project() {

    }

    public Project(String name) {
        this.name = name;
        tasks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void setTaskAsDone(int taskIndex) {
        Task task = getTask(taskIndex);
        task.setDone(true);
    }

    public int countDoneTasks() {
        return countTasksIfDone(true);
    }

    public int countNotDoneTasks() {
        return countTasksIfDone(false);
    }

    private int countTasksIfDone(boolean done) {
        int counter = 0;
        for(Task task: tasks) {
            if(task.isDone() == done) {
                counter++;
            }
        }

        return counter;
    }

    private List<Task> getTasksIfDone(boolean done) {
        List<Task> tmpTasks = new ArrayList<>();
        for(Task task: tasks) {
            if(task.isDone() == done) {
                tmpTasks.add(task);
            }
        }
        return tmpTasks;
    }

    public List<Task> getDoneTasks() {
        return getTasksIfDone(true);
    }

    public List<Task> getNotDoneTasks() {
        return getTasksIfDone(false);
    }

    public List<Task> sortTasksByDueDate() {
        //Create new temporary list of tasks
        List<Task> tmpTasks = new ArrayList<>(tasks);

        //Sort temporary tasks
        Collections.sort(tmpTasks, new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.getDueDate().compareTo(o2.getDueDate());
            }
        });
        return tmpTasks;
    }

    private boolean isValidIndex(int index) {
        if(index < 0 || index >= tasks.size()) {
            System.out.println("Invalid index selected ");
            return false;
        }
        return true;
    }
    public Task getTask(int index) {
        if(!isValidIndex(index)) {
            return null;
        }
        return tasks.get(index);
    }

    public void setTask(int index, Task task) {
        if(isValidIndex(index)) {
            tasks.set(index, task);
        }
    }

    public void removeTask(int index) {
        if(isValidIndex(index)) {
            tasks.remove(index);
        }
    }
}
